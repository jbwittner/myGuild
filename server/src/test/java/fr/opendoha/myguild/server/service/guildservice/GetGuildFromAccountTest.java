package fr.opendoha.myguild.server.service.guildservice;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
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
 * Test class to test getGuildFromAccount method
 */
public class GetGuildFromAccountTest extends AbstractMotherIntegrationTest {

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

    protected GuildServiceImpl mockGuildService;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.mockGuildService = new GuildServiceImpl(this.userAccountRepository, this.characterRepository, this.guildRepository,
            this.guildRankRepository, this.factionRepository, this.playableRaceRepository, this.playableClassRepository,
            this.covenantRepository, this.blizzardAPIHelper);
    }

    /**
     * Test to get with two different guild
     */
    @Test
    public void testTwoDifferentGuildOk() throws IOException {
        this.prepareStaticData();

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Character character;
        GuildData guildData;
        final List<Faction> factions = this.factionRepository.findAll();
        final Faction faction = factions.get(this.factory.getRandomInteger(NUMBER_FACTION));
        Realm realm = this.factory.getRealm();
        realm = this.realmRepository.save(realm);

        Guild guildOne = this.factory.getGuild(faction, realm);
        guildOne = this.guildRepository.save(guildOne);
        guildData = new GuildData();
        guildData.setId(guildOne.getId());
        guildData.setName(guildOne.getName());

        Mockito.when(this.blizzardAPIHelper.getGuildData(guildOne))
            .thenReturn(guildData);

        character = this.factory.getCharacter(userAccount, guildOne);
        this.characterRepository.save(character);

        Guild guildTwo = this.factory.getGuild(faction, realm);
        guildTwo = this.guildRepository.save(guildTwo);
        guildData = new GuildData();
        guildData.setId(guildOne.getId());
        guildData.setName(guildOne.getName());

        Mockito.when(this.blizzardAPIHelper.getGuildData(guildTwo))
            .thenReturn(guildData);

        character = this.factory.getCharacter(userAccount, guildTwo);
        this.characterRepository.save(character);

        final BlizzardAccountParameter blizzardAccountParameter = new BlizzardAccountParameter();
        blizzardAccountParameter.setBlizzardId(userAccount.getBlizzardId());
        
        final List<GuildSummaryDTO> guildSummaryDTOs = this.mockGuildService.getGuildFromAccount(blizzardAccountParameter);

        Assertions.assertEquals(2, guildSummaryDTOs.size());

        for(final GuildSummaryDTO guildSummaryDTO : guildSummaryDTOs){
            Guild guild = new Guild();

            if(guildOne.getId().equals(guildSummaryDTO.getId())){
                guild = guildOne;
            } else if(guildTwo.getId().equals(guildSummaryDTO.getId())){
                guild = guildTwo;
            } else {
                Assertions.fail("No guild !");
            }

            Assertions.assertEquals(guild.getName(), guildSummaryDTO.getName());
            Assertions.assertEquals(guild.getFaction().getId(), guildSummaryDTO.getIndexFaction());
            Assertions.assertEquals(guild.getRealm().getSlug(), guildSummaryDTO.getRealmDTO().getSlug());
        }

    }

    /**
     * Test class to get with two characters who are in the same guild
     */
    @Test
    public void testTwoSameGuildOk() throws IOException {
        this.prepareStaticData();

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Character character;
        GuildData guildData;
        final List<Faction> factions = this.factionRepository.findAll();
        final Faction faction = factions.get(this.factory.getRandomInteger(NUMBER_FACTION));
        Realm realm = this.factory.getRealm();
        realm = this.realmRepository.save(realm);

        Guild guild = this.factory.getGuild(faction, realm);
        guild = this.guildRepository.save(guild);
        guildData = new GuildData();
        guildData.setId(guild.getId());
        guildData.setName(guild.getName());

        Mockito.when(this.blizzardAPIHelper.getGuildData(guild))
            .thenReturn(guildData);

        character = this.factory.getCharacter(userAccount, guild);
        this.characterRepository.save(character);

        character = this.factory.getCharacter(userAccount, guild);
        this.characterRepository.save(character);

        final BlizzardAccountParameter blizzardAccountParameter = new BlizzardAccountParameter();
        blizzardAccountParameter.setBlizzardId(userAccount.getBlizzardId());
        
        final List<GuildSummaryDTO> guildSummaryDTOs = this.mockGuildService.getGuildFromAccount(blizzardAccountParameter);

        Assertions.assertEquals(1, guildSummaryDTOs.size());

        final GuildSummaryDTO guildSummaryDTO = guildSummaryDTOs.get(0);
        Assertions.assertEquals(guild.getName(), guildSummaryDTO.getName());
        Assertions.assertEquals(guild.getFaction().getId(), guildSummaryDTO.getIndexFaction());
        Assertions.assertEquals(guild.getRealm().getSlug(), guildSummaryDTO.getRealmDTO().getSlug());

    }
    
}
