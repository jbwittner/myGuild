import React from 'react'
import { Link } from 'react-router-dom'
import logo from './../../logo.svg'

export default function LoginPage() {
  return (
    <React.Fragment>
      <img src={logo} className="App-logo" alt="logo" />
      <p>
        Edit
        <code> src/App.tsx </code>
        and save to reload.
      </p>
      <Link to="/test">go to test</Link>
      <a
        className="App-link"
        href="https://reactjs.org"
        target="_blank"
        rel="noopener noreferrer"
      >
        Learn React
      </a>
    </React.Fragment>
  )
}
