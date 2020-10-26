import React from 'react'
import logo from './logo.svg'
import './App.css'
import Axios from 'axios'

export class toto {

    public async get(){
        return await Axios.get("/user/getToken")
    }

}

function App() {


    window.sessionStorage.setItem("key", "init");

    var toto = window.localStorage.getItem("connection");



    //Axios.get("/user/connectionTest")

    const test = () => {
        window.localStorage.setItem("connection", "isValid")
        localStorage.setItem("test", "true");
        console.log("tata")
        window.sessionStorage.setItem("key", "toto");

        var win: any;

        win = window.open('http://localhost:2142/oauth2/authorization/oauth-blizzard', 'SomeAuthentication', 'width=972,height=660,modal=yes,alwaysRaised=yes');

        var checkConnect = setInterval(function() {
            if (!win || !win.closed) return;
            win.close()
            clearInterval(checkConnect);
            window.location.reload();
        }, 100);

        //window.open('http://localhost:2142/oauth2/authorization/oauth-blizzard', 'SomeAuthentication', 'width=972,height=660,modal=yes,alwaysRaised=yes');
        //window.location.href = '/oauth2/authorization/oauth-blizzard';
    }

    const test2 = () => {
        parent.close()
        //localStorage.setItem("test", "true");
        //tata.get();
    }

    

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    Edit <code>src/App.tsx</code> and save to reload.
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
                <a href="/oauth2/authorization/oauth-blizzard">test</a>
                <button onClick={test}>TOTO</button>
                <button onClick={test2}>TOTO2</button>
                <p>
                    {"toto value : " + toto}
                </p>
            </header>
        </div>
    )
}

export default App
