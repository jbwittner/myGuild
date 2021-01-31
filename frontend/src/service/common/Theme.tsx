import { createMuiTheme } from '@material-ui/core'

export const theme = createMuiTheme({
  palette: {
    type: window.matchMedia('prefers-color-scheme: dark').matches
      ? 'dark'
      : 'light',
    primary: {
      light: '#8d8d8d',
      main: '#606060',
      dark: '#363636',
      contrastText: '#ffffff',
    },
    secondary: {
      light: '#c1d5e0',
      main: '#90a4ae',
      dark: '#62757f',
      contrastText: '#ffffff',
    },
  },
})
