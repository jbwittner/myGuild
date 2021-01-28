package fr.opendoha.myguild.server.exception;

/**
 * Exception used when the character doesn't exist
 */
@SuppressWarnings("serial")
public class CharacterNotExistedException extends FunctionalException {

    /**
     * Exception used when the character doesn't exist
     */
    public CharacterNotExistedException(final Integer characterId) {
        super("No character exist with the ID : " + characterId);
    }
}