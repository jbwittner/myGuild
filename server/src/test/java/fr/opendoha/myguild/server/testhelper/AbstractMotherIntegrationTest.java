package fr.opendoha.myguild.server.testhelper;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import fr.opendoha.myguild.server.model.blizzard.Covenant;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.repository.blizzard.CovenantRepository;
import fr.opendoha.myguild.server.repository.blizzard.FactionRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableRaceRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;

/**
 * Mother class for integrations tests
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public abstract class AbstractMotherIntegrationTest {

    protected final TestObjectFactory factory = new TestObjectFactory();
    protected final Faker faker = new Faker();

    public static final int NUMBER_SPECIALIZATION_ROLE = 3;
    public static final int NUMBER_PLAYABLE_CLASS = 20;
    public static final int NUMBER_PLAYABLE_SPECIALIZATION_PER_CLASS = 4;
    public static final int NUMBER_FACTION = 3;
    public static final int NUMBER_PLAYABLE_RACE = 20;
    public static final int NUMBER_COVENANT = 4;
    public static final String BASE_URI = "https://www.testmock.com/";

    @Autowired
    private SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    private PlayableSpecializationRepository playableSpecializationRepository;

    @Autowired
    private PlayableClassRepository playableClassRepository;

    @Autowired
    private FactionRepository factionRepository;

    @Autowired
    private PlayableRaceRepository playableRaceRepository;

    @Autowired
    private CovenantRepository covenantRepository;

    /**
     * Method launch before each test
     */
    @BeforeEach
    public void beforeEach() throws Exception {
        this.factory.resetAllList();
        this.initDataBeforeEach();
    }

    /**
     * Method launch after each test
     */
    @AfterEach
    public void afterEach(){
        this.factory.resetAllList();
    }

    /**
     * Method used to prepare the data of tests
     */
    abstract protected void initDataBeforeEach() throws Exception;

    /**
     * Method to check the static data
     */
    protected void checkStaticData(){
        Assertions.assertEquals(NUMBER_SPECIALIZATION_ROLE, this.specializationRoleRepository.count());
        Assertions.assertEquals(NUMBER_PLAYABLE_CLASS, this.playableClassRepository.count());
        Assertions.assertEquals(NUMBER_PLAYABLE_SPECIALIZATION_PER_CLASS * NUMBER_PLAYABLE_CLASS, this.playableSpecializationRepository.count());
        Assertions.assertEquals(NUMBER_FACTION, this.factionRepository.count());
        Assertions.assertEquals(NUMBER_PLAYABLE_RACE, this.playableRaceRepository.count());
        Assertions.assertEquals(NUMBER_COVENANT, this.covenantRepository.count());
    }

    /**
     * Method to prepare all static data (specialization, playable class, playable race, etc...)
     */
    protected void prepareStaticData(){

        final List<SpecializationRole> specializationRoleList = new ArrayList<>();
        final List<Faction> factionList = new ArrayList<>();

        for(Integer index = 0; index < NUMBER_SPECIALIZATION_ROLE; index++){
            SpecializationRole specializationRole = new SpecializationRole();
            specializationRole.setId(this.factory.getUniqueRandomInteger());
            specializationRole.setLocalizedModel(this.factory.getLocalizedModel());
            specializationRole.setType(this.factory.getUniqueRandomAlphanumericString());
            specializationRole = this.specializationRoleRepository.save(specializationRole);
            specializationRoleList.add(specializationRole);
        }

        for(Integer indexClass = 0; indexClass < NUMBER_PLAYABLE_CLASS; indexClass++ ){
            final PlayableClass playableClass = new PlayableClass();
            playableClass.setId(this.factory.getUniqueRandomInteger());
            playableClass.setLocalizedModel(this.factory.getLocalizedModel());
            playableClass.setMediaURL(this.factory.getUniqueRandomURI());

            final List<PlayableSpecialization> playableSpecializationList = new ArrayList<>();

            for(Integer indexPlayableSpecialization = 0; indexPlayableSpecialization < NUMBER_PLAYABLE_SPECIALIZATION_PER_CLASS; indexPlayableSpecialization++){
                PlayableSpecialization playableSpecialization = new PlayableSpecialization();
                playableSpecialization.setId(this.factory.getUniqueRandomInteger());
                playableSpecialization.setLocalizedModel(this.factory.getLocalizedModel());
                playableSpecialization.setUrlMedia(this.factory.getUniqueRandomURI());

                final Integer randomSpecializationRoleIndex = this.factory.getRandomInteger(NUMBER_SPECIALIZATION_ROLE);
                final SpecializationRole specializationRole = specializationRoleList.get(randomSpecializationRoleIndex);

                playableSpecialization.setSpecializationRole(specializationRole);

                playableSpecialization = this.playableSpecializationRepository.save(playableSpecialization);
                playableSpecializationList.add(playableSpecialization);
            }

            playableClass.setPlayableSpecializationList(playableSpecializationList);
            this.playableClassRepository.save(playableClass);
        }

        for(Integer index = 0; index < NUMBER_FACTION; index++){
            Faction faction = new Faction();
            faction.setId(this.factory.getUniqueRandomInteger());
            faction.setLocalizedModel(this.factory.getLocalizedModel());
            faction.setType(this.factory.getUniqueRandomAlphanumericString());
            faction = this.factionRepository.save(faction);
            factionList.add(faction);
        }

        for(Integer index = 0; index < NUMBER_PLAYABLE_RACE; index++){
            PlayableRace playableRace = new PlayableRace();
            playableRace.setId(this.factory.getUniqueRandomInteger());
            playableRace.setLocalizedModel(this.factory.getLocalizedModel());

            final Integer randomFactionIndex = this.factory.getRandomInteger(NUMBER_FACTION);
            final Faction faction = factionList.get(randomFactionIndex);

            playableRace.setFaction(faction);
            playableRace = this.playableRaceRepository.save(playableRace);
        }

        for(Integer index = 0; index < NUMBER_COVENANT; index++){
            Covenant covenant = new Covenant();
            covenant.setId(this.factory.getUniqueRandomInteger());
            covenant.setLocalizedModel(this.factory.getLocalizedModel());
            covenant = this.covenantRepository.save(covenant);
        }

        this.checkStaticData();
    }

    /**
     * Method to get a random PlayableClass
     */
    public PlayableClass getRandomPlayableClass(){
        final Integer index = this.factory.getRandomInteger(NUMBER_PLAYABLE_CLASS);
        final List<PlayableClass> playableClasses = this.playableClassRepository.findAll();
        return playableClasses.get(index);
    }

    /**
     * Method to get a random Faction
     */
    public Faction getRandomFaction(){
        final Integer index = this.factory.getRandomInteger(NUMBER_FACTION);
        final List<Faction> factions = this.factionRepository.findAll();
        return factions.get(index);
    }

    /**
     * Method to get a random PlayableRace
     */
    public PlayableRace getRandomPlayableRace(final Faction faction){
        final List<PlayableRace> playableRaces = this.playableRaceRepository.findByFaction(faction);
        final Integer index = this.factory.getRandomInteger(playableRaces.size());
        return playableRaces.get(index);
    }

    /**
     * Method to get a random Covenant
     */
    public Covenant getRandomCovenant(){
        final Integer index = this.factory.getRandomInteger(NUMBER_COVENANT);
        final List<Covenant> covenants = this.covenantRepository.findAll();
        return covenants.get(index);
    } 

}
