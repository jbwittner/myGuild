import React from 'react'
import { CharacterSummaryDTO, StaticDataDTO } from '../../../api/Entities';
import { SessionStorage } from '../../storage/SessionStorage';

export default function HomePage() {

    const staticData = SessionStorage.getItem<StaticDataDTO>(SessionStorage.STATIC_DATA);
    const accountCharacters = SessionStorage.getItem<any>(SessionStorage.ACCOUNT_CHARACTERS);

    console.log(staticData)
    console.log(accountCharacters.characterSummaryDTOs)
    
    const test = accountCharacters.characterSummaryDTOs as CharacterSummaryDTO[]
    
    if(accountCharacters !== undefined){
        console.log(test[0])
    }

    const tata = test.map((value) => 
        <div key={value.name}>
            {value.name}
        </div>
        
    )
    

    return(
        <div>
            {tata}
        </div>

    )

}