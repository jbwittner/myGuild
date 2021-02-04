import React from 'react'
import { HashRouter, Route, Switch } from 'react-router-dom'
import LoginPage from '../login/LoginPage'
import TestDiv from '../login/TestDiv'

export default function MainRouteur() {
  return (
    <HashRouter hashType={'noslash'}>
      <Switch>
        <Route exact path="/" component={LoginPage} />
        <Route exact path="/test" component={TestDiv} />
      </Switch>
    </HashRouter>
  )
}
