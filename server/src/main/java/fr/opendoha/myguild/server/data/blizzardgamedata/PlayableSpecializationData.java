package fr.opendoha.myguild.server.data.blizzardgamedata;

/**
 * Data for playable specialization
 */
public class PlayableSpecializationData {

    private Integer id;
    private PlayableClassData playable_class;
    private LocalizedValuesData name;
    private SpecializationRoleData role;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public PlayableClassData getPlayable_class() {
        return playable_class;
    }

    public void setPlayable_class(final PlayableClassData playable_class) {
        this.playable_class = playable_class;
    }

    public LocalizedValuesData getName() {
        return name;
    }

    public void setName(final LocalizedValuesData name) {
        this.name = name;
    }

    public SpecializationRoleData getRole() {
        return role;
    }

    public void setRole(final SpecializationRoleData role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "PlayableSpecializationData [id=" + id + ", name=" + name + ", role=" + role + "]";
    }
    

}