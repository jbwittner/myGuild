package fr.opendoha.myguild.server.service.guildservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.model.blizzard.Character;
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
 * Test of getGuildRoster
 */
public class GetGuildRosterTest extends AbstractMotherIntegrationTest{

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

    protected static Integer NUMBER_CHARACTERS = 10;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.mockGuildServiceImpl = new GuildServiceImpl(this.userAccountRepository, this.characterRepository,
                this.guildRepository, this.guildRankRepository, this.factionRepository, this.playableRaceRepository,
                this.playableClassRepository, this.covenantRepository, this.realmRepository, this.blizzardAPIHelper);

        ReflectionTestUtils.setField(this.mockGuildServiceImpl, "guildName", this.guildName);
        ReflectionTestUtils.setField(this.mockGuildServiceImpl, "realmSlug", this.realmSlug);
    }

    /**
     * Test of getGuildRoster
     */
    @Test
    public void testGetGuildRoster() throws IOException {

        this.prepareStaticData();

        Guild guild = this.factory.getGuild();
        guild = this.guildRepository.save(guild);

        final List<Character> characterList = new ArrayList<>();

        final Faction faction = this.getRandomFaction();

        Realm realm = this.factory.getRealm();
        realm = this.realmRepository.save(realm);

        for(Integer index = 0; index < NUMBER_CHARACTERS; index++) {
            final Character character = this.factory.getCharacter();

            character.setFaction(faction);
            character.setRealm(realm);
            character.setPlayableRace(this.getRandomPlayableRace(faction));
            character.setPlayableClass(this.getRandomPlayableClass());

            character.setGuild(guild);
            characterRepository.save(character);
            characterList.add(character);
        }
        guild.setCharacterList(characterList);
        guild = this.guildRepository.save(guild);

        final GuildData guildData = new GuildData();
        guildData.setId(guild.getId()); 

        Mockito.when(this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName))
            .thenReturn(guildData);

        final List<CharacterSummaryDTO> characterSummaryDTOs = this.mockGuildServiceImpl.getGuildRoster();

        for(final CharacterSummaryDTO characterSummaryDTO : characterSummaryDTOs){

            boolean isPresent = false;

            for(final Character character : characterList) {

                if(character.getId() == characterSummaryDTO.getId()){
                    isPresent = true;
                    Assertions.assertEquals(characterSummaryDTO.getIndexFaction(), character.getFaction().getId());
                    Assertions.assertEquals(characterSummaryDTO.getIndexPlayableClass(), character.getPlayableClass().getId());
                    Assertions.assertEquals(characterSummaryDTO.getIndexPlayableRace(), character.getPlayableRace().getId());
                    Assertions.assertEquals(characterSummaryDTO.getRealmDTO().getSlug(), character.getRealm().getSlug());
                    continue;                    
                }
            }

            Assertions.assertTrue(isPresent);

        }

    }
    
}
