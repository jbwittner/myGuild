package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountProfileSummaryBlizzardData {

    private Integer id;

    @JsonProperty("wow_accounts")
    private WowAccountData[] wowAccountsData;

    private HrefData collections;

}
