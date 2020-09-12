package fr.opendoha.myguild.server.service;

import java.io.IOException;

/**
 * Interface for the service who manage the blizzard game data
 */
public interface IBlizzardGameData {

    /**
     * Method used to update all data coming from the Blizzard API
     */
    void updateData() throws IOException;

}