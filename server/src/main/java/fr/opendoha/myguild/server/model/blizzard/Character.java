package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.model.UserAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model for the characters
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "characters")
@Data
public class Character extends AbstractBlizzardModel {

    private String name;

    private Integer level;

    @ManyToOne
    @JoinColumn(name = "userAccount_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToOne
    @JoinColumn(name = "realm_id")
    private Realm realm;

    @ManyToOne
    @JoinColumn(name = "guild_rank_id")
    private GuildRank guildRank;

    @ManyToOne
    @JoinColumn(name = "faction_id")
    private Faction faction;

    private Integer averageItemLevel;
    private Integer equippedItemLevel;
    private Long lastLoginTimestamp;

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
                ", updateTime=" + updateTime +
                '}';
    }
}
