package fr.opendoha.myguild.server.exception;

/**
 * Exception used when the character doesn't exist
 */
public class CharacterNotExistedException extends FunctionalException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception used when the character doesn't exist
     */
    public CharacterNotExistedException(final Integer characterId) {
        super("No character exist with the ID : " + characterId);
    }
}