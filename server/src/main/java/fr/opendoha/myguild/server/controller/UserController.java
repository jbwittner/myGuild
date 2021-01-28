package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.UserAccountDTO;
import fr.opendoha.myguild.server.exception.UserBattleTagAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserBlizzardIdAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.opendoha.myguild.server.parameters.FetchingUserAccountParameter;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;
import fr.opendoha.myguild.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller used to manage users
 */
@RestController
@RequestMapping("user")
public class UserController extends MotherController {

    protected final UserService userService;

    /**
     * Constructor
     */
    @Autowired
    public UserController(final UserService userService) {
        super();
        this.userService = userService;
    }

    /**
     * Endpoint used to add a new account
     */
    @PostMapping("/addAccount")
    public void addingAccount(final OAuth2AuthenticationToken authentication, @RequestBody final UserRegistrationParameter parameter)
            throws UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException, UserBattleTagAlreadyUsedException,
            UserBlizzardIdAlreadyUsedException {

        final String battleTag = this.getBattleTag(authentication);
        final Integer blizzardId = this.getBlizzardId(authentication);

        parameter.setBattleTag(battleTag);
        parameter.setBlizzardId(blizzardId);

        this.userService.registerNewUserAccount(parameter);
    }

    /**
     * Endpoint used to check if the account exist
     */
    @GetMapping("/isAccountExist")
    public Boolean isAccountExist(final OAuth2AuthenticationToken authentication) {

        final Integer blizzardId = this.getBlizzardId(authentication);

        return this.userService.checkUserAccountAlreadyExist(blizzardId);
    }

    /**
     * Endpoint used to get the account information
     */
    @GetMapping("/fetchAccountInfo")
    public UserAccountDTO fetchAccountInfo(final OAuth2AuthenticationToken authentication) {

        final Integer blizzardId = this.getBlizzardId(authentication);
        final String battleTag = this.getBattleTag(authentication);

        final FetchingUserAccountParameter fetchingUserAccountParameter = new FetchingUserAccountParameter();
        fetchingUserAccountParameter.setBattleTag(battleTag);
        fetchingUserAccountParameter.setBlizzardId(blizzardId);

        return this.userService.fetchAccountInfo(fetchingUserAccountParameter);
    }

}
