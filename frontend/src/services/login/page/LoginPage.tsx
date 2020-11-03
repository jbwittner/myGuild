import { createStyles, Grid, makeStyles, Paper } from '@material-ui/core';
import React from 'react';
import Login from '../components/Login';

const useStyle = makeStyles(() =>
    createStyles({
        background: {
            height: '100vh',
            minHeight: '150px',
            backgroundImage: 'url(../1049851.jpg)',
            backgroundPosition: 'center top',
            backgroundSize: 'cover',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center'

        },
        paper:{
            width: '300px',
            backgroundColor: '#242424',
        },
        logo: {

        },
    })
)

export default function LoginPage() {

    const classes = useStyle();

    return (
        <React.Fragment>
            <div className={classes.background}>
                <Paper variant="outlined" elevation={3} className={classes.paper}>
                    <Grid container direction="column" justify="center" alignItems="center">
                        <Grid item>
                            <img src={'../logo192.png'} alt="logos" className={classes.logo}/>
                        </Grid>
                        <Grid item>
                            <Login />
                        </Grid>
                    </Grid>
                </Paper>
            </div>

        </React.Fragment>
    )

}