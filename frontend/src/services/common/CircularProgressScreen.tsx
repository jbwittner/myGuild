import { Backdrop, Box, BoxProps, CircularProgress, createStyles, makeStyles } from '@material-ui/core'
import React from 'react'

const useStyle = makeStyles((theme) =>
        createStyles({
            backdrop: {
                zIndex: theme.zIndex.drawer + 1,
            },
            circularProgress: {
                color: '#ffffff'
            },
            text: {
                marginTop: '20px',
                color: '#ffffff',
                fontSize: '2rem'
            }
        })
    )

export interface CircularProgressScreenProps extends BoxProps{
    open: boolean;
}

export default function CircularProgressScreen(props: CircularProgressScreenProps) {

    const classes = useStyle();

    return (
        <Backdrop className={classes.backdrop} open={props.open}>
            <Box display='flex' flexDirection='column' alignItems='center'>
                <CircularProgress className={classes.circularProgress} thickness={2} size={'20vh'}/>
                <Box color='primary' className={classes.text}>
                    {props.children}
                </Box>
            </Box>
        </Backdrop>
    )
}