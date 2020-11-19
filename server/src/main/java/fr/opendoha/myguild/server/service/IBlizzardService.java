package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildsAccountDTO;
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
     * @throws IOException
     */
    List<CharacterSummaryDTO> fetchCharacterAccount(BlizzardAccountParameter blizzardAccountParameter) throws IOException;

    /**
     * Method to fetch all static data (playable class, race, etc...)
     */
    void fetchStaticData() throws IOException;

    /**
     * Method to get all guilds who the account have a character
     */
    List<GuildSummaryDTO> fetchGuildsAccount(BlizzardAccountParameter blizzardAccountParameter) throws IOException;


    StaticDataDTO getStaticData();

    CharacterSummaryDTO setFavoriteCharacter(BlizzardAccountParameter blizzardAccountParameter, FavoriteCharacterParameter favoriteCharacterParameter) throws IOException, CharacterNotExistedException;

}
