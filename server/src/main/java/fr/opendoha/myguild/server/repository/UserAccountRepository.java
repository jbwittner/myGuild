package fr.opendoha.myguild.server.repository;

import fr.opendoha.myguild.server.model.UserAccount;

/**
 * Repository for UserAccount
 */
public interface UserAccountRepository extends AbstractRepository<UserAccount, Long> {

    /**
     * Method to find UserAccount by blizzardId
     *
     * @param blizzardId BlizzardId name to find
     * @return UserAccount which the BlizzardId name match
     */
    UserAccount findByBlizzardId(Integer blizzardId);

    /**
     * Method to check if the nick name is already used
     *
     * @param nickName nick name to check
     * @return true if the nick name is already used otherwise false
     */
    boolean existsByNickName(String nickName);

    /**
     * Method to check if the email is already used
     *
     * @param email email to check
     * @return true if the email is already used otherwise false
     */
    boolean existsByEmail(String email);

    /**
     * Method to check if the BattleTag name is already used
     *
     * @param battleTag BattleTag name to check
     * @return true if the BattleTag name is already used otherwise false
     */
    boolean existsByBattleTag(String battleTag);

    /**
     * Method to check if the BlizzardId is already used
     *
     * @param blizzardId BlizzardId to check
     * @return true if the BlizzardId is already used otherwise false
     */
    boolean existsByBlizzardId(Integer blizzardId);

}
