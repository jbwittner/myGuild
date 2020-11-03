import { Checkbox, CheckboxProps, withStyles } from '@material-ui/core';
import React from 'react'

export interface ColoredCheckBoxProps extends CheckboxProps{
    colorUnchecked: any,
    colorChecked: any
}

export default function ColoredCheckBox(props: ColoredCheckBoxProps) {

    const ColoredCheckBox = withStyles({
        root: {
          color: props.colorUnchecked,
          '&$checked': {
            color: props.colorChecked,
          },
        },
        checked: {},
      })((props: CheckboxProps) => <Checkbox color="default" {...props} />);
    
    
    return (
        <ColoredCheckBox {...props} />
    )
}