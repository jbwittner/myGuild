import React from 'react';
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import { useLocation } from 'react-router-dom';
import { GeneralContext } from '../common/Context';
import TemporaryDrawer from './DrawerMenu';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      flexGrow: 1,
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1
    },
    appbar:{

    }
  }),
);

export default function MainAppBar() {
  const classes = useStyles();

  const {isSignedIn} = React.useContext(GeneralContext);

  const location = useLocation();

  return (
    <React.Fragment>
        {
          isSignedIn && location.pathname !== '/' &&
            <AppBar position="static" className={classes.appbar}>
              <Toolbar>
                <TemporaryDrawer />
                {location.pathname}
            </Toolbar>
          </AppBar>
        }
    </React.Fragment>
    
  );
}
