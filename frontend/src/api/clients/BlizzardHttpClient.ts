import { CharacterSummaryDTO, GuildSummaryDTO, StaticDataDTO } from "../Entities"
import { HttpClient } from "./HttpClient"

export class BlizzardHttpClient extends HttpClient {

    static FETCH_ACCOUNT_CHARACTER_PATH =  "/blizzard/fetchAccountCharacter";
    static GET_STATIC_DATA_PATH =  "/blizzard/getStaticData";
    static GET_GUILDS_ACCOUNT_PATH =  "/blizzard/getGuildsAccount";

    constructor(){
        super(false)
    }

    public async fetchAccountCharacter(): Promise<CharacterSummaryDTO[]> {
        const response = await this.get(BlizzardHttpClient.FETCH_ACCOUNT_CHARACTER_PATH)
        return response.data as CharacterSummaryDTO[];
    }

    public async getStaticData(): Promise<StaticDataDTO> {
        const response = await this.get(BlizzardHttpClient.GET_STATIC_DATA_PATH)
        return response.data as StaticDataDTO;
    }

    public async getGuildsAccount(): Promise<GuildSummaryDTO[]> {
        const response = await this.get(BlizzardHttpClient.GET_GUILDS_ACCOUNT_PATH)
        return response.data as GuildSummaryDTO[];
    }

}