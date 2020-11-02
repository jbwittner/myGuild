import React from 'react';
import { Route, Redirect, RouteProps } from 'react-router-dom';

interface PrivateRouteProps extends RouteProps {
    component: any;
    isSignedIn: boolean;
}

const PrivateRoute = (props: PrivateRouteProps) => {

    const {component: Component, isSignedIn, ...rest} = props;
    console.log("route : " + isSignedIn)

    return (

        // Show the component only when the user is logged in
        // Otherwise, redirect the user to /signin page
        <Route {...rest} render={props => (
            isSignedIn ? <Component {...props} /> : <Redirect to={{pathname: '/', state: {from: props.location}}} />
        )} />
    );
};

export default PrivateRoute;