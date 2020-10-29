package fr.opendoha.myguild.server.dto;

import java.util.List;

import lombok.Data;

/**
 * DTO for guilds account
 */
@Data
public class GuildsAccountDTO {

    List<GuildInformationsDTO> guildInformationsDTOs;
    
}
