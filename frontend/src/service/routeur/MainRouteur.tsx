import React from 'react'
import { HashRouter, Route, Switch } from 'react-router-dom'
import CharactersPage from '../character/CharactersPage'
import ButtonAppBar from '../common/AppBar'
import { GeneralContext } from '../common/Context'
import HomePage from '../home/HomePage'
import LoginPage from '../login/LoginPage'
import PrivateRoute from './PrivateRouter'

export const INDEX_PATH = '/'
export const HOME_PATH = '/home'
export const CHARACTERS_PATH = '/characters'

export default function MainRouteur(): JSX.Element {
  const { isSignedIn } = React.useContext(GeneralContext)

  return (
    <HashRouter hashType={'noslash'}>
      {isSignedIn && <ButtonAppBar />}
      <Switch>
        <Route exact path={INDEX_PATH} component={LoginPage} />
        <PrivateRoute exact path={HOME_PATH} component={HomePage} />
        <PrivateRoute exact path={CHARACTERS_PATH} component={CharactersPage} />
      </Switch>
    </HashRouter>
  )
}
