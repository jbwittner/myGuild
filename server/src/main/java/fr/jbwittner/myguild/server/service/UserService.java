package fr.jbwittner.myguild.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbwittner.myguild.server.exception.UserBattleTagAlreadyUsedException;
import fr.jbwittner.myguild.server.exception.UserBlizzardIdAlreadyUsedException;
import fr.jbwittner.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.jbwittner.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.jbwittner.myguild.server.model.UserAccount;
import fr.jbwittner.myguild.server.parameters.UserRegistrationParameter;
import fr.jbwittner.myguild.server.repository.UserAccountRepository;

/**
 * Implementation of the user service interface
 */
@Service
public class UserService implements IUserService {
    
    private UserAccountRepository userAccountRepository;

    /**
     * Constructor of the user service
     * @param userAccountRepository user account respository
     * @param passwordEncoder password encoder used to encrypt passwords
     */
    @Autowired
    public UserService(final UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public UserAccount registerNewUserAccount(final UserRegistrationParameter userRegistrationParameter) throws
        UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException {

        this.checkEmailAndNickNameUser(userRegistrationParameter);

        final UserAccount user = new UserAccount();
        
        user.setEmail(userRegistrationParameter.getEmail());
        user.setNickName(userRegistrationParameter.getNickName());
        user.setBattleTag(userRegistrationParameter.getBattletag());
        user.setBlizzardId(userRegistrationParameter.getBlizzardId());
        user.setEnabled(true);
        
        final UserAccount result = this.userAccountRepository.save(user);
        
        return result;
    }

    /**
     * Method used to check if the email and the nick name are already used
     * @param userRegistrationParameter Parameter of the new account
     * @throws UserEmailAlreadyUsedException if the email is already used
     * @throws UserNickNameAlreadyUsedException if the nick name is already used
     */
    private void checkEmailAndNickNameUser(final UserRegistrationParameter userRegistrationParameter) throws
        UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException {

        if(this.userAccountRepository.existsByEmail(userRegistrationParameter.getEmail())){
            throw new UserEmailAlreadyUsedException(userRegistrationParameter);
        }else if(this.userAccountRepository.existsByNickName(userRegistrationParameter.getNickName())){
            throw new UserNickNameAlreadyUsedException(userRegistrationParameter);
        }else if(this.userAccountRepository.existsByBattleTag(userRegistrationParameter.getBattletag())){
            throw new UserBattleTagAlreadyUsedException(userRegistrationParameter);
        }else if(this.userAccountRepository.existsByBlizzardId(userRegistrationParameter.getBlizzardId())){
            throw new UserBlizzardIdAlreadyUsedException(userRegistrationParameter);
        }

    }
}