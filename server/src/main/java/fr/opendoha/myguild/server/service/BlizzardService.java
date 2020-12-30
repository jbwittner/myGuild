package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.StaticDataDTO;

import java.io.IOException;

/**
 * Interface for the blizzard service
 */
public interface BlizzardService {

    /**
     * Method to fetch all static data (playable class, race, etc...)
     */
    void fetchStaticData() throws IOException;

    /**
     * Method to get all static data (playable class, race, etc...)
     */
    StaticDataDTO getStaticData();

}
