package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.model.UserAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * Model for the characters
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CHARACTERS")
@Data
public class Character extends AbstractBlizzardModel {

    @Column(name = "NAME")
    private String name;

    @Column(name = "LEVEL")
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "USER_ACCOUNT_ID")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "GUILD_ID")
    private Guild guild;

    @ManyToOne
    @JoinColumn(name = "REALM_ID")
    private Realm realm;

    @ManyToOne
    @JoinColumn(name = "PLAYABLE_CLASS_ID")
    private PlayableClass playableClass;

    @ManyToOne
    @JoinColumn(name = "PLAYABLE_RACE_ID")
    private PlayableRace playableRace;

    @ManyToOne
    @JoinColumn(name = "GUILD_RANK_ID")
    private GuildRank guildRank;

    @ManyToOne
    @JoinColumn(name = "FACTION_ID")
    private Faction faction;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @Column(name = "INSET_URL")
    private String insetUrl;

    @Column(name = "AVERAGE_ITEM_LEVEL")
    private Integer averageItemLevel;

    @Column(name = "EQUIPPED_ITEM_LEVEL")
    private Integer equippedItemLevel;

    @Column(name = "LAST_LOGIN_TIMESTAMP")
    private Long lastLoginTimestamp;

    @Column(name = "IS_TOO_OLD")
    private Boolean isTooOld = false;

    @Column(name= "IS_FAVORITE")
    private boolean isFavorite = false;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    public void setIsFavoritesTrue(){
        this.isFavorite = true;
    }

    public void setIsFavoritesFalse(){
        this.isFavorite = false;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", averageItemLevel=" + averageItemLevel +
                ", equippedItemLevel=" + equippedItemLevel +
                ", lastLoginTimestamp=" + lastLoginTimestamp +
                ", id=" + id +
                ", isUpdated=" + isUpdated +
                '}';
    }
}
