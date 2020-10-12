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
@Table(name = "guilds")
public class Guild extends AbstractBlizzardModel {

    @Column(name = "name")
    private String name;

    @Column(name = "application_is_used", nullable = false)
    private Boolean useApplication = false;

    @OneToMany(mappedBy = "guild", fetch = FetchType.LAZY)
    private List<Character> characterList;

    @ManyToOne
    @JoinColumn(name = "realm_id")
    private Realm realm;

    @ManyToOne
    @JoinColumn(name = "faction_id")
    private Faction faction;

    @Override
    public String toString() {
        return "Guild{" +
                "name='" + name + '\'' +
                ", useApplication=" + useApplication +
                ", id=" + id +
                ", isUpdated=" + isUpdated +
                ", updateTime=" + updateTime +
                '}';
    }
}
