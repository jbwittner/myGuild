export class LocalStorage {
  public static getItem<T>(key: string): T | undefined {
    let item: string | null = window.localStorage.getItem(key)
    if (item !== null) {
      return JSON.parse(item) as T
    } else {
      return undefined
    }
  }

  public static setItem<T>(key: string, value: T): void {
    window.localStorage.setItem(key, JSON.stringify(value))
  }

  public static removeItem(key: string): void {
    window.localStorage.removeItem(key)
  }

  public static clear(): void {
    window.localStorage.clear()
  }
}
