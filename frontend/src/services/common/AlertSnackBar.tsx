import { Snackbar } from '@material-ui/core';
import { Alert, Color } from '@material-ui/lab';
import React from 'react';

interface AlertSnackBarProps{

    open: boolean;
    autoHideDuration?: number;
    onClose?: () => void;
    severity: Color
    children?: React.ReactNode;

}

export default function AlertSnackBar(props: AlertSnackBarProps) {
    
    return (
        <Snackbar open={props.open} autoHideDuration={6000} onClose={props.onClose}>
            <Alert onClose={props.onClose} severity={props.severity}>
                {props.children}
            </Alert>
        </Snackbar>
    )
}