package fr.opendoha.myguild.server.service;


import fr.opendoha.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Interface of the user service
 */
public interface IUserService {

    /**
     * Method to register a new user account
     * @param userRegistrationParameter Parameters of the new account
     * @return user account of the user
     * @throws UserEmailAlreadyUsedException if the email is already used
     * @throws UserNickNameAlreadyUsedException if the nick name is already used
     */
    UserAccount registerNewUserAccount(UserRegistrationParameter userRegistrationParameter) throws 
        UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException;

}
