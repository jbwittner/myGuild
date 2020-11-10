import { createMuiTheme } from "@material-ui/core";

export const theme = createMuiTheme({
    palette: {
        type: window.matchMedia('prefers-color-scheme: dark').matches ? 'dark' : 'light',
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

export class Faction{
  public static HORDE: FactionConfiguration = {
      type: "HORDE",
      backgroundColor: "rgba(255, 0, 0, 0.5)"
  };

  public static ALIANCE: FactionConfiguration = {
      type: "ALIANCE",
      backgroundColor: "rgba(0, 27, 255, 0.5)"
  };

  public static NEUTRAL: FactionConfiguration ={
      type: "NEUTRAL",
      backgroundColor: "rgba(192, 192, 192, 0.5)"
  };
}

export interface FactionConfiguration{
  type: string;
  backgroundColor: string;
}