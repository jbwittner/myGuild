import { StaticDataDTO } from '../Entities'
import HttpClient from './HttpClient'

export class BlizzardHttpClient extends HttpClient {
  constructor() {
    super(false)
  }

  public async getStaticData(): Promise<StaticDataDTO> {
    const response = await this.get('/blizzard/getStaticData')
    return response.data as StaticDataDTO
  }
}
