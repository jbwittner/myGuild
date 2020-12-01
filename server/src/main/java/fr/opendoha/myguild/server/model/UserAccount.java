package fr.opendoha.myguild.server.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import fr.opendoha.myguild.server.model.blizzard.Guild;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "FAVORITE_GUILDS", joinColumns = @JoinColumn(name="USER_ACCOUNT_BLIZZARD_ID"), inverseJoinColumns = @JoinColumn(name="GUILD_ID"))
    private List<Guild> favoriteGuilds = new ArrayList<>();

}