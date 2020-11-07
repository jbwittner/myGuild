import { FactionDTO, PlayableClassDTO, PlayableRaceDTO, StaticDataDTO } from '../../api/Entities';
import { SessionStorage } from '../storage/SessionStorage';

export function getClassFromIndex(index: number){

    const staticData = SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA);

    const playableClassDTO : PlayableClassDTO | undefined = staticData?.playableClassDTOs.find(element => element.index === index)

    return playableClassDTO;

}

export function getRaceFromIndex(index: number){

    const staticData = SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA);

    const playableRaceDTO : PlayableRaceDTO | undefined = staticData?.playableRaceDTOs.find(element => element.index === index)

    return playableRaceDTO;

}

export function getFactionFromType(index: number){

    const staticData = SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA);

    const factionDTO : FactionDTO | undefined = staticData?.factionDTOs.find(element => element.index === index)

    return factionDTO;

}