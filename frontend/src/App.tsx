import { CssBaseline, ThemeProvider } from '@material-ui/core'
import React, { useState } from 'react'
import { GeneralContext } from './service/common/Context'
import getTheme from './service/common/Theme'
import MainRouteur from './service/routeur/MainRouteur'

function App(): JSX.Element {
  const [isSignedIn, setIsSignedIn] = useState(false)

  const theme = getTheme({ darkMode: true })

  return (
    <div className="App">
      <header className="App-header">
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <GeneralContext.Provider
            value={{
              isSignedIn,
              setIsSignedIn,
            }}
          >
            <GeneralContext.Consumer>
              {() => (
                <React.Fragment>
                  <MainRouteur />
                </React.Fragment>
              )}
            </GeneralContext.Consumer>
          </GeneralContext.Provider>
        </ThemeProvider>
      </header>
    </div>
  )
}

export default App
