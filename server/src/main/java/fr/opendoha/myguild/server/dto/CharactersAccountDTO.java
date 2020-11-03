package fr.opendoha.myguild.server.dto;

import java.util.List;

import lombok.Data;

@Data
public class CharactersAccountDTO {
    
    List<CharacterSummaryDTO> characterSummaryDTOs;

}
