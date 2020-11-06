import { Snackbar } from '@material-ui/core';
import { Alert, Color } from '@material-ui/lab';
import React from 'react';

interface AlertSnackBarProps{

    open: boolean;
    autoHideDuration?: number;
    onClose?: () => void;
    severity: Color
    children?: React.ReactNode;
    vertical?: 'top' | 'bottom';
    horizontal?: 'left' | 'center' | 'right';

}

export default function AlertSnackBar(props: AlertSnackBarProps) {

    const autoHideDuration = props.autoHideDuration !== undefined ? props.autoHideDuration : 6000;
    const verticalAnchor = props.vertical !== undefined ? props.vertical : 'top';
    const horizontalAnchor = props.horizontal !== undefined ? props.horizontal : 'right';

    return (
        <Snackbar color="primary" open={props.open} autoHideDuration={autoHideDuration} onClose={props.onClose} anchorOrigin={{ vertical: verticalAnchor, horizontal: horizontalAnchor }}>
            <Alert variant="filled" onClose={props.onClose} severity={props.severity}>
                {props.children}
            </Alert>
        </Snackbar>
    )
}