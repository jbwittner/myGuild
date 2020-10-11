package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRankRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRepository;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage the blizzard game data
 */
@Service
@Transactional
public class BlizzardService implements IBlizzardService {

    protected final Logger logger = LoggerFactory.getLogger(BlizzardService.class);

    @Value("${application.blizzard.wow.profile.base-uri}")
    protected String baseUriProfile;

    @Value("${application.blizzard.wow.profile.namespace}")
    protected String namespaceProfile;

    protected final OAuth2FlowHandler oAuth2FlowHandler;
    protected final UserAccountRepository userAccountRepository;
    protected final GuildRepository guildRepository;
    protected final CharacterRepository characterRepository;
    protected final GuildRankRepository guildRankRepository;
    protected final HttpHelper httpHelper;

    /**
     * Constructor
     */
    @Autowired
    public BlizzardService(
            final OAuth2FlowHandler oAuth2FlowHandler,
            final UserAccountRepository userAccountRepository,
            final CharacterRepository characterRepository,
            final GuildRepository guildRepository,
            final GuildRankRepository guildRankRepository,
            final HttpHelper httpHelper
    ) {

        this.oAuth2FlowHandler = oAuth2FlowHandler;
        this.userAccountRepository = userAccountRepository;
        this.characterRepository = characterRepository;
        this.guildRepository = guildRepository;
        this.guildRankRepository = guildRankRepository;
        this.httpHelper = httpHelper;
    }

}