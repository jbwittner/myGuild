package fr.opendoha.myguild.server.parameters.blizzardgamedata;

/**
 * DTO for playable specialization
 */
public class PlayableSpecializationDTO {

    private Integer id;
    private PlayableClassDTO playable_class;
    private LocalizedValuesDTO name;
    private SpecializationRoleDTO role;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public PlayableClassDTO getPlayable_class() {
        return playable_class;
    }

    public void setPlayable_class(final PlayableClassDTO playable_class) {
        this.playable_class = playable_class;
    }

    public LocalizedValuesDTO getName() {
        return name;
    }

    public void setName(final LocalizedValuesDTO name) {
        this.name = name;
    }

    public SpecializationRoleDTO getRole() {
        return role;
    }

    public void setRole(final SpecializationRoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "PlayableSpecializationDTO [id=" + id + ", name=" + name + ", role=" + role + "]";
    }
    

}