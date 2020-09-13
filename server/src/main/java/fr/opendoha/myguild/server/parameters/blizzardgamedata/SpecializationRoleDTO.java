package fr.opendoha.myguild.server.parameters.blizzardgamedata;

/**
 * DTO of specialization role
 */
public class SpecializationRoleDTO {

    private String type;
    private LocalizedValuesDTO name;

    public LocalizedValuesDTO getName() {
        return name;
    }

    public void setName(final LocalizedValuesDTO name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SpecializationRoleDTO [name=" + name + ", type=" + type + "]";
    }


}