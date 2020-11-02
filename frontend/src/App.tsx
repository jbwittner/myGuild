import { ThemeProvider } from '@material-ui/core'
import React, { useState } from 'react'
import './App.css'
import { GeneralContext } from './services/common/Context'
import { theme } from './services/common/Theme'
import MainRouteur from './services/routeur/MainRouteur'

function App() {

    const [isSignedIn, setIsSignedIn] = useState(false)

    return (
        <div className="App">
            <header className="App-header">

                <ThemeProvider theme={theme}>
                    <GeneralContext.Provider value={{
                        isSignedIn, setIsSignedIn
                    }}>
                        <MainRouteur/>
                    </GeneralContext.Provider>
                </ThemeProvider>
                                
            </header>
        </div>
    )
}

export default App
