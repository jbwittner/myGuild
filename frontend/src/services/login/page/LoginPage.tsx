import React, { useState } from 'react';
import { Backdrop, Button, Checkbox, CircularProgress, createStyles, FormControlLabel, Snackbar } from "@material-ui/core";
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
import { Alert } from '@material-ui/lab';

const useStyle = makeStyles((theme) =>
    createStyles({
        imageButton: {
            width: '70px'
        },
        button: {
            fontFamily: 'MORPHEUS'
        },
        backdrop: {
            zIndex: theme.zIndex.drawer + 1,
            color: '#fff',
        },
    })
)

export default function LoginPage() {

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
        history.push('/another');
    }

    const login = async () => {

        const securityHttpClient = new SecurityHttpClient();
        const userHttpClient = new UserHttpClient();
        try{
            await securityHttpClient.connectionTest();
            const accountIsExist = await userHttpClient.isAccountExist();

            accountIsExist == false ? setShowModalRegistration(true) : () =>{};

            setIsSignedIn(accountIsExist);
            LocalStorage.setItem<boolean>(LocalStorage.AUTO_LOGIN, checked);
            history.push('/another');

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
            <Button variant="contained" color="primary" className={classes.button} startIcon={
                <img src={'../Blizzard_Entertainment_Logo.svg'} className={classes.imageButton} alt="logo"/>
            } onClick={() => {login()}}>
                Login with blizzard
            </Button>

            <FormControlLabel
                control={
                <Checkbox
                    checked={checked}
                    onChange={onChecked}
                    name="checkedB"
                    color="primary"
                />
                }
                label="Primary"
            />

<Snackbar open={true} autoHideDuration={6000} onClose={() => {}}>
        <Alert onClose={() => {}} severity="success">
          This is a success message!
        </Alert>
      </Snackbar>

            {isSignedIn && <Button onClick={onClickTest}>go to test</Button>}

            <Registration open={showModalRegistration} onSubmit={onSubmit} onCancel={onCancel}/>

            <Backdrop className={classes.backdrop} open={dowloadInProgress}>
                <CircularProgress color="inherit" />
            </Backdrop>

        </React.Fragment>
    )

}