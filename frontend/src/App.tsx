import { ThemeProvider } from '@material-ui/core'
import React, { useState } from 'react'
import './App.css'
import ButtonAppBar from './services/common/AppBar'
import { GeneralContext } from './services/common/Context'
import { theme } from './services/common/Theme'
import MainRouteur from './services/routeur/MainRouteur'

function App() {

    const [isSignedIn, setIsSignedIn] = useState(false)

    return (
        <ThemeProvider theme={theme}>
            <GeneralContext.Provider value={{
                    isSignedIn, setIsSignedIn
                }}>
                <GeneralContext.Consumer>
                    {() => (
                        <React.Fragment>
                            <ButtonAppBar/>
                            <MainRouteur/>
                        </React.Fragment>
                        )}
                </GeneralContext.Consumer>
            </GeneralContext.Provider>
        </ThemeProvider>             
    )
}

export default App
