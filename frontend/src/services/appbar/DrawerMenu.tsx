import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import MenuIcon from '@material-ui/icons/Menu';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { IconButton } from '@material-ui/core';
import { useHistory } from 'react-router-dom';
import PersonIcon from '@material-ui/icons/Person';
import HomeIcon from '@material-ui/icons/Home';

const useStyles = makeStyles({
  list: {
    width: 250,
  },
  fullList: {
    width: 'auto',
  },
});

type Anchor = 'top' | 'left' | 'bottom' | 'right';

export default function TemporaryDrawer() {
  const classes = useStyles();
  const [state, setState] = React.useState({
    top: false,
    left: false,
    bottom: false,
    right: false,
  });

  const history = useHistory();

  const toggleDrawer = (anchor: Anchor, open: boolean) => (
    event: React.KeyboardEvent | React.MouseEvent,
  ) => {
    if (
      event.type === 'keydown' &&
      ((event as React.KeyboardEvent).key === 'Tab' ||
        (event as React.KeyboardEvent).key === 'Shift')
    ) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor: Anchor) => (
    <div
      className={clsx(classes.list, {
        [classes.fullList]: anchor === 'top' || anchor === 'bottom',
      })}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
      <ListItem button key={"home"} onClick={() => history.push("/home")}>
            <ListItemIcon><HomeIcon /></ListItemIcon>
            <ListItemText primary={'Home'} />
          </ListItem>
        <ListItem button key={"charactersPage"} onClick={() => history.push("/charactersPage")}>
            <ListItemIcon><PersonIcon /></ListItemIcon>
            <ListItemText primary={'Characters'} />
          </ListItem>
      </List>
      <Divider />
      <List>
      <ListItem button key={"home"} onClick={() => history.push("/home")}>
            <ListItemIcon><HomeIcon /></ListItemIcon>
            <ListItemText primary={'Home'} />
          </ListItem>
        <ListItem button key={"charactersPage"} onClick={() => history.push("/charactersPage")}>
            <ListItemIcon><PersonIcon /></ListItemIcon>
            <ListItemText primary={'Characters'} />
          </ListItem>
      </List>
    </div>
  );

  return (
    <React.Fragment key={'left'}>
        <IconButton edge="start" color="inherit" aria-label="menu" onClick={toggleDrawer('left', true)}>
            <MenuIcon />
        </IconButton>
        <Drawer anchor={'left'} open={state['left']} onClose={toggleDrawer('left', false)}>
        {list('left')}
        </Drawer>
    </React.Fragment>
  );
}
