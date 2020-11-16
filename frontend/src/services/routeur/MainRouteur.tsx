import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import MainAppBar from '../appbar/AppBar';
import CharactersPage from '../characters/page/CharactersPage';
import GuildsIndexPage from '../guildsIndex/page/GuildsIndexPage';
import HomePage from '../home/page/HomePage';
import LoginPage from '../login/page/LoginPage';
import PrivateRoute from './PrivateRouteur';

export default function MainRouteur() {

    return (
        <BrowserRouter>
            <MainAppBar/>
            <Route exact path="/" component={LoginPage}/>
            <PrivateRoute exact path="/home" component={HomePage}/>
            <PrivateRoute exact path="/characters" component={CharactersPage}/>
            <PrivateRoute exact path="/guildsIndex" component={GuildsIndexPage}/>
        </BrowserRouter>
    )

}