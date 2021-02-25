import React from 'react'
import { HashRouter, Route, Switch } from 'react-router-dom'
import AccountCharactersPage from '../accountcharacters/AccountCharactersPage'
import AccountGuildsPage from '../accountguilds/AccountGuildsPage'
import ButtonAppBar from '../common/AppBar'
import { GeneralContext } from '../common/Context'
import HomePage from '../home/HomePage'
import LoginPage from '../login/LoginPage'
import PrivateRoute from './PrivateRouter'

export const INDEX_PATH = '/'
export const HOME_PATH = '/home'
export const ACCOUNT_CHARACTERS_PATH = '/accountcharacters'
export const ACCOUNT_GUILDS_PATH = '/accountguilds'

export default function MainRouteur(): JSX.Element {
  const { isSignedIn } = React.useContext(GeneralContext)

  return (
    <HashRouter hashType={'noslash'}>
      {isSignedIn && <ButtonAppBar />}
      <Switch>
        <Route exact path={INDEX_PATH} component={LoginPage} />
        <PrivateRoute exact path={HOME_PATH} component={HomePage} />
        <PrivateRoute
          exact
          path={ACCOUNT_CHARACTERS_PATH}
          component={AccountCharactersPage}
        />
        <PrivateRoute
          exact
          path={ACCOUNT_GUILDS_PATH}
          component={AccountGuildsPage}
        />
      </Switch>
    </HashRouter>
  )
}
