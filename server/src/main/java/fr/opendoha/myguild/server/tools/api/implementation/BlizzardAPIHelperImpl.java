package fr.opendoha.myguild.server.tools.api.implementation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import fr.opendoha.myguild.server.tools.helper.HttpHelper;

/**
 * Help making an http call to the Blizzard API
 */
@Component
public class BlizzardAPIHelperImpl implements BlizzardAPIHelper {

    @Value("${application.blizzard.wow.profile.base-uri}")
    protected String baseUriProfile;

    @Value("${application.blizzard.wow.profile.namespace}")
    protected String namespaceProfile;

    @Value("${application.blizzard.wow.game-data.base-uri}")
    protected String baseUriGameData;

    @Value("${application.blizzard.wow.game-data.namespace}")
    protected String namespaceGameData;

    private final OAuth2FlowHandler oAuth2FlowHandler;
    private final HttpHelper httpHelper;

    /**
     * Constructor
     */
    @Autowired
    public BlizzardAPIHelperImpl(final OAuth2FlowHandler oAuth2FlowHandler,
            final HttpHelper httpHelper){
        this.oAuth2FlowHandler = oAuth2FlowHandler;
        this.httpHelper = httpHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterData getCharacterData(final CharacterSummaryData characterSummaryData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = characterSummaryData.getCharacterHrefData().getHref() + "&access_token=" + token;

        final CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);
        
        return characterData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterData getCharacterData(final Character character) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriProfile + "/wow/character/" + character.getRealm().getSlug()
                + "/" + character.getName().toLowerCase() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        final CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);

        return characterData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterData getCharacterData(final GuildMemberIndexData guildMemberIndexData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = guildMemberIndexData.getGuildMemberData().getHrefData().getHref() + "&access_token=" + token;

        final CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);

        return characterData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountProfileSummaryBlizzardData getAccountProfileSummaryBlizzardData(final BlizzardAccountParameter blizzardAccountParameter)
        throws HttpClientErrorException{

            final String url = this.baseUriProfile + "/user/wow?namespace=" + this.namespaceProfile + "&access_token=" + blizzardAccountParameter.getToken();

            final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData = this.httpHelper.getForObject(url, AccountProfileSummaryBlizzardData.class);

            return accountProfileSummaryBlizzardData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuildData getGuildData(final Guild guild) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriGameData + "/guild/" + guild.getRealm().getSlug()
                + "/" + guild.getName().toLowerCase() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        final GuildData guildData = this.httpHelper.getForObject(url, GuildData.class);

        return guildData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuildRosterIndexData getGuildRosterIndexData(final Guild guild) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriGameData + "/guild/" + guild.getRealm().getSlug() + "/" + guild.getName().toLowerCase()
                + "/roster?namespace=" + this.namespaceProfile + "&locale=&access_token=" + token;

        final GuildRosterIndexData guildRosterIndexData = this.httpHelper.getForObject(url, GuildRosterIndexData.class);

        return guildRosterIndexData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayableRacesIndexData getPlayableRacesIndexData() throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriGameData + "/playable-race/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        final PlayableRacesIndexData playableRacesIndexData =
                this.httpHelper.getForObject(url, PlayableRacesIndexData.class);

        return playableRacesIndexData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayableRaceData getPlayableRaceData(final IndexData indexData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = indexData.getHrefData().getHref() + "&access_token=" + token;

        final PlayableRaceData playableRaceData = this.httpHelper.getForObject(url, PlayableRaceData.class);

        return playableRaceData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayableClassesIndexData getPlayableClassesIndexData() throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriGameData + "/playable-class/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        final PlayableClassesIndexData playableClassesIndexData =
                this.httpHelper.getForObject(url, PlayableClassesIndexData.class);

        return playableClassesIndexData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayableClassData getPlayableClassData(final IndexData indexData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = indexData.getHrefData().getHref() + "&access_token=" + token;

        final PlayableClassData playableClassData = this.httpHelper.getForObject(url, PlayableClassData.class);

        return playableClassData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameDataMediaData getGameDataMediaData(final PlayableClassData playableClassData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = playableClassData.getMediaHrefData().getHrefData().getHref() + "&access_token=" + token;

        final GameDataMediaData gameDataMediaData = this.httpHelper.getForObject(url, GameDataMediaData.class);

        return gameDataMediaData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayableSpecializationData getPlayableSpecializationData(final IndexData indexData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = indexData.getHrefData().getHref() + "&access_token=" + token;

        final PlayableSpecializationData playableSpecializationData = this.httpHelper.getForObject(url, PlayableSpecializationData.class);

        return playableSpecializationData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameDataMediaData getGameDataMediaData(final PlayableSpecializationData playableSpecializationData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = playableSpecializationData.getMediaData().getHrefData().getHref() + "&access_token=" + token;

        final GameDataMediaData gameDataMediaData = this.httpHelper.getForObject(url, GameDataMediaData.class);

        return gameDataMediaData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameDataMediaData getGameDataMediaData(final String href) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = href + "&access_token=" + token;

        final GameDataMediaData gameDataMediaData = this.httpHelper.getForObject(url, GameDataMediaData.class);

        return gameDataMediaData;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CovenantIndexData getCovenantIndexData() throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriGameData + "/covenant/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        final CovenantIndexData covenantIndexData =
                this.httpHelper.getForObject(url, CovenantIndexData.class);

        return covenantIndexData;

    }


}
