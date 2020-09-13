package fr.opendoha.myguild.server.parameters.blizzardgamedata;

/**
 * DTO used for the indexations
 */
public class IndexDTO {

    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IndexDTO [id=" + id + ", name=" + name + "]";
    }
    
}