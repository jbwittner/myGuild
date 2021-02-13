import { Box, makeStyles } from '@material-ui/core'
import React, { useCallback, useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom'
import { StaticDataDTO, CharacterSummaryDTO } from '../../api/Entities'
import { BlizzardHttpClient } from '../../api/httpclient/BlizzardHttpClient'
import { CharacterHttpClient } from '../../api/httpclient/CharacterHttpClient'
import { SecurityHttpClient } from '../../api/httpclient/SecurityHttpClient'
import { UserHttpClient } from '../../api/httpclient/UserHttpClient'
import CircularProgressScreen from '../common/CircularProgressScreenProps'
import { GeneralContext } from '../common/Context'
import { HOME_PATH } from '../routeur/MainRouteur'
import { SessionStorage } from '../storage/SessionStorage'
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
  const [downloadInProgress, setDownloadInProgress] = useState<boolean>(false)

  useEffect(() => {
    const securityHttpClient = new SecurityHttpClient()
    const userHttpClient = new UserHttpClient()
    const blizzardHttpClient = new BlizzardHttpClient()
    const characterHttpClient = new CharacterHttpClient()

    securityHttpClient
      .connectionTest()
      .then(() => {
        return userHttpClient.isAccountExist()
      })
      .then((value) => {
        if (value === true) {
          setDownloadInProgress(true)
          blizzardHttpClient
            .getStaticData()
            .then((staticData) => {
              SessionStorage.setItem<StaticDataDTO>(
                SessionStorage.STATIC_DATA,
                staticData,
              )
              return characterHttpClient.fetchAccountCharacter()
            })
            .then((characterSummaryDTOs: CharacterSummaryDTO[]) => {
              SessionStorage.setItem<CharacterSummaryDTO[]>(
                SessionStorage.CHARACTERS_DATA,
                characterSummaryDTOs,
              )
              setIsSignedIn(true)
              history.push(HOME_PATH)
            })
            .finally(() => {
              setDownloadInProgress(false)
            })
        } else {
          setShowRegistration(true)
        }
      })
      .catch((reason) => {
        setIsSignedIn(false)
        console.log('login process reason : start')
        console.error(reason)
        console.log('login process reason : end')
      })
  }, [])

  const onCloseRegistration = useCallback((registration: boolean) => {
    setShowRegistration(false)
    setIsSignedIn(registration)

    if (registration === true) {
      const blizzardHttpClient = new BlizzardHttpClient()
      const characterHttpClient = new CharacterHttpClient()

      setDownloadInProgress(true)

      blizzardHttpClient
        .getStaticData()
        .then((staticData: StaticDataDTO) => {
          SessionStorage.setItem<StaticDataDTO>(
            SessionStorage.STATIC_DATA,
            staticData,
          )
          return characterHttpClient.fetchAccountCharacter()
        })
        .then((characterSummaryDTOs: CharacterSummaryDTO[]) => {
          SessionStorage.setItem<CharacterSummaryDTO[]>(
            SessionStorage.CHARACTERS_DATA,
            characterSummaryDTOs,
          )
          history.push(HOME_PATH)
        })
        .finally(() => {
          setDownloadInProgress(false)
        })
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
      <CircularProgressScreen open={downloadInProgress} />
    </React.Fragment>
  )
}
