package fr.opendoha.myguild.server.data.blizzardgamedata;

/**
 * Data used for the indexations
 */
public class IndexData {

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
        return "IndexData [id=" + id + ", name=" + name + "]";
    }
    
}