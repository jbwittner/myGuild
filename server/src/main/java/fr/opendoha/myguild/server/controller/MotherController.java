package fr.opendoha.myguild.server.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Objects;

/**
 * Mother controller
 */
public class MotherController {

    /**
     * Method to get the battle tag from the authentication
     */
    protected String getBattleTag(final OAuth2AuthenticationToken authentication){

        final String battleTag = Objects.requireNonNull(
                authentication.getPrincipal().getAttribute("battletag")
        ).toString();

        return battleTag;
    }

    /**
     * Method to get the blizzard id tag from the authentication
     */
    protected Integer getBlizzardId(final OAuth2AuthenticationToken authentication){
        
        final Integer id = Integer.parseInt(
                Objects.requireNonNull(
                        authentication.getPrincipal().getAttribute("id")
                ).toString()
        );

        return id;
    }

}
