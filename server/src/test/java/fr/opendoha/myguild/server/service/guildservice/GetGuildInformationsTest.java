package fr.opendoha.myguild.server.service.guildservice;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.data.blizzardgamedata.RealmData;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.repository.blizzard.CovenantRepository;
import fr.opendoha.myguild.server.repository.blizzard.FactionRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRankRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableRaceRepository;
import fr.opendoha.myguild.server.repository.blizzard.RealmRepository;
import fr.opendoha.myguild.server.service.implementation.GuildServiceImpl;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;

/**
 * Test of getGuildInformations
 */
public class GetGuildInformationsTest extends AbstractMotherIntegrationTest {

    @Value("${application.blizzard.guild.name}")
    protected String guildName;

    @Value("${application.blizzard.guild.realmslug}")
    protected String realmSlug;

    protected GuildServiceImpl mockGuildServiceImpl;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected GuildRepository guildRepository;

    @Autowired
    protected CharacterRepository characterRepository;

    @Autowired
    protected GuildRankRepository guildRankRepository;

    @Autowired
    protected FactionRepository factionRepository;

    @Autowired
    protected PlayableRaceRepository playableRaceRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected CovenantRepository covenantRepository;

    @Autowired
    protected RealmRepository realmRepository;

    @MockBean
    protected BlizzardAPIHelper blizzardAPIHelper;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.mockGuildServiceImpl = new GuildServiceImpl(this.userAccountRepository, this.characterRepository,
                this.guildRepository, this.guildRankRepository, this.factionRepository, this.playableRaceRepository,
                this.playableClassRepository, this.covenantRepository, this.realmRepository, this.blizzardAPIHelper);

        ReflectionTestUtils.setField(this.mockGuildServiceImpl, "guildName", this.guildName);
        ReflectionTestUtils.setField(this.mockGuildServiceImpl, "realmSlug", this.realmSlug);
    }

    /**
     * Test of getGuildInformations
     */
    @Test
    public void testGetGuildInformations() throws IOException {

        this.prepareStaticData();

        final Faction faction = this.getRandomFaction();

        Realm realm = this.factory.getRealm();
        realm.setSlug(this.realmSlug);
        realm = this.realmRepository.save(realm);

        Guild guild = this.factory.getGuild(faction, realm);
        guild.setName(this.guildName);
        guild.setUseApplication(true);
        guild = this.guildRepository.save(guild);

        final GuildData guildData = new GuildData();
        guildData.setId(guild.getId()); 
        guildData.setAchievementPoints(this.factory.getRandomInteger());
        guildData.setCreatedTimestamp(Long.valueOf(this.factory.getRandomInteger()));
        guildData.setMemberCount(this.factory.getRandomInteger());

        final RealmData realmData = new RealmData();
        realmData.setSlug(this.realmSlug);

        Mockito.when(this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName))
            .thenReturn(guildData);

        final GuildSummaryDTO guildSummaryDTO = this.mockGuildServiceImpl.getGuildInformations();

        Assertions.assertEquals(guildData.getAchievementPoints(), guildSummaryDTO.getAchievementPoints());
        Assertions.assertEquals(guildData.getCreatedTimestamp(), guildSummaryDTO.getCreatedTimestamp());
        Assertions.assertEquals(guildData.getMemberCount(), guildSummaryDTO.getMemberCount());
        Assertions.assertEquals(guild.getFaction().getId(), guildSummaryDTO.getIndexFaction());
        Assertions.assertEquals(guild.getName(), guildSummaryDTO.getName());
        Assertions.assertEquals(guild.getRealm().getSlug(), guildSummaryDTO.getRealmDTO().getSlug());
        Assertions.assertEquals(guild.getId(), guildSummaryDTO.getId());
        Assertions.assertTrue(guildSummaryDTO.getUseApplication());

    }
    
}
