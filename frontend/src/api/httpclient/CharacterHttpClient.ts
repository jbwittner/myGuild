import { CharacterSummaryDTO, FavoriteCharacterParameter } from '../Entities'
import HttpClient from './HttpClient'

export class CharacterHttpClient extends HttpClient {
  constructor() {
    super(false)
  }

  public async fetchCharacterAccount(): Promise<CharacterSummaryDTO[]> {
    const response = await this.get('/character/fetchCharacterAccount')
    return response.data as CharacterSummaryDTO[]
  }

  public async getCharacterAccount(): Promise<CharacterSummaryDTO[]> {
    const response = await this.get('/character/getCharacterAccount')
    return response.data as CharacterSummaryDTO[]
  }

  public async setFavoriteCharacter(
    parameter: FavoriteCharacterParameter,
  ): Promise<void> {
    await this.post('/character/setFavoriteCharacter', parameter)
  }
}
