import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import LoginPage from '../login/page/LoginPage';
import PrivateRoute from './PrivateRouteur';

export default function MainRouteur() {

    return (
        <BrowserRouter>
            <Route exact path="/" component={LoginPage}/>
            <PrivateRoute exact path="/test" component={LoginPage}/>
        </BrowserRouter>
    )

}