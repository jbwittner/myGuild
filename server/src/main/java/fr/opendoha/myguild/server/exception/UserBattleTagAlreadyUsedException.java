package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Exception to signal a BattleTag already used
 */
@SuppressWarnings("serial")
public final class UserBattleTagAlreadyUsedException extends FunctionalException {

    /**
     * Exception to signal a BattleTag already used
     */
    public UserBattleTagAlreadyUsedException(final UserRegistrationParameter userRegistrationParameter) {
        super("The BattleTag " + userRegistrationParameter.getBattleTag() + " is already used by a another account");
    }

}
