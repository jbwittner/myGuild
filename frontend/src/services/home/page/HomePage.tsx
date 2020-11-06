import React from 'react'
import { CharacterSummaryDTO, StaticDataDTO } from '../../../api/Entities';
import { SessionStorage } from '../../storage/SessionStorage';

export default function HomePage() {

    const staticData = SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA);
    const accountCharacters = SessionStorage.getItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS);

    console.log(staticData)
    console.log(accountCharacters as CharacterSummaryDTO[])
    
    const test = accountCharacters as CharacterSummaryDTO[]
    
    if(accountCharacters !== undefined){
        console.log(test[0])
    }
    

    return(
        <div>

        </div>

    )

}