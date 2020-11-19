export interface AddUserParameter{
    nickName: string;
    email: string;
}

export interface LocalizedStringDTO{
    en_US: string;
    es_MX: string;
    pt_BR: string;
    de_DE: string;
    en_GB: string;
    es_ES: string;
    fr_FR: string;
    it_IT: string;
    ru_RU: string;
    ko_KR: string;
    zh_TW: string;
    zh_CN: string;
}

export interface PlayableClassDTO{
    index: number;
    mediaURL: string;
    localizedStringDTO: LocalizedStringDTO;
}

export interface PlayableRaceDTO{
    index: number;
    localizedStringDTO: LocalizedStringDTO;
}

export interface PlayableSpecializationDTO{
    index: number;
    mediaURL: string;
    specializationRoleType: string;
    localizedStringDTO: LocalizedStringDTO;
}

export interface SpecializationRoleDTO{
    type: string;
    localizedStringDTO: LocalizedStringDTO;
}

export interface FactionDTO{
    index: number;
    type: string;
    localizedStringDTO: LocalizedStringDTO;
}

export interface RealmDTO{
    slug: string;
    localizedStringDTO: LocalizedStringDTO;
}

export interface StaticDataDTO{
    playableClassDTOs: PlayableClassDTO[];
    playableRaceDTOs: PlayableRaceDTO[];
    playableSpecializationDTOs: PlayableSpecializationDTO[];
    factionDTOs: FactionDTO[];
}

export interface GuildSummaryDTO{
    id: number;
    name: string;
    useApplication: boolean;
    isGuildMaster: boolean;
    indexFaction: number;
    realmDTO: RealmDTO;
    achievementPoints: number;
    createdTimestamp: number;
    memberCount: number;
}

export interface CrestDTO{
    emblem: CrestPartDTO;
    border: CrestPartDTO;
    background: ColorDTO;
}

export interface CrestPartDTO{
    url: string;
    color: ColorDTO;
}

export interface ColorDTO{
    r: number;
    g: number;
    b: number;
    a: number;
}

export interface CharacterSummaryDTO{
    id: number;
    name: string;
    level: number;
    guildName: string;
    realmDTO: RealmDTO;
    indexPlayableClass: number;
    indexPlayableRace: number;
    indexFaction: number;
    avatarUrl: string;
    insertUrl: string;
    averageItemLevel: number;
    equippedItemLevel: number;
    lastLoginTimestamp: number;
    favorite: boolean;
}