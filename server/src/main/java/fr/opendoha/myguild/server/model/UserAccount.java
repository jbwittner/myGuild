package fr.opendoha.myguild.server.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class of the User account
 */
@Data
@Entity
@Table(name = "USER_ACCOUNT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "BLIZZARD_ID", name = "UK_BLIZZARD_ID"),
        @UniqueConstraint(columnNames = "BATTLE_TAG", name = "UK_BATTLE_TAG"),
        @UniqueConstraint(columnNames = "EMAIL", name = "UK_EMAIL"),
        @UniqueConstraint(columnNames = "NICK_NAME", name = "UK_NICK_NAME")
})
public class UserAccount {

    @Id
    @Column(name = "blizzard_id", nullable = false)
    private Integer blizzardId;

    @Column(name = "battle_tag", nullable = false, length = 60)
    private String battleTag;

    @Column(name = "nick_name", nullable = false, length = 60)
    private String nickName;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}