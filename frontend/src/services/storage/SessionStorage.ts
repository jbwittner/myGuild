
export class SessionStorage{

    public static getItem<T>(key: string): T | undefined {
        let item: string | null = window.sessionStorage.getItem(key);
        if(item !== null) {
            return JSON.parse(item) as T;
        } else {
            return undefined;
        }
    }

    public static setItem<T>(key: string, value: T): void {
        window.sessionStorage.setItem(key, JSON.stringify(value));
    }

    public static removeItem(key: string): void {
        window.sessionStorage.removeItem(key);
    }

    public static clear():void {
        window.sessionStorage.clear();
    }

}