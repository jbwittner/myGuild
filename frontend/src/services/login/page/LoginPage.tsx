import React, { useState } from 'react';
import { Button, createStyles } from "@material-ui/core";
import { makeStyles,  } from '@material-ui/core/styles';
import { SecurityHttpClient } from '../../../api/SecurityHttpClient';
import { UserHttpClient } from '../../../api/UserHttpClient';
//import { useForm } from 'react-hook-form';
//import { AddUserParameter } from "../../../api/Entities";
import { LocalStorage } from '../../storage/LocalStorage';
import { useHistory } from 'react-router-dom';
import { GeneralContext } from '../../common/Context';
import Registration from '../components/Registration';

const useStyle = makeStyles(() =>
    createStyles({
        imageButton: {
            width: '70px'
        },
        button: {
            fontFamily: 'MORPHEUS'
        }
    })
)

/*
type FormInputs = {
    nickName: string
    email: string
  }
*/

export default function LoginPage() {

    const history = useHistory();
    const {isSignedIn, setIsSignedIn} = React.useContext(GeneralContext);
    const [showModalRegistration, setShowModalRegistration] = useState(false);

    /*
    const { register, handleSubmit } = useForm<FormInputs>();

    const onSubmit = async (data: FormInputs) => {
        console.log(data)
        const userHttpClient = new UserHttpClient();

        const parameter: AddUserParameter = {
            nickName: data.nickName,
            email: data.email
        }

        try{
            await userHttpClient.addUserAccount(parameter);
            setShowModalRegistration(false)
        } catch(e){
            console.log(e)
        }

    };
    */

    const onCancel = () => {
        setShowModalRegistration(false)
    }

    const login = async () => {

        const securityHttpClient = new SecurityHttpClient();
        const userHttpClient = new UserHttpClient();
        try{
            await securityHttpClient.connectionTest();
            const accountIsExist = await userHttpClient.isAccountExist();
            console.log("isSignedIn : " + isSignedIn)
            setIsSignedIn(true);
            console.log("isSignedIn : " + isSignedIn)

            accountIsExist == false ? setShowModalRegistration(true) : () =>{};

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
    
    const onClickTest = () => {
        console.log(history)
        history.push('/test')
    }
    const classes = useStyle();

    return (
        <React.Fragment>
            <Button variant="contained" color="primary" className={classes.button} startIcon={
                <img src={'../Blizzard_Entertainment_Logo.svg'} className={classes.imageButton} alt="logo"/>
            } onClick={() => {login()}}>
                Login with blizzard
            </Button>
            <Button onClick={onClickTest}>go to test</Button>

            <Registration open={showModalRegistration} onCancel={onCancel}/>

        </React.Fragment>
    )

}