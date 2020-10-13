package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of account profile summary
 */
@Data
public class AccountProfileSummaryBlizzardData {

    private Integer id;

    @JsonProperty("wow_accounts")
    private List<WowAccountData> wowAccountsData;

}
