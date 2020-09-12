package fr.opendoha.myguild.server.it.dto.blizzard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.dto.blizzard.LocalizedValuesDTO;
import fr.opendoha.myguild.server.dto.blizzard.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.dto.blizzard.SpecializationRoleDTO;
import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;

/**
 * Class to test toString method of PlayableSpecializationDTO
 */
public class ToStringPlayableSpecializationDTOTest extends AbstractMotherIntegrationTest {

    private static Integer LENGTH_VALUE = 20;

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

        final String toStringExpectex = "PlayableSpecializationDTO [id=" + playableSpecializationDTO.getId() + ", name=" + playableSpecializationDTO.getName() + ", role=" + playableSpecializationDTO.getRole() + "]";

        Assertions.assertEquals(toStringExpectex, playableSpecializationDTO.toString());
    }

}