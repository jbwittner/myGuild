import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import { GeneralContext } from '../common/Context';
import LoginPage from '../login/page/LoginPage';
import PrivateRoute from './PrivateRouteur';

export default function MainRouteur() {

    return (
        <React.Fragment>
            <GeneralContext.Consumer>
                {(generalContext) => (
                    <BrowserRouter>
                        <Route exact path="/" component={LoginPage}/>
                        <PrivateRoute exact path="/test" component={LoginPage} isSignedIn={generalContext.isSignedIn}/>
                    </BrowserRouter>
                )}
            </GeneralContext.Consumer>
        </React.Fragment>
    )

}