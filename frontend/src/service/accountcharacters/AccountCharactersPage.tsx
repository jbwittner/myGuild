import { Button, createStyles, makeStyles } from '@material-ui/core'
import React, { useCallback, useEffect, useMemo, useState } from 'react'
import { CharacterSummaryDTO } from '../../api/Entities'
import { CharacterHttpClient } from '../../api/httpclient/CharacterHttpClient'
import AccountCharacterTable from './components/AccountCharacterTable'

const useStyles = makeStyles(() =>
  createStyles({
    button: {
      margin: '5px',
    },
  }),
)

export default function AccountCharactersPage(): JSX.Element {
  const classes = useStyles()

  const characterHttpClient = new CharacterHttpClient()
  const [characters, setCharacters] = useState<CharacterSummaryDTO[]>(
    new Array<CharacterSummaryDTO>(),
  )
  const [isLoading, setIsLoading] = useState<boolean>(false)

  useEffect(() => {
    setIsLoading(true)
    characterHttpClient
      .getCharacterAccount()
      .then((data: CharacterSummaryDTO[]) => {
        setCharacters(data)
        setIsLoading(false)
      })
  }, [])

  const onClickButton = useCallback(() => {
    setIsLoading(true)

    characterHttpClient
      .fetchCharacterAccount()
      .then((data: CharacterSummaryDTO[]) => {
        setCharacters(data)
        setIsLoading(false)
      })
  }, [])

  const accountCharacterTable = useMemo(() => {
    return <AccountCharacterTable data={characters} loading={isLoading} />
  }, [characters, isLoading])

  return (
    <div>
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={onClickButton}
      >
        Fetch characters from Blizzard
      </Button>
      {accountCharacterTable}
    </div>
  )
}
