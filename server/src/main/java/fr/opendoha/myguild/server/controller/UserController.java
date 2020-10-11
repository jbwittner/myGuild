package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.UserAccountDTO;
import fr.opendoha.myguild.server.exception.UserAccountNotExistedException;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;
import fr.opendoha.myguild.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/addAccount")
    public void addingAccount(final OAuth2AuthenticationToken authentication,
                              final UserRegistrationParameter parameter) {

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
    @GetMapping("/getAccountInfo")
    public UserAccountDTO getAccountInfo(final OAuth2AuthenticationToken authentication)
            throws UserAccountNotExistedException {

        final Integer blizzardId = this.getBlizzardId(authentication);

        return this.userService.getAccountInfo(blizzardId);
    }

}
