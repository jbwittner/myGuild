import { ThemeProvider } from '@material-ui/core'
import React from 'react'
import './App.css'
import { theme } from './services/common/Theme'
import Login from './services/login/Login'

function App() {

    return (
        <div className="App">
            <header className="App-header">

                <ThemeProvider theme={theme}>
                    <Login/>
                </ThemeProvider>
                                
            </header>
        </div>
    )
}

export default App
