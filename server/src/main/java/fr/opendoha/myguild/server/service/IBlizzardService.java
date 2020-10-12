package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

/**
 * Interface for the blizzard service
 */
public interface IBlizzardService {

    void fetchAccountCharacter(final BlizzardAccountParameter blizzardAccountParameter);

}
