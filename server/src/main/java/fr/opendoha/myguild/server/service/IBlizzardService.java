package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the blizzard service
 */
public interface IBlizzardService {

    /**
     * Method to fetch the characters from a blizzard account
     */
    List<CharacterSummaryDTO> fetchCharacterAccount(BlizzardAccountParameter blizzardAccountParameter) throws IOException;

    /**
     * Method to fetch all static data (playable class, race, etc...)
     */
    void fetchStaticData() throws IOException;

    /**
     * Method to get all static data (playable class, race, etc...)
     */
    StaticDataDTO getStaticData();

    /**
     * Method to set a favorite character
     */
    void setFavoriteCharacter(BlizzardAccountParameter blizzardAccountParameter, FavoriteCharacterParameter favoriteCharacterParameter) throws IOException, CharacterNotExistedException;

    /**
     * Method used to fetch all data of the principal guild
     */
    void fetchPrincipalGuild() throws IOException;

    /**
     * Method used to fetch all members of the guild
     */
    void fetchGuildMembers() throws IOException;

}
