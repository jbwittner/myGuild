import { getStaticData } from '../storage/SessionStorage'
import {
  CovenantDTO,
  FactionDTO,
  PlayableClassDTO,
  PlayableRaceDTO,
} from '../../api/Entities'

export function GetFactionDTO(indexFaction: number): FactionDTO {
  const staticData = getStaticData()

  const faction = staticData?.factionDTOs.find((value: FactionDTO) => {
    return value.index === indexFaction
  })

  const result = faction !== undefined ? faction : ({} as FactionDTO)

  return result
}

export function GetPlayableRaceDTO(indexPlayableRace: number): PlayableRaceDTO {
  const staticData = getStaticData()

  const playableRace = staticData?.playableRaceDTOs.find(
    (value: PlayableRaceDTO) => {
      return value.index === indexPlayableRace
    },
  )

  const result =
    playableRace !== undefined ? playableRace : ({} as PlayableRaceDTO)

  return result
}

export function GetPlayableClassDTO(
  indexPlayableClass: number,
): PlayableClassDTO {
  const staticData = getStaticData()

  const playableClass = staticData?.playableClassDTOs.find(
    (value: PlayableClassDTO) => {
      return value.index === indexPlayableClass
    },
  )

  const result =
    playableClass !== undefined ? playableClass : ({} as PlayableClassDTO)

  return result
}

export function GetCovenantDTO(indexCovenant: number): CovenantDTO {
  const staticData = getStaticData()

  const covenant = staticData?.covenantDTOs.find((value: CovenantDTO) => {
    return value.index === indexCovenant
  })

  const result = covenant !== undefined ? covenant : ({} as CovenantDTO)

  return result
}
