import { createStyles, Grid, makeStyles, Paper, Typography } from '@material-ui/core';
import React from 'react'
import { FactionDTO, GuildSummaryDTO } from '../../../api/Entities';
import { getFactionFromType } from '../../common/GetterStaticData';
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

export interface GuildPaperProps {
    guildSummary: GuildSummaryDTO;
}

export default function GuildPaper(props: GuildPaperProps){

    const guildSummary: GuildSummaryDTO = props.guildSummary;

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

    return(
        <Paper className={classNamePaper}  elevation={3} variant="outlined" >
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
            </Grid>
        </Paper>
    )
}