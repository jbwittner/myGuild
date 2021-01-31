import React from 'react'
import { BrowserRouter, Route } from 'react-router-dom'
import LoginPage from '../login/LoginPage'

export default function MainRouteur() {
  return (
    <BrowserRouter>
      <Route exact path="/" component={LoginPage} />
    </BrowserRouter>
  )
}
