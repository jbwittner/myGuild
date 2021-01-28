package fr.opendoha.myguild.server.dto;

import java.util.List;

import lombok.Data;

/**
 * DTO for guild informations
 */
@Data
public class GuildDTO {

    private List<CharacterSummaryDTO> characterSummaryDTOs;
    private GuildSummaryDTO guildSummaryDTO;
    
}
