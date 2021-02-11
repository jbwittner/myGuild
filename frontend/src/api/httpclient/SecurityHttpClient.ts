import HttpClient from './HttpClient'

export class SecurityHttpClient extends HttpClient {
  constructor() {
    super(false)
  }

  public async connectionTest(): Promise<void> {
    await this.get('/security/connectionTest')
  }
}
