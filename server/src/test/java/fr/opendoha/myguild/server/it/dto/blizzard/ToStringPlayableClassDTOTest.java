package fr.opendoha.myguild.server.it.dto.blizzard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.LocalizedValuesDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableClassDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.SpecializationRoleDTO;

/**
 * Class to test toString method of PlayableClassDTO
 */
public class ToStringPlayableClassDTOTest extends AbstractMotherIntegrationTest {

    private static Integer LENGTH_VALUE = 20;

    static final Integer PLAYABLE_SPECIALIZATION = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {}

    /**
     * Test of toString
     */
    @Test
    public void testToStringOk(){

        PlayableSpecializationDTO[] playableSpecializations = new PlayableSpecializationDTO[PLAYABLE_SPECIALIZATION];

        for(int i = 0; i < PLAYABLE_SPECIALIZATION; i++){

            final LocalizedValuesDTO localizedValuesSpecializationDTO = new LocalizedValuesDTO();
            localizedValuesSpecializationDTO.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
            localizedValuesSpecializationDTO.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

            final LocalizedValuesDTO localizedValuesRoleDTO = new LocalizedValuesDTO();
            localizedValuesRoleDTO.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
            localizedValuesRoleDTO.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

            final SpecializationRoleDTO specializationRoleDTO = new SpecializationRoleDTO();
            specializationRoleDTO.setName(localizedValuesRoleDTO);
            specializationRoleDTO.setType(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

            final PlayableSpecializationDTO playableSpecializationDTO = new PlayableSpecializationDTO();
            playableSpecializationDTO.setId(this.factory.getRandomInteger());
            playableSpecializationDTO.setName(localizedValuesSpecializationDTO);
            playableSpecializationDTO.setRole(specializationRoleDTO);

            playableSpecializations[i] = playableSpecializationDTO;

        }

        final LocalizedValuesDTO localizedValuesClassDTO = new LocalizedValuesDTO();
        localizedValuesClassDTO.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
        localizedValuesClassDTO.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

        final PlayableClassDTO playableClassDTO = new PlayableClassDTO();
        playableClassDTO.setId(this.factory.getRandomInteger());
        playableClassDTO.setName(localizedValuesClassDTO);
        playableClassDTO.setSpecializations(playableSpecializations);

        final String toStringExpectex = "PlayableClassDTO [id=" + playableClassDTO.getId() + ", name=" + playableClassDTO.getName() + ", specializations=" + Arrays.toString(playableClassDTO.getSpecializations()) + "]";

        Assertions.assertEquals(toStringExpectex, playableClassDTO.toString());
    }

}