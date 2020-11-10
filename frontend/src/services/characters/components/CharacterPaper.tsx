import { createStyles, Grid, makeStyles, Paper, Typography } from '@material-ui/core';
import React from 'react'
import { CharacterSummaryDTO, FactionDTO, PlayableClassDTO, PlayableRaceDTO } from '../../../api/Entities';
import { getClassFromIndex, getFactionFromType, getRaceFromIndex } from '../../common/GetterStaticData';
import { Faction } from '../../common/Theme'

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

export interface CharacterPaperProps {
    characterSummary: CharacterSummaryDTO;
}

export default function CharacterPaper(props: CharacterPaperProps){

    const characterSummary: CharacterSummaryDTO = props.characterSummary;

    const classes = useStyles();

    const playableClass: PlayableClassDTO | undefined = getClassFromIndex(characterSummary.indexPlayableClass);
    const playableRace: PlayableRaceDTO | undefined = getRaceFromIndex(characterSummary.indexPlayableRace);
    const faction: FactionDTO | undefined = getFactionFromType(characterSummary.indexFaction);

    let classNamePaper = classes.paperNeutral;

    if(faction?.type === Faction.ALIANCE.type){
        classNamePaper = classes.paperAlliance;
    } else if (faction?.type === Faction.HORDE.type){
        classNamePaper = classes.paperHorde;
    } else if (faction?.type === Faction.NEUTRAL.type){
        classNamePaper = classes.paperNeutral;
    }

    return(
        <Paper className={classNamePaper}  elevation={3} variant="outlined" >
            <Grid container direction="row" justify="center" alignItems="center" spacing={1}>
                <Grid container item direction="column" xs={12} sm={6}>
                    <Grid item>
                        <Typography variant="h2" className={classes.name}>{characterSummary.name}</Typography>
                    </Grid>
                    <Grid item className={classes.image}>
                        <img  src={characterSummary.insertUrl} />
                    </Grid>
                </Grid>
                <Grid container item direction="column" xs={12} sm={6}>
                    <Grid item>
                        {"lvl : " + characterSummary.level}
                    </Grid>
                    <Grid item>
                        {"averageItemLevel : " + characterSummary.averageItemLevel}
                    </Grid>
                    <Grid item>
                        {"equippedItemLevel : " + characterSummary.equippedItemLevel}
                    </Grid>
                    <Grid item>
                        {"realm : " + characterSummary.realmDTO.localizedStringDTO.en_US}
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
    )
}