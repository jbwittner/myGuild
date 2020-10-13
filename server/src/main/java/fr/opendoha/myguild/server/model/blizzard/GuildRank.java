package fr.opendoha.myguild.server.model.blizzard;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Model for the guild ranks
 */
@Entity
@Table(name = "guild_ranks")
@Data
public class GuildRank {

    @Id
    @Column(name = "id", nullable = false, updatable = false, insertable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "guild_rank")
    private Integer rank;

    @Column(name = "is_updated", nullable = false)
    private Boolean isUpdated = true;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @OneToMany(mappedBy = "guildRank", fetch = FetchType.LAZY)
    private List<Character> characterList;

    @Override
    public String toString() {
        return "GuildRank{" +
                "id=" + id +
                ", rank=" + rank +
                ", isUpdated=" + isUpdated +
                ", name='" + name + '\'' +
                '}';
    }

}
