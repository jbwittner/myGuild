import { Box, createStyles, Grid, makeStyles, Paper } from '@material-ui/core';
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
        },
        paper:{
            width: '300px',
            backgroundColor: '#606060',
        },
        logo: {

        }
    })
)

export default function LoginPage() {

    const classes = useStyle();

    return (
        <React.Fragment>
            <Box className={classes.background} display="flex" justifyContent="center" alignItems="center">
              <Paper color="secondary" variant="outlined" elevation={3} className={classes.paper}>
                <Grid container direction="column" justify="center" alignItems="center">
                  <Grid item>
                    <img src={'../logo192.png'} alt="logos" className={classes.logo}/>
                  </Grid>
                  <Grid item>
                    <Login />
                  </Grid>
                </Grid>
              </Paper>
            </Box>
        </React.Fragment>
    )

}