import { Accordion, AccordionDetails, AccordionSummary, Button, createStyles, Grid, makeStyles } from '@material-ui/core';
import React, { useState } from 'react'
import { BlizzardHttpClient } from '../../../api/clients/BlizzardHttpClient';
import { GuildSummaryDTO, RealmDTO } from '../../../api/Entities';
import CircularProgressScreen from '../../common/CircularProgressScreen';
import ReplayIcon from '@material-ui/icons/Replay';
import { SessionStorage } from '../../storage/SessionStorage';
import GuildPaper from '../components/GuildPaper';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const useStyles = makeStyles(() =>
  createStyles({
    root: {
      width: '100%',
    },
    gridReal:{
        width: '100%'
    },
    button:{
        margin: '10px',
        padding: '10px',
    }
  }),
);

export default function GuildsIndexPage() {

    const [downloadInProgress, setDownloadInProgress] = useState(false);
    const [value, setValue] = useState<number>(0)

    const accoutGuildsStorage = SessionStorage.getItem<GuildSummaryDTO[]>(SessionStorage.ACCOUNT_GUILDS);

    const [accoutGuilds, setAccoutGuilds] = useState<GuildSummaryDTO[] | undefined>(accoutGuildsStorage)

    const classes = useStyles();

    const reloadData = async () => {

        const blizzardHttpClient = new BlizzardHttpClient();

        setDownloadInProgress(true);

        const localAccoutGuilds = await blizzardHttpClient.getGuildsAccount();

        SessionStorage.setItem<GuildSummaryDTO[]>(SessionStorage.ACCOUNT_DATE_FETCH_GUILDS, localAccoutGuilds);
        SessionStorage.setItem<Date>(SessionStorage.ACCOUNT_DATE_FETCH_GUILDS, new Date())

        setAccoutGuilds(localAccoutGuilds);

        setDownloadInProgress(false);

    }

    const testRender = (id: number, isFavortie: boolean) => {

        if(accoutGuilds !== undefined){
            const index = accoutGuilds?.findIndex((element) => element.id === id);
            accoutGuilds[index].isFavorite = isFavortie;
            const newAccoutGuilds = accoutGuilds;
            setAccoutGuilds(newAccoutGuilds)

            SessionStorage.setItem(SessionStorage.ACCOUNT_GUILDS, newAccoutGuilds);

            setValue(value + 1)
        }

    }

    accoutGuilds?.sort((a,b) => {
        const result: boolean = a.realmDTO.slug > b.realmDTO.slug;
        if(result){
            return 1;
        }else {
            return -1;
        }
    })

    const realm = accoutGuilds?.map((value) => value.realmDTO.slug);
    
    let uniqueRealm = realm?.filter((item, index, array) => {
        return array.indexOf(item) === index
      })
    
    let toto: {realmSlug: string, realmDTO: RealmDTO, guilds: GuildSummaryDTO[]}[] = []

    uniqueRealm?.forEach((value) => {
        const result = accoutGuilds?.filter(guilds => guilds.realmDTO.slug === value)
        if(result !== undefined){
            toto.push({realmSlug:value, realmDTO:result[0].realmDTO, guilds:result})
        }
    })

    const tito = toto.map((value) => {
        const realm = value.realmDTO;
        const tttoto = value.guilds.map((value: GuildSummaryDTO) =>{

            return(
                <Grid item key={value.name} sm={12} lg={6} xl={4}>
                    <GuildPaper onFavoriteToggle={testRender} guildSummary={value}/>
                </Grid>
            )
        })

        return (
            <Accordion key={realm.slug}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    {realm.localizedStringDTO.en_US}
                </AccordionSummary>
                <AccordionDetails>
                    <Grid container className={classes.gridReal}>
                        {tttoto}
                    </Grid>
                </AccordionDetails>
            </Accordion>
        )
        
    })

    const list = () => {
        const favoriteGuilds = accoutGuilds?.filter(element => element.isFavorite === true);

        const favoriteGuildsPaper = favoriteGuilds?.map((element) => {
            const key = element.realmDTO.slug + "-" + element.name;
            return(
                <Grid item key={key} sm={12} lg={6} xl={4}>
                    <GuildPaper onFavoriteToggle={testRender} guildSummary={element}/>
                </Grid>
            )
        })

        return (
            <Accordion key='favoris' defaultExpanded={true}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    {'Favoris'}
                </AccordionSummary>
                <AccordionDetails>
                    <Grid container className={classes.gridReal}>
                        {favoriteGuildsPaper}
                    </Grid>
                </AccordionDetails>
            </Accordion>
        )
    }

    const sessionDate = SessionStorage.getItem<Date>(SessionStorage.ACCOUNT_DATE_FETCH_GUILDS)

    const fetchingDate = sessionDate !== undefined ? new Date(sessionDate) : new Date()

    return(
        <React.Fragment>
            <Grid container direction="column" alignItems="center">
                <Grid item>
                    <Button className={classes.button} variant="contained" color="primary" onClick={reloadData} startIcon={<ReplayIcon />}>{"Reload Data"}</Button>
                </Grid>
                <Grid item>
                    {"Last update : " + fetchingDate.toLocaleDateString() + " - " + fetchingDate.toLocaleTimeString()}
                </Grid>
                <Grid item className={classes.root}>
                    {list()}
                    {tito}
                </Grid>
            </Grid>
            <CircularProgressScreen open={downloadInProgress}>{"Download in progress"}</CircularProgressScreen>
        </React.Fragment>
    )
}