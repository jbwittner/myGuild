package fr.opendoha.myguild.server.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private String token;
    private long expiresAt;
    
}
