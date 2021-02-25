import React, { useCallback } from 'react'
import { createStyles, makeStyles } from '@material-ui/core/styles'
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Button from '@material-ui/core/Button'
import AccountCircleIcon from '@material-ui/icons/AccountCircle'
import { useHistory } from 'react-router-dom'
import {
  ACCOUNT_CHARACTERS_PATH,
  ACCOUNT_GUILDS_PATH,
  HOME_PATH,
} from '../routeur/MainRouteur'
import HomeIcon from '@material-ui/icons/Home'
import GroupIcon from '@material-ui/icons/Group'

const useStyles = makeStyles(() =>
  createStyles({
    root: {
      flexGrow: 1,
    },
    menuButton: {},
    title: {
      flexGrow: 1,
    },
    appBar: {
      flexGrow: 1,
    },
  }),
)

export default function ButtonAppBar(): JSX.Element {
  const classes = useStyles()
  const history = useHistory()

  const onClickCharacters = useCallback(() => {
    history.push(ACCOUNT_CHARACTERS_PATH)
  }, [history])

  const onClickGuilds = useCallback(() => {
    history.push(ACCOUNT_GUILDS_PATH)
  }, [history])

  const onClickHome = useCallback(() => {
    history.push(HOME_PATH)
  }, [history])

  return (
    <AppBar position="static" className={classes.appBar}>
      <Toolbar variant="dense">
        <div>
          <Button
            color="inherit"
            onClick={onClickHome}
            startIcon={<HomeIcon />}
          >
            Home
          </Button>
          <Button
            color="inherit"
            onClick={onClickCharacters}
            startIcon={<AccountCircleIcon />}
          >
            Characters
          </Button>
          <Button
            color="inherit"
            onClick={onClickGuilds}
            startIcon={<GroupIcon />}
          >
            Guilds
          </Button>
        </div>
      </Toolbar>
    </AppBar>
  )
}
