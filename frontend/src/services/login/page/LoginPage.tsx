import { AppBar, Box, createStyles, Grid, makeStyles, Paper, Tab, Tabs, Typography } from '@material-ui/core';
import React from 'react';
import Login from '../components/Login';
import SwipeableViews from 'react-swipeable-views';
import { theme } from '../../common/Theme';

const useStyle = makeStyles(() =>
    createStyles({
        background: {
            height: '100%',
            minHeight: '150px',
            backgroundImage: 'url(../1049851.jpg)',
            backgroundPosition: 'center top',
            backgroundSize: 'cover',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center'

        },
        paper:{
            width: '300px',
            backgroundColor: '#242424',
        },
        logo: {

        },
        root: {
            height: '100vh'
        },
        box:{
            height: '100%'
        },
        appbar: {
        },
        tabs: {
            height: '100%'
        },
        tab: {
            height: '50px'
        },
        swipeableViews: {
            height: 'calc(100vh - 50px)'
        }
    })
)

interface TabPanelProps {
    children?: React.ReactNode;
    dir?: string;
    index: any;
    value: any;
  }

function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    const classes = useStyle();
  
    return (
      <Box
        className = {classes.box}
        role="tabpanel"
        hidden={value !== index}
        id={`full-width-tabpanel-${index}`}
        aria-labelledby={`full-width-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box p={0} className = {classes.box}>
            <Typography className = {classes.box}>{children}</Typography>
          </Box>
        )}
      </Box>
    );
  }
  

function a11yProps(index: any) {
    return {
      id: `full-width-tab-${index}`,
      'aria-controls': `full-width-tabpanel-${index}`,
    };
  }

export default function LoginPage() {

    const classes = useStyle();

    const [value, setValue] = React.useState(0);

    const handleChange = (event: React.ChangeEvent<{}>, newValue: number) => {
        setValue(newValue);
      };

      const handleChangeIndex = (index: number) => {
        setValue(index);
      };

    return (
        <React.Fragment>
            <div className={classes.root}>
            <AppBar position="static" color="default" className={classes.appbar}>
                <Tabs
                className={classes.tabs}
                value={value}
                onChange={handleChange}
                indicatorColor="primary"
                textColor="primary"
                variant="fullWidth"
                aria-label="full width tabs example"
                >
                    <Tab className={classes.tab} label="Login" {...a11yProps(0)}/>
                    <Tab className={classes.tab} label="About" {...a11yProps(1)}/>
                </Tabs>
            </AppBar>
                    <SwipeableViews
                axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
                index={value}
                onChangeIndex={handleChangeIndex}
                className={classes.swipeableViews}
                containerStyle={{height:'100%'}}
            >
                <TabPanel value={value} index={0} dir={theme.direction}>
                                <Box className={classes.background}>
                                    <Paper variant="outlined" elevation={3} className={classes.paper}>
                                        <Grid container direction="column" justify="center" alignItems="center">
                                            <Grid item>
                                                <img src={'../logo192.png'} alt="logos" className={classes.logo}/>
                                            </Grid>
                                            <Grid item>
                                                <Login />
                                            </Grid>
                                        </Grid>
                                    </Paper>
                                </Box>
                </TabPanel>
                <TabPanel value={value} index={1} dir={theme.direction}>
                Item Two
                </TabPanel>
                <TabPanel value={value} index={2} dir={theme.direction}>
                Item Three
                </TabPanel>
            </SwipeableViews>

            </div>

        </React.Fragment>
    )

}