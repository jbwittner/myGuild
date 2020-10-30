import React, { useState } from 'react';
import { Button, createStyles, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField } from "@material-ui/core";
import { makeStyles,  } from '@material-ui/core/styles';

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

export default function Login() {

    const [loginClick, setLoginClick] = useState(false);

    const classes = useStyle();

    return (
        <React.Fragment>
            <Button variant="contained" color="primary" className={classes.button} startIcon={
                <img src={'../Blizzard_Entertainment_Logo.svg'} className={classes.imageButton} alt="logo"/>
            } onClick={() => {setLoginClick(true)}}>
                Login with blizzard
            </Button>

            <Dialog open={loginClick} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Registration</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            It&apos;s your first connection, please to renseign this informations
                        </DialogContentText>
                        <TextField id="name" label="Nick name" fullWidth/>
                        <TextField id="mail" label="Email" fullWidth/>
                    </DialogContent>
                <DialogActions>
                <Button color="primary" onClick={() => {setLoginClick(false)}}>
                    Valid
                </Button>
                <Button color="primary" onClick={() => {setLoginClick(false)}}>
                    Cancel
                </Button>
                </DialogActions>
            </Dialog>

        </React.Fragment>
    )

}