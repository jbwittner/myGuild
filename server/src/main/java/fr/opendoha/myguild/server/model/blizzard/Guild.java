package fr.opendoha.myguild.server.model.blizzard;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
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

    @Column(name= "ACHIEVEMENT_POINTS")
    private Integer achievementPoints;

    @Column(name= "CREATED_TIMESTAMP")
    private Long createdTimestamp;

    @Column(name= "MEMBER_COUNT")
    private Integer memberCount;

    @OneToMany(mappedBy = "guild", fetch = FetchType.LAZY)
    private List<Character> characterList;

    @ManyToOne
    @JoinColumn(name = "REALM_ID")
    private Realm realm;

    @ManyToOne
    @JoinColumn(name = "FACTION_ID")
    private Faction faction;

    @Column(name = "USE_APPLICATION")
    private Boolean useApplication = false;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Override
    public String toString() {
        return "Guild{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", isUpdated=" + isUpdated +
                '}';
    }
}
