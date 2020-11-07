import { createStyles, Grid, makeStyles, Paper, Typography } from '@material-ui/core';
import React from 'react'
import { CharacterSummaryDTO, Faction, FactionDTO, PlayableClassDTO, PlayableRaceDTO } from '../../../api/Entities';
import { getClassFromIndex, getFactionFromType, getRaceFromIndex } from '../../common/GetterStaticData';
import { SessionStorage } from '../../storage/SessionStorage';

const useStyles = makeStyles(() =>
  createStyles({
    paperAlliance: {
        margin: '10px',
        padding: '10px',
        backgroundColor: Faction.ALIANCE.backgroundColor
    },
    paperHorde: {
        margin: '10px',
        padding: '10px',
        backgroundColor: Faction.HORDE.backgroundColor
    },
    paperNeutral: {
        margin: '10px',
        padding: '10px',
        backgroundColor: Faction.ALIANCE.backgroundColor
    },
    image: {
        display: 'flex',
        justifyContent: 'center'
    },
    name: {

    }
  }),
);


export default function CharactersPage() {

    const accountCharacters = SessionStorage.getItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS);

    const classes = useStyles();

    accountCharacters?.sort((a,b) => {
        const result: boolean = a.realmDTO.slug > b.realmDTO.slug;
        if(result){
            return 1;
        }else {
            return -1;
        }
    })

    const realm = accountCharacters?.map((value) => value.realmDTO.slug);
    
    let uniqueRealm = realm?.filter((item, index, array) => {
        return array.indexOf(item) === index
      })
    
    console.log(uniqueRealm)

    let toto: {realm: string, characters: CharacterSummaryDTO[]}[] = []

    uniqueRealm?.forEach((value) => {
        const result = accountCharacters?.filter(character => character.realmDTO.slug === value)
        if(result !== undefined){
            toto.push({realm:value, characters:result})
        }
    })

    console.log('toto')
    console.log(toto)
    console.log('toto')


    const tata = accountCharacters?.map((value) =>{

        const playableClass: PlayableClassDTO | undefined = getClassFromIndex(value.indexPlayableClass);
        const playableRace: PlayableRaceDTO | undefined = getRaceFromIndex(value.indexPlayableRace);
        const faction: FactionDTO | undefined = getFactionFromType(value.indexFaction);

        let classNamePaper = classes.paperNeutral;

        if(faction?.type === Faction.ALIANCE.type){
            classNamePaper = classes.paperAlliance;
        } else if (faction?.type === Faction.HORDE.type){
            classNamePaper = classes.paperHorde;
        } else if (faction?.type === Faction.NEUTRAL.type){
            classNamePaper = classes.paperNeutral;
        }

        return(
            <Grid item key={value.name} xs={12} sm={6} lg={6} xl={4}>
                <Paper className={classNamePaper}  elevation={3} variant="outlined" >
                    <Grid container direction="row" justify="center" alignItems="center" spacing={1}>
                        <Grid container item direction="column" xs={12} lg={6}>
                            <Grid item>
                                <Typography variant="h2" className={classes.name}>{value.name}</Typography>
                            </Grid>
                            <Grid item className={classes.image}>
                                <img  src={value.insertUrl} />
                            </Grid>
                        </Grid>
                        <Grid container item direction="column" xs={12} lg={6}>
                            <Grid item>
                                {"lvl : " + value.level}
                            </Grid>
                            <Grid item>
                                {"averageItemLevel : " + value.averageItemLevel}
                            </Grid>
                            <Grid item>
                                {"equippedItemLevel : " + value.equippedItemLevel}
                            </Grid>
                            <Grid item>
                                {"realm : " + value.realmDTO.localizedStringDTO.en_US}
                            </Grid>
                            <Grid item>
                                {"race : " + playableRace?.localizedStringDTO.en_US}
                            </Grid>
                            <Grid item>
                                {"class : " + playableClass?.localizedStringDTO.en_US}
                            </Grid>
                            <Grid item>
                                <img  src={playableClass?.mediaURL} />
                            </Grid>
                        </Grid>
                    </Grid>
                </Paper>
            </Grid>

        )
    }
        
    )
    

    return(
        <Grid container>
            {tata}
        </Grid>

    )

}