package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Exception to signal a BattleTag already used
 */
@SuppressWarnings("serial")
public final class UserBattleTagAlreadyUsedException extends RuntimeException {

    /**
     * Exception to signal a BattleTag already used
     * @param userRegistrationParameter userRegistrationParameter who throw the exception
     */
    public UserBattleTagAlreadyUsedException(final UserRegistrationParameter userRegistrationParameter) {
        super("The BattleTag " + userRegistrationParameter.getBattletag() + " is already used by a another account");
    }

}
