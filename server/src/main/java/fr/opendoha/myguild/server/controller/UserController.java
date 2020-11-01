package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.TokenDTO;
import fr.opendoha.myguild.server.dto.UserAccountDTO;
import fr.opendoha.myguild.server.exception.FunctionalException;
import fr.opendoha.myguild.server.exception.UserAccountNotExistedException;
import fr.opendoha.myguild.server.exception.UserBattleTagAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserBlizzardIdAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;
import fr.opendoha.myguild.server.service.IUserService;
import fr.opendoha.myguild.server.service.UserService;

import java.time.Instant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller used to manage users
 */
@RestController
@RequestMapping("user")
public class UserController extends MotherController {

    protected final IUserService userService;
    protected final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    /**
     * Constructor
     */
    @Autowired
    public UserController(final UserService userService,
            final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        super();
        this.userService = userService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    /**
     * Endpoint used to add a new account
     * 
     * @throws UserNickNameAlreadyUsedException
     * @throws UserEmailAlreadyUsedException
     * @throws UserBlizzardIdAlreadyUsedException
     * @throws UserBattleTagAlreadyUsedException
     */
    @GetMapping("/addAccount")
    public void addingAccount(final OAuth2AuthenticationToken authentication, final UserRegistrationParameter parameter)
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
    @GetMapping("/getAccountInfo")
    public UserAccountDTO getAccountInfo(final OAuth2AuthenticationToken authentication)
            throws UserAccountNotExistedException {

        final Integer blizzardId = this.getBlizzardId(authentication);

        return this.userService.getAccountInfo(blizzardId);
    }

}
