package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

/**
 * Interface for the blizzard service
 */
public interface IBlizzardService {

    /**
     * Method to fetch character data from a blizzard account
     */
    void fetchAccountCharacter(final BlizzardAccountParameter blizzardAccountParameter);

}
