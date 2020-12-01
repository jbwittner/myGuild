import { Button, createStyles, Grid, makeStyles, Paper, Typography } from '@material-ui/core';
import React, { useEffect, useState } from 'react'
import { FactionDTO, GuildSummaryDTO } from '../../../api/Entities';
import { getFactionFromType } from '../../common/GetterStaticData';
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
    image: {
        display: 'flex',
        justifyContent: 'center'
    },
    name: {

    }
  }),
);

export interface GuildPaperProps {
    guildSummary: GuildSummaryDTO;
    onFavoriteToggle(id: number, isFavorite: boolean): any; // eslint-disable-line no-unused-vars
}

export default function GuildPaper(props: GuildPaperProps){

    const guildSummary: GuildSummaryDTO = props.guildSummary;

    const [isFavorite, setIsFavorite] = useState(props.guildSummary.isFavorite);

    useEffect(() => {
        setIsFavorite(props.guildSummary.isFavorite);
    }, [props]);

    const classes = useStyles();

    const faction: FactionDTO | undefined = getFactionFromType(guildSummary.indexFaction);

    let classNamePaper = classes.paperNeutral;

    if(faction?.type === Faction.ALIANCE.type){
        classNamePaper = classes.paperAlliance;
    } else if (faction?.type === Faction.HORDE.type){
        classNamePaper = classes.paperHorde;
    } else if (faction?.type === Faction.NEUTRAL.type){
        classNamePaper = classes.paperNeutral;
    }

    let starIcon;
    
    if(isFavorite === true){
        starIcon = <StarIcon />
    } else {
        starIcon = <StarBorderIcon />
    }

    const toggleFavorite = async () => {
        setIsFavorite(!isFavorite)

        const blizzardHttpClient = new BlizzardHttpClient();

        await blizzardHttpClient.setFavoriteGuild(props.guildSummary.id, !isFavorite)

        props.onFavoriteToggle(props.guildSummary.id, !isFavorite)
    }

    return(
        <Paper className={classNamePaper}  elevation={3} variant="outlined">
            <Grid container item direction="column" xs={12} sm={6}>
                <Grid item>
                    <Typography variant="h2" className={classes.name}>{guildSummary.name}</Typography>
                </Grid>
                <Grid item>
                    {"faction : " + faction?.localizedStringDTO.en_US}
                </Grid>
                <Grid item>
                    {"realm : " + guildSummary.realmDTO.localizedStringDTO.en_US}
                </Grid>
                <Grid item>
                    {"isGuildMaster : " + guildSummary.isGuildMaster}
                </Grid>
                <Grid item>
                    {"useApplication : " + guildSummary.useApplication}
                </Grid>
                <Grid item>
                    {"achievementPoints : " + guildSummary.achievementPoints}
                </Grid>
                <Grid item>
                    {"createdTimestamp : " + guildSummary.createdTimestamp}
                </Grid>
                <Grid item>
                    {"Date : " + new Date(guildSummary.createdTimestamp).toLocaleDateString()}
                </Grid>
                <Grid item>
                    {"memberCount : " + guildSummary.memberCount}
                </Grid>
                {guildSummary.isGuildMaster === true && guildSummary.useApplication === false &&
                    <Grid item>
                        <Button variant="contained" color="secondary" startIcon={starIcon} >Init application</Button>
                    </Grid>
                }
                <Grid item>
                    <Button variant="contained" color="secondary" startIcon={starIcon} onClick={toggleFavorite}>Favorite</Button>
                </Grid>
            </Grid>
        </Paper>
    )
}