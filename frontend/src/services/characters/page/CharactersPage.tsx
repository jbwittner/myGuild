import { Accordion, AccordionDetails, AccordionSummary, createStyles, Grid, makeStyles } from '@material-ui/core';
import React from 'react'
import { CharacterSummaryDTO, RealmDTO } from '../../../api/Entities';
import { SessionStorage } from '../../storage/SessionStorage';
import CharacterPaper from '../components/CharacterPaper';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const useStyles = makeStyles(() =>
  createStyles({
    root: {
      width: '100%',
    },
    gridReal:{
        width: '100%'
    }
  }),
);

export default function CharactersPage() {

    const classes = useStyles();

    const accountCharacters = SessionStorage.getItem<CharacterSummaryDTO[]>(SessionStorage.ACCOUNT_CHARACTERS);

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
        const tttoto = value.characters.map((value: CharacterSummaryDTO) =>{

            return(
                <Grid item key={value.name} sm={12} lg={6} xl={4}>
                    <CharacterPaper characterSummary={value}/>
                </Grid>
            )
        })

        return (
            <Accordion key='kfdjmfqksjf'>
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
        return (
            <Accordion key='kfdqsddjmfqksjf' defaultExpanded={true}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    {'Favoris'}
                </AccordionSummary>
                <AccordionDetails>
                    <Grid container className={classes.gridReal}>
                        {'WIP'}
                    </Grid>
                </AccordionDetails>
            </Accordion>
        )
    }

    /*

    const tata = accountCharacters?.map((value: CharacterSummaryDTO) =>{

        return(
            <Grid item key={value.name} xs={12} sm={6} lg={6} xl={4}>
                <CharacterPaper characterSummary={value}/>
            </Grid>
        )
    })
    */
    

    return(
        <div className={classes.root}>
            {list()}
            {tito}
        </div>

    )

}