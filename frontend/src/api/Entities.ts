export interface AddUserParameter {
  nickName: string
  email: string
}

export interface FavoriteCharacterParameter {
  id: number
  isFavorite: boolean
}

export interface BackendError {
  details: string
  exception: string
  message: string
  timestamp: string
}

/*
DTO
*/

export interface LocalizedStringDTO {
  en_US: string
  es_MX: string
  pt_BR: string
  de_DE: string
  en_GB: string
  es_ES: string
  fr_FR: string
  it_IT: string
  ru_RU: string
  ko_KR: string
  zh_TW: string
  zh_CN: string
}

export interface PlayableClassDTO {
  index: number
  mediaURL: string
  localizedStringDTO: LocalizedStringDTO
  playableSpecializationIndex: number[]
}

export interface PlayableRaceDTO {
  index: number
  localizedStringDTO: LocalizedStringDTO
  factionIndex: number
}

export interface PlayableSpecializationDTO {
  index: number
  mediaURL: string
  specializationRoleType: string
  localizedStringDTO: LocalizedStringDTO
}

export interface SpecializationRoleDTO {
  type: string
  localizedStringDTO: LocalizedStringDTO
}

export interface FactionDTO {
  index: number
  type: string
  localizedStringDTO: LocalizedStringDTO
}

export interface CovenantDTO {
  index: number
  localizedStringDTO: LocalizedStringDTO
}

export interface StaticDataDTO {
  playableClassDTOs: PlayableClassDTO[]
  playableRaceDTOs: PlayableRaceDTO[]
  playableSpecializationDTOs: PlayableSpecializationDTO[]
  specializationRoleDTOs: SpecializationRoleDTO[]
  factionDTOs: FactionDTO[]
  covenantDTOs: CovenantDTO[]
}

export interface RealmDTO {
  slug: string
  localizedStringDTO: LocalizedStringDTO
}

export interface CharacterSummaryDTO {
  id: number
  name: string
  level: number
  guildName: string
  realmDTO: RealmDTO
  indexPlayableClass: number
  indexPlayableRace: number
  indexFaction: number
  averageItemLevel: number
  equippedItemLevel: number
  indexCovenant: number
  renownLevel: number
  lastLoginTimestamp: number
  isFavorite: boolean
}

export interface CharacterData {
  id: number
  name: string
  level: number
  guildName: string
  realm: string
  playableClass: PlayableClassDTO
  playableRace: PlayableRaceDTO
  faction: string
  averageItemLevel: number
  equippedItemLevel: number
  covenant: string | undefined
  renownLevel: number
  lastLoginTimestamp: number
  isFavorite: boolean
}

export interface GuildSummaryDTO {
  id: number
  name: string
  useApplication: boolean
  indexFaction: number
  realmDTO: RealmDTO
  achievementPoints: number
  createdTimestamp: number
  memberCount: number
}
