import { Button, createStyles, Grid, makeStyles, Paper, Typography } from '@material-ui/core';
import React, { useEffect, useState } from 'react'
import { CharacterSummaryDTO, FactionDTO, PlayableClassDTO, PlayableRaceDTO } from '../../../api/Entities';
import { getClassFromIndex, getFactionFromType, getRaceFromIndex } from '../../common/GetterStaticData';
import { Faction } from '../../common/Theme'
import StarIcon from '@material-ui/icons/Star';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import { BlizzardHttpClient } from '../../../api/clients/BlizzardHttpClient';

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
    name: {
        fontSize: '3vw'
    },
    image: {
        borderRadius: '5%'
    },
    iconClass: {
        borderRadius: '5%'
    }
  }),
);

export interface CharacterPaperProps {
    characterSummary: CharacterSummaryDTO;
    onFavoriteToggle(id: number, isFavorite: boolean): any; // eslint-disable-line no-unused-vars
}

export default function CharacterPaper(props: CharacterPaperProps){

    const characterSummary: CharacterSummaryDTO = props.characterSummary;

    const [isFavorite, setIsFavorite] = useState(props.characterSummary.isFavorite);

    useEffect(() => {
        setIsFavorite(props.characterSummary.isFavorite);
    }, [props]);

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

    const lastLoginDate = new Date(characterSummary.lastLoginTimestamp);

    let starIcon;
    
    if(isFavorite === true){
        starIcon = <StarIcon />
    } else {
        starIcon = <StarBorderIcon />
    }

    const toggleFavorite = async () => {
        setIsFavorite(!isFavorite)

        const blizzardHttpClient = new BlizzardHttpClient();

        await blizzardHttpClient.setFavoriteCharacter(props.characterSummary.id, !isFavorite)

        props.onFavoriteToggle(props.characterSummary.id, !isFavorite)
    }

    return(
        <Paper className={classNamePaper} elevation={3} variant="outlined" >
            <Grid container direction="column">
                <Grid container justify="space-between" alignItems="center">
                    <Grid item>
                        <Typography variant="h2" className={classes.name}>{characterSummary.name.substring(0,14)}</Typography>
                    </Grid>
                    <Grid item>
                        <img className={classes.iconClass} src={playableClass?.mediaURL}/>
                    </Grid>
                </Grid>
                <Grid container direction="row" spacing={1} justify="flex-start" alignItems="center">
                    <Grid item >
                        <img className={classes.image} src={characterSummary.insertUrl}/>
                    </Grid>
                    <Grid item direction="column">
                        <Grid item>
                            {"lvl : " + characterSummary.level}
                        </Grid>
                        <Grid item>
                            {"guildName : " + characterSummary.guildName}
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
                            {"Last login time : " + lastLoginDate.toLocaleDateString() + " - " + lastLoginDate.toLocaleTimeString() }
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item>
                    <Button variant="contained" color="secondary" startIcon={starIcon} onClick={toggleFavorite}>Favorite</Button>
                </Grid>
            </Grid>
        </Paper>
    )
}