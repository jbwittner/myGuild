import React, { useState } from 'react';
import { Backdrop, Button, CircularProgress, createStyles, FormControlLabel, Grid} from "@material-ui/core";
import { makeStyles,  } from '@material-ui/core/styles';
import { SecurityHttpClient } from '../../../api/clients/SecurityHttpClient';
import { UserHttpClient } from '../../../api/clients/UserHttpClient';
import { LocalStorage } from '../../storage/LocalStorage';
import { useHistory } from 'react-router-dom';
import { GeneralContext } from '../../common/Context';
import Registration from '../components/Registration';
import { BlizzardHttpClient } from '../../../api/clients/BlizzardHttpClient';
import { CharacterSummaryDTO, StaticDataDTO } from '../../../api/Entities';
import { SessionStorage } from '../../storage/SessionStorage';
import ColoredCheckBox from '../../common/ColoredCheckBox';

const useStyle = makeStyles((theme) =>
    createStyles({
        imageButton: {
            height: '40px'
        },
        button: {
            fontFamily: 'MORPHEUS',
            height: '50px'
        },
        backdrop: {
            zIndex: theme.zIndex.drawer + 1,
            color: '#fff',
        },
        grid: {
            height: '100%'
        },
        checkBock: {
            color: '#ffffff',
            alignItems: 'center',
            textAlign: "center",
            fontFamily: ''
        }
    })
)

export default function Login() {

    const history = useHistory();
    const autoLogin = LocalStorage.getItem<boolean>(LocalStorage.AUTO_LOGIN);
    const {isSignedIn, setIsSignedIn} = React.useContext(GeneralContext);
    const [dowloadInProgress, setDownloadInProgress] = useState(false);
    const [showModalRegistration, setShowModalRegistration] = useState(false);
    const [checked, setChecked] = React.useState(autoLogin !== undefined ? autoLogin : false);

    const onCancel = () => {
        setShowModalRegistration(false)
    }

    const onSubmit = async () => {
        const userHttpClient = new UserHttpClient();
        const accountIsExist = await userHttpClient.isAccountExist();
        console.log("accountIsExist : " + accountIsExist)
        setIsSignedIn(accountIsExist);
        setShowModalRegistration(!accountIsExist);
        history.push('/home');
    }

    const login = async () => {

        const securityHttpClient = new SecurityHttpClient();
        const userHttpClient = new UserHttpClient();
        try{
            await securityHttpClient.connectionTest();
            const accountIsExist = await userHttpClient.isAccountExist();

            accountIsExist === false ? setShowModalRegistration(true) : () =>{};

            setShowModalRegistration(true)

            setIsSignedIn(accountIsExist);

            LocalStorage.setItem<boolean>(LocalStorage.AUTO_LOGIN, checked);

            accountIsExist === true ? history.push('/home') : ()=> {}

        } catch(e){
            LocalStorage.setItem<boolean>(LocalStorage.LOGIN_CLICK, true);
            window.location.href = "/oauth2/authorization/oauth-blizzard"
        }
        
    }
    


    const isClicked = LocalStorage.getItem<boolean>(LocalStorage.LOGIN_CLICK) ;

    if(isClicked === true){      
        LocalStorage.removeItem(LocalStorage.LOGIN_CLICK);
        login();
    }

    const isChecked = LocalStorage.getItem<boolean>(LocalStorage.AUTO_LOGIN) ;

    if(isChecked === true){      
        login();
    }
    
    const onClickTest = async () => {
        console.log(history)
        const blizzardHttpClient = new BlizzardHttpClient();

        setDownloadInProgress(true)
        const [staticData, accountCharacters] = await Promise.all([blizzardHttpClient.getStaticData(), blizzardHttpClient.fetchAccountCharacter()]);
        SessionStorage.setItem<StaticDataDTO>(SessionStorage.STATIC_DATA, staticData);
        SessionStorage.setItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS, accountCharacters);
        setDownloadInProgress(false)

    }

    const onChecked = (event: React.ChangeEvent<HTMLInputElement>) => {
        setChecked(event.target.checked)
    }

    const classes = useStyle();

    return (
        <React.Fragment>
            <Grid container direction="column" alignItems="center" justify="center" className={classes.grid}>
                <Grid item>
                    <Button variant="contained" color="primary" className={classes.button} startIcon={
                        <img src={'../Blizzard_Entertainment_Logo.svg'} className={classes.imageButton} alt="logo"/>
                        } onClick={() => {login()}}>
                        Login with blizzard
                    </Button>
                </Grid>
                <Grid item>
                    <FormControlLabel
                        className={classes.checkBock}
                        control={
                        <ColoredCheckBox
                            colorChecked={"#ffffff"}
                            colorUnchecked={"#ffffff"}
                            checked={checked}
                            onChange={onChecked}
                            name="checkedB"
                        />
                        } label="Remember me" />
                </Grid>
            </Grid>
            
            {isSignedIn && <Button onClick={onClickTest}>go to test</Button>}

            <Registration open={showModalRegistration} onSubmit={onSubmit} onCancel={onCancel}/>

            <Backdrop className={classes.backdrop} open={dowloadInProgress}>
                <CircularProgress color="inherit" />
            </Backdrop>

        </React.Fragment>
    )

}