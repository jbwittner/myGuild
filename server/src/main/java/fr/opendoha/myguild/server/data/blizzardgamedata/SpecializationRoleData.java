package fr.opendoha.myguild.server.data.blizzardgamedata;

/**
 * Data of specialization role
 */
public class SpecializationRoleData {

    private String type;
    private LocalizedValuesData name;

    public LocalizedValuesData getName() {
        return name;
    }

    public void setName(final LocalizedValuesData name) {
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
        return "SpecializationRoleData [name=" + name + ", type=" + type + "]";
    }


}