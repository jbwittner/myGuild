package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.UserAccount;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO used to send the user account information
 */
@Data
public class UserAccountDTO {

    private Integer blizzardId;
    private String battleTag;
    private String nickName;
    private String email;
    private LocalDateTime creationTime;
    private LocalDateTime updateTime;

    /**
     * Builder to get DTO from model
     */
    public void builderDTO(final UserAccount userAccount) {
        this.battleTag = userAccount.getBattleTag();
        this.blizzardId = userAccount.getBlizzardId();
        this.email = userAccount.getEmail();
        this.nickName = userAccount.getNickName();
        this.creationTime = userAccount.getCreationDateTime();
        this.updateTime = userAccount.getUpdateDateTime();
    }

}
