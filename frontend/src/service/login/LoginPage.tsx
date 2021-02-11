import { Box, makeStyles } from '@material-ui/core'
import React, { useCallback, useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom'
import { SecurityHttpClient } from '../../api/httpclient/SecurityHttpClient'
import { UserHttpClient } from '../../api/httpclient/UserHttpClient'
import { GeneralContext } from '../common/Context'
import { HOME_PATH } from '../routeur/MainRouteur'
import LoginPaper from './componants/LoginPaper'
import Registration from './componants/Registration'

const useStyles = makeStyles({
  LoginPaper: {
    height: '100vh',
    width: '100%',
  },
})

export default function LoginPage(): JSX.Element {
  const classes = useStyles()

  const history = useHistory()
  const [showRegistration, setShowRegistration] = useState<boolean>(false)
  const { setIsSignedIn } = useContext(GeneralContext)

  useEffect(() => {
    const securityHttpClient = new SecurityHttpClient()
    const userHttpClient = new UserHttpClient()

    securityHttpClient
      .connectionTest()
      .then(() => {
        return userHttpClient.isAccountExist()
      })
      .then((value) => {
        if (value === true) {
          setIsSignedIn(true)
          history.push(HOME_PATH)
        } else {
          setShowRegistration(true)
        }
      })
      .catch((reason) => {
        setIsSignedIn(false)
        console.log(reason)
      })
  }, [])

  const onCloseRegistration = useCallback((registration: boolean) => {
    setShowRegistration(false)
    setIsSignedIn(registration)
    if (registration === true) {
      history.push(HOME_PATH)
    }
  }, [])

  return (
    <React.Fragment>
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        className={classes.LoginPaper}
      >
        <LoginPaper />
      </Box>
      <Registration open={showRegistration} handleClose={onCloseRegistration} />
    </React.Fragment>
  )
}
