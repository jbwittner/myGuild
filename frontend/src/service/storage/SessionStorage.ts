import { StaticDataDTO } from '../../api/Entities'

export class SessionStorage {
  public static STATIC_DATA = 'MYGUILD.STATIC_DATA'

  public static getItem<T>(key: string): T | undefined {
    const item: string | null = window.sessionStorage.getItem(key)
    if (item !== null) {
      return JSON.parse(item) as T
    } else {
      return undefined
    }
  }

  public static setItem<T>(key: string, value: T): void {
    window.sessionStorage.setItem(key, JSON.stringify(value))
  }

  public static removeItem(key: string): void {
    window.sessionStorage.removeItem(key)
  }

  public static clear(): void {
    window.sessionStorage.clear()
  }
}

export function getStaticData(): StaticDataDTO | undefined {
  return SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA)
}
