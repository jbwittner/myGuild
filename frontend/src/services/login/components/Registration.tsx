import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Grid } from "@material-ui/core";
import { AccountCircle, Email } from "@material-ui/icons";
import React from "react";
import { useForm, ValidationRules } from "react-hook-form";
import { AddUserParameter } from "../../../api/Entities";
import { UserHttpClient } from "../../../api/clients/UserHttpClient";
import TextFieldsWithIcone from "../../common/TextFieldWithIcone";


export interface RegistrationProps {

    onSubmit?: any,
    onCancel?: any,
    open: boolean

}

type RegistrationRules = {
    nickName: ValidationRules;
    email: ValidationRules;
}

type FormInputs = {
    nickName: string
    email: string
  }

export default function Registration (props: RegistrationProps) {

    const { register, handleSubmit, errors } = useForm<FormInputs>();

    const onSubmit = async (data: FormInputs) => {
        console.log(data)
        const userHttpClient = new UserHttpClient();

        const parameter: AddUserParameter = {
            nickName: data.nickName,
            email: data.email
        }

        try{
            await userHttpClient.addUserAccount(parameter);
            props.onSubmit();
        } catch(e){
            console.log(e)
        }

    };

    const validationRules: RegistrationRules = {
        nickName: {
            required: true
        },

        email: {
            required: true,
            pattern: /^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$/i
        }
    }

    return(
        <Dialog open={props.open} aria-labelledby="form-dialog-title">
            <form onSubmit={handleSubmit(onSubmit)}>
                <DialogTitle id="form-dialog-title">Registration</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            It&apos;s your first connection, please to renseign this informations
                        </DialogContentText>
                        <Grid container direction="column" spacing={2}>
                            <Grid item>
                                <TextFieldsWithIcone
                                    variant="outlined"
                                    name="nickName"
                                    label="Nick name"
                                    inputRef={register(validationRules.nickName)}
                                    error={errors.nickName !== undefined}
                                    icone={<AccountCircle/>} />
                            </Grid>
                            <Grid item>
                                <TextFieldsWithIcone
                                    variant="outlined"
                                    name="email"
                                    label="Email"
                                    inputRef={register(validationRules.email)}
                                    error={errors.email !== undefined}
                                    icone={<Email/>} />
                            </Grid>
                        </Grid>
                    </DialogContent>
                <DialogActions>
                <Button color="primary" type="submit">
                    Valid
                </Button>
                <Button color="primary" onClick={props.onCancel}>
                    Cancel
                </Button>
                </DialogActions>
            </form>
        </Dialog>
    )

}