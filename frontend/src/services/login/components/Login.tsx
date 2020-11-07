import React, { useState } from 'react';
import { Button, createStyles} from "@material-ui/core";
import { makeStyles,  } from '@material-ui/core/styles';
import { UserHttpClient } from '../../../api/clients/UserHttpClient';
import { LocalStorage } from '../../storage/LocalStorage';
import { useHistory } from 'react-router-dom';
import { GeneralContext } from '../../common/Context';
import Registration from '../components/Registration';
import { BlizzardHttpClient } from '../../../api/clients/BlizzardHttpClient';
import { SessionStorage } from '../../storage/SessionStorage';
import { CharacterSummaryDTO, StaticDataDTO } from '../../../api/Entities';
import AlertSnackBar from '../../common/AlertSnackBar'
import CircularProgressScreen from '../../common/CircularProgressScreen';

const useStyle = makeStyles(() =>
    createStyles({
        imageButton: {
            height: '40px'
        },
        button: {
            backgroundColor: '#3f51b5',
            "&:hover": {
                backgroundColor: "#1a237e"
              },
            fontFamily: 'MORPHEUS',
            height: '50px'
        }
    })
)

export default function Login() {

    const history = useHistory();
    const [downloadInProgress, setDownloadInProgress] = useState(false);
    const {setIsSignedIn} = React.useContext(GeneralContext);
    const [isAlertOpen, setIsAlertOpen] = useState(false);
    const [alertMessage, setAlertMessage] = useState("");
    const [showModalRegistration, setShowModalRegistration] = useState(false);

    
    if (!process.env.NODE_ENV || process.env.NODE_ENV === 'development') { // eslint-disable-line no-use-before-define
        const staticData = SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA);
        const accountCharacters = SessionStorage.getItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS);
        if(staticData !== undefined && accountCharacters !== undefined){
            const userHttpClient = new UserHttpClient();

            userHttpClient.isAccountExist().then((accountIsExist: boolean) => {
                setShowModalRegistration(!accountIsExist);
    
                if(accountIsExist){
    
                    setIsSignedIn(accountIsExist);
                    history.push('/home');
                }
    
            }).catch(() => {
            })

        }

    } 

    const onRegistrationCancel = () => {
        setShowModalRegistration(false)
    }

    const onRegistrationSubmit = async () => {
        await login();
    }

    const login = async () => {

        const userHttpClient = new UserHttpClient();

        userHttpClient.isAccountExist().then((accountIsExist: boolean) => {
            setShowModalRegistration(!accountIsExist);

            if(accountIsExist){

                fetchData()
                .then(() => {
                    setIsSignedIn(accountIsExist);
                    history.push('/home');
                })
                .catch(() => {});
            }

        }).catch(() => {
            LocalStorage.setItem<boolean>(LocalStorage.LOGIN_CLICK, true);
            window.location.href = "/oauth2/authorization/oauth-blizzard"
        })

    }

    const fetchData = async () => {
        const blizzardHttpClient = new BlizzardHttpClient();

        setDownloadInProgress(true)

        SessionStorage.setItem<boolean>(SessionStorage.DATA_DOWNLOADED, true);

        return Promise.all([blizzardHttpClient.getStaticData(), blizzardHttpClient.fetchAccountCharacter()])
            .then((value: [StaticDataDTO, CharacterSummaryDTO[]]) => {
                const staticData = value[0];
                const accountCharacters = value[1]
                SessionStorage.setItem<StaticDataDTO>(SessionStorage.STATIC_DATA, staticData);
                SessionStorage.setItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS, accountCharacters);
            })
            .catch((reason) => {

                let message: string = "Problem during api call : "
            
                if(reason?.data?.path === BlizzardHttpClient.FETCH_ACCOUNT_CHARACTER_PATH) {
                    message = message + BlizzardHttpClient.FETCH_ACCOUNT_CHARACTER_PATH;
                }else if(reason?.data?.path === BlizzardHttpClient.FETCH_STATIC_DATA_PATH){
                    message = message + BlizzardHttpClient.FETCH_STATIC_DATA_PATH;
                }else{
                    message = message + "undefined";
                }

                setAlertMessage(message)
                setIsAlertOpen(true);

                throw 'Problem with fetching data' 
            })
            .finally(() => {
                setDownloadInProgress(false)
            });
        
    }
    
    const isClicked = LocalStorage.getItem<boolean>(LocalStorage.LOGIN_CLICK) ;

    if(isClicked === true){      
        LocalStorage.removeItem(LocalStorage.LOGIN_CLICK);
        login();
    }

    const onCloseAlert = () => {
        setIsAlertOpen(false);
    }

    const classes = useStyle();

    return (
        <React.Fragment>
            
            <Button variant="contained" color="primary" className={classes.button} startIcon={
                        <img src={'../Blizzard_Entertainment_Logo.svg'} className={classes.imageButton} alt="logo"/>
                        } onClick={() => {login()}}>
                        Login with blizzard
                    </Button>
            
            <Registration open={showModalRegistration} onSubmit={onRegistrationSubmit} onCancel={onRegistrationCancel}/>

            <AlertSnackBar open={isAlertOpen} severity="error" onClose={onCloseAlert}>{alertMessage}</AlertSnackBar>

                    <CircularProgressScreen open={downloadInProgress}>{"Download in progress"}</CircularProgressScreen>

        </React.Fragment>
    )

}