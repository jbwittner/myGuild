import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from '@material-ui/core'
import React, { useCallback, useState } from 'react'
import { Controller, useForm } from 'react-hook-form'
import { AddUserParameter, BackendError } from '../../../api/Entities'
import { UserHttpClient } from '../../../api/httpclient/UserHttpClient'
import CustomizedSnackbar from '../../common/CustomizedSnackbar'

export interface RegistrationProps {
  open: boolean
  handleClose: (registration: boolean) => void
}

interface RegistrationForm {
  email: string
  nickName: string
}

export default function Registration(props: RegistrationProps): JSX.Element {
  const emailValidation = /^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$/i

  const [errorRegistration, setErrorRegistration] = useState<boolean>(false)
  const [errorMessage, setErrorMessage] = useState<string>('')
  const { control, handleSubmit, errors } = useForm<RegistrationForm>()

  const onCloseSnackBar = useCallback(() => {
    setErrorRegistration(false)
  }, [])

  const onSubmit = (data: RegistrationForm) => {
    const addUserParameter: AddUserParameter = {
      nickName: data.nickName,
      email: data.email,
    }
    const userHttpClient = new UserHttpClient()

    userHttpClient
      .addUserAccount(addUserParameter)
      .then(() => {
        props.handleClose(true)
      })
      .catch((error) => {
        const data: BackendError = error.data
        setErrorRegistration(true)
        setErrorMessage(data.message)
      })
  }

  return (
    <React.Fragment>
      <Dialog
        open={props.open}
        onClose={() => {
          props.handleClose(false)
        }}
        aria-labelledby="form-dialog-title"
      >
        <form onSubmit={handleSubmit((data) => onSubmit(data))}>
          <DialogTitle id="form-dialog-title">Subscribe</DialogTitle>
          <DialogContent>
            <DialogContentText>
              To subscribe to this application, please enter your nick name and
              your email address here
            </DialogContentText>

            <Controller
              as={
                <TextField
                  margin="dense"
                  label={'Nick Name*'}
                  fullWidth
                  error={errors.nickName !== undefined}
                />
              }
              name="nickName"
              control={control}
              type="text"
              defaultValue=""
              rules={{ required: true }}
            />

            <Controller
              as={
                <TextField
                  margin="dense"
                  label={'Email Address*'}
                  fullWidth
                  error={errors.email !== undefined}
                />
              }
              name="email"
              control={control}
              type="text"
              defaultValue=""
              rules={{ required: true, pattern: emailValidation }}
            />
          </DialogContent>

          <DialogActions>
            <Button
              onClick={() => {
                props.handleClose(false)
              }}
              color="primary"
            >
              Cancel
            </Button>

            <Button type="submit" color="primary">
              Subscribe
            </Button>
          </DialogActions>
        </form>
      </Dialog>
      <CustomizedSnackbar
        open={errorRegistration}
        onClose={onCloseSnackBar}
        severity="error"
      >
        {errorMessage}
      </CustomizedSnackbar>
    </React.Fragment>
  )
}
