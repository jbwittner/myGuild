import {
    Button,
    CircularProgress,
  makeStyles,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { GuildSummaryDTO } from '../../api/Entities'
import { GuildHttpClient } from '../../api/httpclient/GuildHttpClient'
import { GetFactionDTO } from '../common/GetStaticData'

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
})


export default function AccountGuildsPage(): JSX.Element {
  const classes = useStyles()

  const guildHttpClient = new GuildHttpClient()

  const [guilds, setGuilds] = useState<GuildSummaryDTO[]>(
    new Array<GuildSummaryDTO>(),
  )


  useEffect(() => {
    guildHttpClient.getGuildFromAccount().then((data: GuildSummaryDTO[]) => {
      console.log(data)
      setGuilds(data)
    })
  }, [])

  const buttonUseApplication = (): JSX.Element => {
      const [inProgress ] = useState(true)

      const icon = inProgress ? <div>{'erezr'}</div> : <CircularProgress color="inherit" />
      
      return (
          <Button variant="contained" color="primary" endIcon={icon}>Use Application</Button>
      )
  }

  const giveButtonAction = (useApplication: boolean): JSX.Element => {
      return (
          <div>{useApplication ? "Yes" : buttonUseApplication()}</div>
      )
  }

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="center">name</TableCell>
            <TableCell align="center">useApplication</TableCell>
            <TableCell align="center">indexFaction</TableCell>
            <TableCell align="center">memberCount</TableCell>
            <TableCell align="center">achievementPoints</TableCell>
            <TableCell align="center">createdTimestamp</TableCell>
            <TableCell align="center">memberCount</TableCell>
            <TableCell align="center">action</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {guilds.map((row: GuildSummaryDTO) => (
            <TableRow key={row.name}>
              <TableCell component="th" scope="row" align="center">
                {row.name}
              </TableCell>
              <TableCell align="center">{row.useApplication ? "Yes" : "No"}</TableCell>
              <TableCell align="center">{GetFactionDTO(row.indexFaction).localizedStringDTO.en_GB}</TableCell>
              <TableCell align="center">{row.memberCount}</TableCell>
              <TableCell align="center">{row.realmDTO.localizedStringDTO.en_GB}</TableCell>
              <TableCell align="center">{row.achievementPoints}</TableCell>
              <TableCell align="center">{new Date(row.createdTimestamp).toLocaleDateString()}</TableCell>
              <TableCell align="center">{giveButtonAction(row.useApplication)}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  )
}
