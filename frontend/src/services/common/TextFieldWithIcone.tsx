import { Grid, TextField, TextFieldProps } from '@material-ui/core';
import React from 'react'


export type TextFieldsWithIconeProps = TextFieldProps & {
    icone: any
};


export default function TextFieldsWithIcone (props: TextFieldsWithIconeProps) {

    return(
        <Grid container alignItems="center">
          <Grid item xs={1}>
            {props.icone}
          </Grid>
          <Grid item xs={11}>
            <TextField {...props} fullWidth/>
          </Grid>
        </Grid>
    )
    
}