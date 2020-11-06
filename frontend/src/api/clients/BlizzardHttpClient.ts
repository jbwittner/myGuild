import { CharacterSummaryDTO, StaticDataDTO } from "../Entities"
import { HttpClient } from "./HttpClient"

export class BlizzardHttpClient extends HttpClient {

    static FETCH_ACCOUNT_CHARACTER_PATH =  "/blizzard/fetchAccountCharacter";
    static FETCH_STATIC_DATA_PATH =  "/blizzard/getStaticData";

    constructor(){
        super(false)
    }

    public async fetchAccountCharacter(): Promise<CharacterSummaryDTO[]> {
        const response = await this.get(BlizzardHttpClient.FETCH_ACCOUNT_CHARACTER_PATH)
        return response.data as CharacterSummaryDTO[];
    }

    public async getStaticData(): Promise<StaticDataDTO> {
        const response = await this.get(BlizzardHttpClient.FETCH_STATIC_DATA_PATH)
        return response.data as StaticDataDTO;
    }

}