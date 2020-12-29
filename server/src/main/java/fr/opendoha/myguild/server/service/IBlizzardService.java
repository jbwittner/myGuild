package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterRosterSummaryDTO;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildsAccountDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the blizzard service
 */
public interface IBlizzardService {

    List<CharacterSummaryDTO> fetchCharacterAccount(BlizzardAccountParameter blizzardAccountParameter) throws IOException;

    List<CharacterRosterSummaryDTO> fetchCharacterRoster();

    void fetchStaticData() throws IOException;

    StaticDataDTO getStaticData();

    void setFavoriteCharacter(BlizzardAccountParameter blizzardAccountParameter, FavoriteCharacterParameter favoriteCharacterParameter) throws IOException, CharacterNotExistedException;

    void fetchPrincipalGuild() throws IOException;

    void fetchGuildMembers() throws IOException;

    

}
