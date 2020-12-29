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
    @Column(name = "BLIZZARD_ID", nullable = false)
    private Integer blizzardId;

    @Column(name = "BATTLE_TAG", nullable = false, length = 60)
    private String battleTag;

    @Column(name = "NICK_NAME", nullable = false, length = 60)
    private String nickName;

    @Column(name = "EMAIL", nullable = false, length = 60)
    private String email;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @CreationTimestamp
    @Column(name = "CREATION_DATE_TIME", nullable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE_TIME", nullable = false)
    private LocalDateTime updateDateTime;
    
}