import React, { useCallback } from 'react'
import Snackbar from '@material-ui/core/Snackbar'
import MuiAlert, { AlertProps } from '@material-ui/lab/Alert'
import { makeStyles } from '@material-ui/core/styles'

function Alert(props: AlertProps) {
  return <MuiAlert elevation={6} variant="filled" {...props} />
}

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    '& > * + *': {
      marginTop: theme.spacing(2),
    },
  },
}))

export interface CustomizedSnackbarProps {
  open: boolean
  severity: 'success' | 'info' | 'warning' | 'error'
  vertical?: 'top' | 'bottom'
  horizontal?: 'center' | 'right' | 'left'
  children?: string
  onClose: () => void
}

export default function CustomizedSnackbar(
  props: CustomizedSnackbarProps,
): JSX.Element {
  const classes = useStyles()

  const handleClose = useCallback(() => {
    props.onClose()
  }, [props.onClose])

  const vertical = props.vertical === undefined ? 'top' : props.vertical
  const horizontal = props.horizontal === undefined ? 'right' : props.horizontal

  return (
    <div className={classes.root}>
      <Snackbar
        open={props.open}
        autoHideDuration={6000}
        onClose={handleClose}
        anchorOrigin={{ vertical, horizontal }}
      >
        <Alert onClose={handleClose} severity={props.severity}>
          {props.children}
        </Alert>
      </Snackbar>
    </div>
  )
}
