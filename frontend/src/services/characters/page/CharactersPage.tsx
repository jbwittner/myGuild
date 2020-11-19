import { Accordion, AccordionDetails, AccordionSummary, Button, createStyles, Grid, makeStyles } from '@material-ui/core';
import React, { useState } from 'react'
import { CharacterSummaryDTO, RealmDTO } from '../../../api/Entities';
import { SessionStorage } from '../../storage/SessionStorage';
import CharacterPaper from '../components/CharacterPaper';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ReplayIcon from '@material-ui/icons/Replay';
import { BlizzardHttpClient } from '../../../api/clients/BlizzardHttpClient';
import CircularProgressScreen from '../../common/CircularProgressScreen';


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

export default function CharactersPage() {

    console.log("render CharactersPage")

    const [downloadInProgress, setDownloadInProgress] = useState(false);
    const accountCharactersStorage = SessionStorage.getItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS);
    const [accountCharacters, setAccountCharacters] = useState<CharacterSummaryDTO[] | undefined>(accountCharactersStorage)
    const [value, setValue] = useState<number>(0)

    const classes = useStyles();

    const favoriteCharacters = accountCharacters?.filter(element => element.favorite === true);

    const testRender = (id: number, isFavortie: boolean) => {
        console.log(id)
        console.log(isFavortie)

        if(accountCharacters !== undefined){
            const index = accountCharacters?.findIndex((element) => element.id === id);
            accountCharacters[index].favorite = isFavortie;
            const newAccountCharacters = accountCharacters;
            setAccountCharacters(newAccountCharacters)

            console.log(newAccountCharacters)
            SessionStorage.setItem(SessionStorage.ACCOUNT_CHARACTERS, newAccountCharacters);

            setValue(value + 1)
        }

    }

    favoriteCharacters?.sort((a,b) => {
        if(a.name < b.name){
            return -1;
        } else {
            return 1;
        }
    })

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
    
    let toto: {realmSlug: string, realmDTO: RealmDTO, characters: CharacterSummaryDTO[]}[] = []

    uniqueRealm?.forEach((value) => {
        const result = accountCharacters?.filter(character => character.realmDTO.slug === value)
        if(result !== undefined){
            toto.push({realmSlug:value, realmDTO:result[0].realmDTO, characters:result})
        }
    })

    const tito = toto.map((value) => {
        const realm = value.realmDTO;
        value.characters.sort((a,b) => {
            if(a.name < b.name){
                return -1;
            } else {
                return 1;
            }
        })
        const tttoto = value.characters.map((value: CharacterSummaryDTO) =>{

            return(
                <Grid item key={value.name} sm={12} lg={6} xl={4}>
                    <CharacterPaper onFavoriteToggle={testRender} characterSummary={value}/>
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

        favoriteCharacters

        const favoriteCharactersPaper = favoriteCharacters?.map((element) => {
            const key = element.realmDTO.slug + "-" + element.name;
            return(
                <Grid item key={key} sm={12} lg={6} xl={4}>
                    <CharacterPaper onFavoriteToggle={testRender} characterSummary={element}/>
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
                        {favoriteCharactersPaper}
                    </Grid>
                </AccordionDetails>
            </Accordion>
        )
    }

    const reloadData = async () => {

        const blizzardHttpClient = new BlizzardHttpClient();

        setDownloadInProgress(true);

        const accountCharacters = await blizzardHttpClient.fetchAccountCharacter();

        SessionStorage.setItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS, accountCharacters);
        SessionStorage.setItem<Date>(SessionStorage.ACCOUNT_DATE_FETCH_CHARACTERS, new Date())

        setAccountCharacters(accountCharacters);

        setDownloadInProgress(false);

    }

    const sessionDate = SessionStorage.getItem<Date>(SessionStorage.ACCOUNT_DATE_FETCH_CHARACTERS)

    const fetchingDate = sessionDate !== undefined ? new Date(sessionDate) : new Date()


    return(
        <div className={classes.root}>
            <Grid container direction="column" alignItems="center" className={classes.root}> 
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
        </div>
    )

}