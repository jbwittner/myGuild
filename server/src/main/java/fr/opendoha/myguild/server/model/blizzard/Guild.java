package fr.opendoha.myguild.server.model.blizzard;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Model for the guilds
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "GUILDS")
public class Guild extends AbstractBlizzardModel {

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "guild", fetch = FetchType.LAZY)
    private List<Character> characterList;

    @Column(name = "MEMBER_COUNT")
    private Integer memberCount;

    @Column(name = "ACHIEVEMENT_POINTS")
    private Integer achievementPoints;

    @ManyToOne
    @JoinColumn(name = "REALM_ID")
    private Realm realm;

    @ManyToOne
    @JoinColumn(name = "FACTION_ID")
    private Faction faction;

    @Column(name = "USE_APPLICATION")
    private Boolean useApplication = false;

    @Override
    public String toString() {
        return "Guild{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", isUpdated=" + isUpdated +
                '}';
    }
}
