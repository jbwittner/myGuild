package fr.opendoha.myguild.server.model.blizzard;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class for Blizzard models
 */
@MappedSuperclass
public abstract class AbstractBlizzardModel {

    @Id
    @Column(name = "ID", nullable = false, updatable = false, insertable = false, unique = true)
    protected Integer id;

    @Embedded
    protected LocalizedString names;

    @Column(name = "IS_UPDATED", nullable = false)
    protected Boolean isUdpated = true;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public LocalizedString getNames() {
        return names;
    }

    public void setNames(final LocalizedString names) {
        this.names = names;
    }

    public Boolean getIsUdpated() {
        return isUdpated;
    }

    public void setIsUdpated(final Boolean isUdpated) {
        this.isUdpated = isUdpated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean equals(final Object obj);

}