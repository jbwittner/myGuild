package fr.opendoha.myguild.server.tools.api;

import java.io.IOException;

import org.springframework.web.client.HttpClientErrorException;

import fr.opendoha.myguild.server.data.blizzardgamedata.AccountProfileSummaryBlizzardData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterSummaryData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CovenantIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GameDataMediaData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildMemberIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildRosterIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassesIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableRaceData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableRacesIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

/**
 * Helper for http request
 */
public interface BlizzardAPIHelper {

    /**
     * Get CharacterData
     */
    CharacterData getCharacterData(final CharacterSummaryData characterSummaryData) throws IOException;

    /**
     * Get CharacterData
     */
    CharacterData getCharacterData(final Character character) throws IOException;

    /**
     * Get CharacterData
     */
    CharacterData getCharacterData(final GuildMemberIndexData guildMemberIndexData) throws IOException;

    /**
     * Get AccountProfileSummaryBlizzardData
     */
    AccountProfileSummaryBlizzardData getAccountProfileSummaryBlizzardData(final BlizzardAccountParameter blizzardAccountParameter)
        throws HttpClientErrorException;

    /**
     * Get GuildData
     */
    GuildData getGuildData(Guild guild) throws IOException;

    /**
     * Get GuildRosterIndexData
     */
    GuildRosterIndexData getGuildRosterIndexData(Guild guild) throws IOException;

    /**
     * Get PlayableRacesIndexData
     */
    PlayableRacesIndexData getPlayableRacesIndexData() throws IOException;

    /**
     * Get PlayableRaceData
     */
    PlayableRaceData getPlayableRaceData(final IndexData indexData) throws IOException;

    /**
     * Get PlayableClassesIndexData
     */
    PlayableClassesIndexData getPlayableClassesIndexData() throws IOException;

    /**
     * Get PlayableClassData
     */
    PlayableClassData getPlayableClassData(final IndexData indexData) throws IOException;

    /**
     * Get GameDataMediaData
     */
    GameDataMediaData getGameDataMediaData(final PlayableClassData playableClassData) throws IOException;

    /**
     * Get PlayableSpecializationData
     */
    PlayableSpecializationData getPlayableSpecializationData(final IndexData indexData) throws IOException;

    /**
     * Get GameDataMediaData
     */
    GameDataMediaData getGameDataMediaData(final PlayableSpecializationData playableSpecializationData) throws IOException;

    /**
     * Get GameDataMediaData
     */
    GameDataMediaData getGameDataMediaData(final String href) throws IOException;

    /**
     * Get CovenantIndexData
     */
    CovenantIndexData getCovenantIndexData() throws IOException;
}
