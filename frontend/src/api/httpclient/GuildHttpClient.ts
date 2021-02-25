import { GuildSummaryDTO } from '../Entities'
import HttpClient from './HttpClient'

export class GuildHttpClient extends HttpClient {
  constructor() {
    super(false)
  }

  public async getGuildFromAccount(): Promise<GuildSummaryDTO[]> {
    const response = await this.get('/guild/getGuildFromAccount')
    return response.data as GuildSummaryDTO[]
  }
}
