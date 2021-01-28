package fr.opendoha.myguild.server.service.blizzardservice;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.opendoha.myguild.server.dto.CovenantDTO;
import fr.opendoha.myguild.server.dto.FactionDTO;
import fr.opendoha.myguild.server.dto.LocalizedStringDTO;
import fr.opendoha.myguild.server.dto.PlayableClassDTO;
import fr.opendoha.myguild.server.dto.PlayableRaceDTO;
import fr.opendoha.myguild.server.dto.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.model.blizzard.Covenant;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.LocalizedModel;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CovenantRepository;
import fr.opendoha.myguild.server.repository.blizzard.FactionRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableRaceRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;

/**
 * Test class to test getStaticData method
 */
public class GetStaticDataTest extends AbstractMotherIntegrationTest {
    
    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected FactionRepository factionRepository;

    @Autowired
    protected PlayableRaceRepository playableRaceRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    protected CovenantRepository covenantRepository;

    @Autowired
    protected BlizzardService blizzardService;

    @Override
    protected void initDataBeforeEach() {
        this.prepareStaticData();
    }

    private void checkLocalizedData(final LocalizedModel localizedModel, final LocalizedStringDTO localizedStringDTO){
        Assertions.assertEquals(localizedModel.getDeDE(), localizedStringDTO.getDeDE());
        Assertions.assertEquals(localizedModel.getEnGB(), localizedStringDTO.getEnGB());
        Assertions.assertEquals(localizedModel.getEnUS(), localizedStringDTO.getEnUS());
        Assertions.assertEquals(localizedModel.getEsES(), localizedStringDTO.getEsES());
        Assertions.assertEquals(localizedModel.getEsMX(), localizedStringDTO.getEsMX());
        Assertions.assertEquals(localizedModel.getFrFR(), localizedStringDTO.getFrFR());
        Assertions.assertEquals(localizedModel.getItIT(), localizedStringDTO.getItIT());
        Assertions.assertEquals(localizedModel.getKoKR(), localizedStringDTO.getKoKR());
        Assertions.assertEquals(localizedModel.getPtBR(), localizedStringDTO.getPtBR());
        Assertions.assertEquals(localizedModel.getRuRU(), localizedStringDTO.getRuRU());
        Assertions.assertEquals(localizedModel.getZhCN(), localizedStringDTO.getZhCN());
        Assertions.assertEquals(localizedModel.getZhTW(), localizedStringDTO.getZhTW());
    }

    /**
     * Test to get static data
     */
    @Test
    public void testGetStaticData() {

        final StaticDataDTO staticDataDTO = this.blizzardService.getStaticData();

        final List<FactionDTO> factionDTOs = staticDataDTO.getFactionDTOs();
        Assertions.assertEquals(this.factionRepository.count(), factionDTOs.size());

        for(final FactionDTO factionDTO : factionDTOs){
            final Optional<Faction> optionalFaction = this.factionRepository.findByType(factionDTO.getType());
            Assertions.assertTrue(optionalFaction.isPresent());
            final Faction faction = optionalFaction.get();
            Assertions.assertEquals(faction.getType(), factionDTO.getType());
            this.checkLocalizedData(faction.getLocalizedModel(), factionDTO.getLocalizedStringDTO());
        }

        final List<PlayableRaceDTO> playableRaceDTOs = staticDataDTO.getPlayableRaceDTOs();
        Assertions.assertEquals(this.playableRaceRepository.count(), playableRaceDTOs.size());

        for(final PlayableRaceDTO playableRaceDTO : playableRaceDTOs){
            final Optional<PlayableRace> optionalPlayableRace = this.playableRaceRepository.findById(playableRaceDTO.getIndex());
            Assertions.assertTrue(optionalPlayableRace.isPresent());
            final PlayableRace playableRace = optionalPlayableRace.get();
            Assertions.assertEquals(playableRace.getFaction().getId(), playableRaceDTO.getFactionIndex());
            this.checkLocalizedData(playableRace.getLocalizedModel(), playableRaceDTO.getLocalizedStringDTO());
        }

        final List<PlayableClassDTO> playableClassDTOs = staticDataDTO.getPlayableClassDTOs();
        Assertions.assertEquals(this.playableClassRepository.count(), playableClassDTOs.size());

        for(final PlayableClassDTO playableClassDTO : playableClassDTOs){
            final Optional<PlayableClass> optionalPlayableClass = this.playableClassRepository.findById(playableClassDTO.getIndex());
            Assertions.assertTrue(optionalPlayableClass.isPresent());
            final PlayableClass playableClass = optionalPlayableClass.get();
            Assertions.assertEquals(playableClass.getMediaURL(), playableClassDTO.getMediaURL());
            this.checkLocalizedData(playableClass.getLocalizedModel(), playableClassDTO.getLocalizedStringDTO());

            final List<PlayableSpecialization> playableSpecializations = playableClass.getPlayableSpecializationList();
            final List<Integer> playableSpecializationIndex = playableClassDTO.getPlayableSpecializationIndex();
            Assertions.assertNotNull(playableSpecializationIndex);
            Assertions.assertEquals(playableSpecializations.size(), playableSpecializationIndex.size());

            for(final PlayableSpecialization playableSpecialization : playableSpecializations){
                final Boolean contain = playableSpecializationIndex.contains(playableSpecialization.getId());
                Assertions.assertTrue(contain);
            }
            
        }

        final List<PlayableSpecializationDTO> playableSpecializationDTOs = staticDataDTO.getPlayableSpecializationDTOs();
        Assertions.assertEquals(this.playableSpecializationRepository.count(), playableSpecializationDTOs.size());

        for(final PlayableSpecializationDTO playableSpecializationDTO : playableSpecializationDTOs){
            final Optional<PlayableSpecialization> optionalPlayableSpecialization = this.playableSpecializationRepository.findById(playableSpecializationDTO.getIndex());
            Assertions.assertTrue(optionalPlayableSpecialization.isPresent());
            final PlayableSpecialization playableSpecialization = optionalPlayableSpecialization.get();

            Assertions.assertEquals(playableSpecialization.getUrlMedia(), playableSpecializationDTO.getMediaURL());
            Assertions.assertEquals(playableSpecialization.getSpecializationRole().getType(), playableSpecializationDTO.getSpecializationRoleType());            
            this.checkLocalizedData(playableSpecialization.getLocalizedModel(), playableSpecializationDTO.getLocalizedStringDTO());
        }

        final List<CovenantDTO> covenantDTOs = staticDataDTO.getCovenantDTOs();
        Assertions.assertEquals(this.covenantRepository.count(), covenantDTOs.size());

        for(final CovenantDTO covenantDTO : covenantDTOs){
            final Optional<Covenant> optionalCovenant = this.covenantRepository.findById(covenantDTO.getIndex());
            Assertions.assertTrue(optionalCovenant.isPresent());
            final Covenant covenant = optionalCovenant.get();

            this.checkLocalizedData(covenant.getLocalizedModel(), covenantDTO.getLocalizedStringDTO());
        }
    }


}
