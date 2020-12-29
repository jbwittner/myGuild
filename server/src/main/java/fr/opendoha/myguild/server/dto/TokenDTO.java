package fr.opendoha.myguild.server.dto;

import lombok.Data;

/**
 * DTO for token
 */
@Data
public class TokenDTO {

    private String token;
    private long expiresAt;
    
}
