package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.FavoriteGuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildsAccountDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;
import fr.opendoha.myguild.server.parameters.FavoriteGuildParameter;

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

    StaticDataDTO getStaticData();

    void setFavoriteCharacter(BlizzardAccountParameter blizzardAccountParameter, FavoriteCharacterParameter favoriteCharacterParameter) throws IOException, CharacterNotExistedException;

    void fetchPrincipalGuild() throws IOException;

    void fetchGuildMembers() throws IOException;

    

}
