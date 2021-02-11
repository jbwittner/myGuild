import { grey, blue, cyan } from '@material-ui/core/colors'
import { createMuiTheme, Theme } from '@material-ui/core/styles'

export interface ThemeProps {
  darkMode: boolean
}

export default function getTheme({ darkMode = false }: ThemeProps): Theme {
  const themeDark = createMuiTheme({
    palette: {
      type: 'dark',
      primary: blue,
      secondary: cyan,
    },
    typography: {
      fontFamily: [
        '-apple-system',
        'BlinkMacSystemFont',
        '"Segoe UI"',
        'Roboto',
        '"Helvetica Neue"',
        'Arial',
        'sans-serif',
        '"Apple Color Emoji"',
        '"Segoe UI Emoji"',
        '"Segoe UI Symbol"',
        'Risque',
      ].join(','),
    },
  })

  const themeLight = createMuiTheme({
    palette: {
      type: 'light',
      primary: blue,
      secondary: grey,
    },
  })

  const theme = darkMode ? themeDark : themeLight

  return theme
}
