import React from 'react'
import { HashRouter, Route, Switch } from 'react-router-dom'
import HomePage from '../home/HomePage'
import LoginPage from '../login/LoginPage'
import PrivateRoute from './PrivateRouter'

export const INDEX_PATH = '/'
export const HOME_PATH = '/home'

export default function MainRouteur(): JSX.Element {
  return (
    <HashRouter hashType={'noslash'}>
      <Switch>
        <Route exact path={INDEX_PATH} component={LoginPage} />
        <PrivateRoute exact path={HOME_PATH} component={HomePage} />
      </Switch>
    </HashRouter>
  )
}
