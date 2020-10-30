import React from 'react';
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField } from "@material-ui/core";

export default function MainRouteur() {

    return (
        <React.Fragment>
            <Button variant="contained" color="primary" startIcon={
                <img src={'../Blizzard_Entertainment_Logo.svg'} alt="logo"/>
            }>
                Login with blizzard
            </Button>

            <Dialog open={false} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Registration</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            It&apos;s your first connection, please to renseign this informations
                        </DialogContentText>
                        <TextField autoFocus margin="dense" id="name" label="Nick name" type="email" fullWidth/>
                        <TextField autoFocus margin="dense" id="name" label="email" type="email" fullWidth/>
                    </DialogContent>
                <DialogActions>
                <Button color="primary">
                    Cancel
                </Button>
                <Button color="primary">
                    Subscribe
                </Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    )

}