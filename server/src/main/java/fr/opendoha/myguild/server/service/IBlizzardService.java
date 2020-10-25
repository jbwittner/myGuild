package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

import java.io.IOException;

/**
 * Interface for the blizzard service
 */
public interface IBlizzardService {

    /**
     * Method to fetch character data from a blizzard account
     */
    void fetchAccountCharacter(final BlizzardAccountParameter blizzardAccountParameter);

    /**
     * Method to fetch all the static data
     */
    void fetchStaticData() throws IOException;

    /**
     * Method to fetch all the data
     */
    void fetchAllData() throws IOException;

    /**
     * Method to fetch all data characters
     */
    void fetchAllDataCharacters() throws IOException;

}
