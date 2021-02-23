package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.exception.IsNotCharacterAccountException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the character service
 */
public interface CharacterService {

    /**
     * Method to fetch the characters from a blizzard account
     */
    List<CharacterSummaryDTO> fetchCharacterAccount(BlizzardAccountParameter blizzardAccountParameter) throws IOException;

    /**
     * Method to get the characters from a blizzard account
     */
    List<CharacterSummaryDTO> getCharacterAccount(BlizzardAccountParameter blizzardAccountParameter);

    /**
     * Method to set a favorite character
     */
    void setFavoriteCharacter(FavoriteCharacterParameter favoriteCharacterParameter) throws CharacterNotExistedException, IsNotCharacterAccountException;

}
