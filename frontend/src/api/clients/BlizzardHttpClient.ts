import { CharacterSummaryDTO, StaticDataDTO } from "../Entities"
import { HttpClient } from "./HttpClient"

export class BlizzardHttpClient extends HttpClient {

    constructor(){
        super(false)
    }

    public async fetchAccountCharacter(): Promise<CharacterSummaryDTO[]> {
        const response = await this.get("/blizzard/fetchAccountCharacter")
        return response.data as CharacterSummaryDTO[];
    }

    public async getStaticData(): Promise<StaticDataDTO> {
        const response = await this.get("/blizzard/getStaticData")
        return response.data as StaticDataDTO;
    }

}