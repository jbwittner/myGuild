import {
  Box,
  Button,
  makeStyles,
  Paper,
  Theme,
  Typography,
} from '@material-ui/core'
import { indigo } from '@material-ui/core/colors'
import React from 'react'
import Logo from './../../../img/BlizzardLogo.svg'

const useStyles = makeStyles((theme: Theme) => ({
  paper: {
    width: '800px',
    padding: '25px',
    height: '300px',
  },
  box: {
    width: '100%',
    height: '100%',
  },
  title: {
    width: 'auto',
  },
  loginButton: {
    color: theme.palette.getContrastText(indigo[500]),
    backgroundColor: indigo[500],
    '&:hover': {
      backgroundColor: indigo[700],
    },
    height: '45px',
    paddingTop: '6px',
    paddingBottom: '4px',
  },
  iconButton: {
    width: '50px',
    marginLeft: '10px',
    marginRight: '10px',
  },
  buttonLabel: {
    marginRight: '10px',
    fontFamily: 'Race',
  },
}))

export default function LoginPaper(): JSX.Element {
  const classes = useStyles()

  return (
    <Paper className={classes.paper} variant="outlined">
      <Box
        className={classes.box}
        display="flex"
        flexDirection="column"
        justifyContent="space-around"
        alignItems="center"
      >
        <Typography variant="h1" className={classes.title}>
          My Guild
        </Typography>
        <Button
          variant="contained"
          color="primary"
          className={classes.loginButton}
          href={'/oauth2/authorization/oauth-blizzard'}
        >
          <img src={Logo} className={classes.iconButton} />
          <div className={classes.buttonLabel}>Login with BLIZZARD</div>
        </Button>
      </Box>
    </Paper>
  )
}
