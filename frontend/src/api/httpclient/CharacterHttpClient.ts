import { CharacterSummaryDTO } from '../Entities'
import HttpClient from './HttpClient'

export class CharacterHttpClient extends HttpClient {
  constructor() {
    super(false)
  }

  public async fetchAccountCharacter(): Promise<CharacterSummaryDTO[]> {
    const response = await this.get('/character/fetchAccountCharacter')
    return response.data as CharacterSummaryDTO[]
  }

  public async getAccountCharacter(): Promise<CharacterSummaryDTO[]> {
    const response = await this.get('/character/getAccountCharacter')
    return response.data as CharacterSummaryDTO[]
  }
}
