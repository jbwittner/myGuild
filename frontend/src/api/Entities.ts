export interface AddUserParameter {
  nickName: string
  email: string
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
  enUS: string
  esMX: string
  ptBR: string
  deDE: string
  enGB: string
  esES: string
  frFR: string
  itIT: string
  ruRU: string
  koKR: string
  zhTW: string
  zhCN: string
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
