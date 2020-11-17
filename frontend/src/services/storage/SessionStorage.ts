
export class SessionStorage{

    public static DATA_DOWNLOADED: string = "MYGUILD.DATA_DOWNLOADED";
    public static STATIC_DATA: string = "MYGUILD.STATIC_DATA";
    public static ACCOUNT_CHARACTERS: string = "MYGUILD.ACCOUNT_CHARACTERS";
    public static ACCOUNT_GUILDS: string = "MYGUILD.ACCOUNT_GUILDS";
    public static ACCOUNT_DATE_FETCH_CHARACTERS: string = "MYGUILD.ACCOUNT_DATE_FETCH_CHARACTERS";
    public static ACCOUNT_DATE_FETCH_GUILDS: string = "MYGUILD.ACCOUNT_DATE_FETCH_GUILDS";

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