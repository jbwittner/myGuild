import { CharacterSummaryDTO, FavoriteGuildDTO, GuildSummaryDTO, StaticDataDTO } from "../Entities"
import { HttpClient } from "./HttpClient"

export class BlizzardHttpClient extends HttpClient {

    static FETCH_ACCOUNT_CHARACTER_PATH =  "/blizzard/fetchAccountCharacter";
    static GET_STATIC_DATA_PATH =  "/blizzard/getStaticData";
    static GET_GUILDS_ACCOUNT_PATH =  "/blizzard/getGuildsAccount";
    static SET_FAVORITE_CHARACTER =  "/blizzard/setFavoriteCharacter";
    static SET_FAVORITE_GUILD =  "/blizzard/setFavoriteGuild";

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

    public async setFavoriteCharacter(id: number, isFavorite: Boolean): Promise<CharacterSummaryDTO> {
        const response = await this.post(BlizzardHttpClient.SET_FAVORITE_CHARACTER, {id, isFavorite})
        return response.data as CharacterSummaryDTO;
    }

    public async setFavoriteGuild(id: number, isFavorite: Boolean): Promise<FavoriteGuildDTO> {
        const response = await this.post(BlizzardHttpClient.SET_FAVORITE_GUILD, {id, isFavorite})
        return response.data as FavoriteGuildDTO;
    }

}