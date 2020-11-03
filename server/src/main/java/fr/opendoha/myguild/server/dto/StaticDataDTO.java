package fr.opendoha.myguild.server.dto;

import java.util.List;

import lombok.Data;

@Data
public class StaticDataDTO {
    
    private List<PlayableClassDTO> playableClassDTOs;
    private List<PlayableRaceDTO> playableRaceDTOs;
    private List<PlayableSpecializationDTO> playableSpecializationDTOs;
    private List<SpecializationRoleDTO> specializationRoleDTOs;
    private List<FactionDTO> factionDTOs;

}
