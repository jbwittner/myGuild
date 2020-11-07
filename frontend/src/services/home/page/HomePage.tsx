import { Button } from '@material-ui/core';
import React from 'react'
import { useHistory } from 'react-router-dom';

export default function HomePage() {

    const history = useHistory();

    return(
    <Button variant="contained" onClick={() => history.push("/charactersPage")}>{'kdlsmjgfqslkfj'}</Button>
    )

}