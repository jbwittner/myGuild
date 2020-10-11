package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.UserAccountDTO;
import fr.opendoha.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserBattleTagAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserBlizzardIdAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserAccountNotExistedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implementation of the user service interface
 */
@Service
public class UserService implements IUserService {

    private final UserAccountRepository userAccountRepository;

    /**
     * Constructor of the user service
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
    public void registerNewUserAccount(final UserRegistrationParameter userRegistrationParameter) throws
            UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException {

        this.checkEmailAndNickNameUser(userRegistrationParameter);

        final UserAccount user = new UserAccount();

        user.setEmail(userRegistrationParameter.getEmail());
        user.setNickName(userRegistrationParameter.getNickName());
        user.setBattleTag(userRegistrationParameter.getBattleTag());
        user.setBlizzardId(userRegistrationParameter.getBlizzardId());
        user.setEnabled(true);

        this.userAccountRepository.save(user);
    }

    /**
     * Method used to check if the email and the nick name are already used
     *
     * @param userRegistrationParameter Parameter of the new account
     * @throws UserEmailAlreadyUsedException    if the email is already used
     * @throws UserNickNameAlreadyUsedException if the nick name is already used
     */
    private void checkEmailAndNickNameUser(final UserRegistrationParameter userRegistrationParameter) throws
            UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException {

        if (this.userAccountRepository.existsByEmail(userRegistrationParameter.getEmail())) {
            throw new UserEmailAlreadyUsedException(userRegistrationParameter);
        } else if (this.userAccountRepository.existsByNickName(userRegistrationParameter.getNickName())) {
            throw new UserNickNameAlreadyUsedException(userRegistrationParameter);
        } else if (this.userAccountRepository.existsByBattleTag(userRegistrationParameter.getBattleTag())) {
            throw new UserBattleTagAlreadyUsedException(userRegistrationParameter);
        } else if (this.userAccountRepository.existsByBlizzardId(userRegistrationParameter.getBlizzardId())) {
            throw new UserBlizzardIdAlreadyUsedException(userRegistrationParameter);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean checkUserAccountAlreadyExist(final Integer blizzardId) {
        return this.userAccountRepository.existsByBlizzardId(blizzardId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAccountDTO getAccountInfo(final Integer blizzardId) throws UserAccountNotExistedException {

        if(!this.userAccountRepository.existsByBlizzardId(blizzardId)){
            throw new UserAccountNotExistedException(blizzardId);
        }

        final UserAccount userAccount = this.userAccountRepository.findByBlizzardId(blizzardId);

        final UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.builderDTO(userAccount);
        return userAccountDTO;
    }
}