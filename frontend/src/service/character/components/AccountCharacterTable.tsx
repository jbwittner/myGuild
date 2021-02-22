import {
  CellParams,
  CellValue,
  ColDef,
  DataGrid,
  GridToolbar,
  SortDirection,
  ValueFormatterParams,
} from '@material-ui/data-grid'
import React, { useEffect, useState } from 'react'
import {
  CharacterData,
  CharacterSummaryDTO,
  FavoriteCharacterParameter,
  PlayableClassDTO,
  PlayableRaceDTO,
} from '../../../api/Entities'
import { CharacterHttpClient } from '../../../api/httpclient/CharacterHttpClient'
import { CustomGridLoadingOverlay } from '../../common/CustomGridLoadingOverlay'
import {
  GetCovenantDTO,
  GetFactionDTO,
  GetPlayableClassDTO,
  GetPlayableRaceDTO,
} from '../../common/GetStaticData'
import { PlayableClassCell } from '../../common/PlayableClassCell'
import { StarFavorite } from './StarFavorite'

export interface AccountCharacterTableProps {
  data: CharacterSummaryDTO[]
  loading?: boolean | undefined
}

export default function AccountCharacterTable(
  props: AccountCharacterTableProps,
): JSX.Element {
  console.log('render AccountCharacterTable')

  const [characterDatas, setCharacterDatas] = useState<CharacterData[]>(
    new Array<CharacterData>(),
  )

  useEffect(() => {
    const data = props.data.map((value: CharacterSummaryDTO) => {
      const covenant =
        value.indexCovenant === null
          ? null
          : GetCovenantDTO(value.indexCovenant)
      const faction = GetFactionDTO(value.indexFaction)
      const playableRace = GetPlayableRaceDTO(value.indexPlayableRace)
      const playableClass = GetPlayableClassDTO(value.indexPlayableClass)

      const result: CharacterData = {
        id: value.id,
        name: value.name,
        level: value.level,
        guildName: value.guildName,
        realm: value.realmDTO.localizedStringDTO.en_GB,
        playableClass: playableClass,
        playableRace: playableRace,
        faction: faction.localizedStringDTO.en_GB,
        averageItemLevel: value.averageItemLevel,
        equippedItemLevel: value.equippedItemLevel,
        covenant: covenant?.localizedStringDTO.en_GB,
        renownLevel: value.renownLevel,
        lastLoginTimestamp: value.lastLoginTimestamp,
        isFavorite: value.isFavorite,
      }

      return result
    })

    setCharacterDatas(data)
  }, [props])

  const updateFavorite = (id: number, isFavorite: boolean) => {
    const characterHttpClient = new CharacterHttpClient()
    const parameter: FavoriteCharacterParameter = {
      id: id,
      isFavorite: isFavorite,
    }

    characterHttpClient.setFavoriteCharacter(parameter).then(() => {
      const index = characterDatas.findIndex((value: CharacterData) => {
        return value.id === id
      })
      characterDatas[index].isFavorite = isFavorite
    })
  }

  const columns: ColDef[] = [
    {
      field: 'isFavorite',
      headerName: ' ',
      renderCell: (params: CellParams) => (
        <StarFavorite
          updateFavorite={updateFavorite}
          isFavorite={params.value as boolean}
          id={params.row['id'] as number}
        />
      ),
    },
    { field: 'name', headerName: 'Name', width: 150 },
    { field: 'level', headerName: 'Level', width: 150 },
    { field: 'guildName', headerName: 'Guild Name', width: 200 },
    { field: 'realm', headerName: 'Realm', width: 200 },
    {
      field: 'playableClass',
      headerName: 'Playable Class',
      width: 200,
      filterable: false,
      renderCell: (params: CellParams) => (
        <PlayableClassCell {...(params.value as PlayableClassDTO)} />
      ),
      sortComparator: (
        v1: CellValue,
        v2: CellValue,
        cellParams1: CellParams,
        cellParams2: CellParams,
      ) =>
        (cellParams1.value as PlayableClassDTO).index -
        (cellParams2.value as PlayableClassDTO).index,
    },
    {
      field: 'playableRace',
      headerName: 'Playable Race',
      width: 200,
      filterable: false,

      renderCell: (params: CellParams) => (
        <div>{(params.value as PlayableRaceDTO).localizedStringDTO.en_GB}</div>
      ),
      sortComparator: (
        v1: CellValue,
        v2: CellValue,
        cellParams1: CellParams,
        cellParams2: CellParams,
      ) =>
        (cellParams1.value as PlayableRaceDTO).index -
        (cellParams2.value as PlayableRaceDTO).index,
    },
    { field: 'faction', headerName: 'Faction', width: 100, hide: true },
    {
      field: 'averageItemLevel',
      headerName: 'Average ILVL',
      width: 150,
      hide: true,
    },
    { field: 'equippedItemLevel', headerName: 'Equipped ILVL', width: 200 },
    { field: 'covenant', headerName: 'Covenant', width: 150 },
    { field: 'renownLevel', headerName: 'Renown LVL', width: 200 },
    {
      field: 'lastLoginTimestamp',
      headerName: 'Last login date',
      width: 150,
      valueFormatter: (params: ValueFormatterParams) =>
        new Date(params.value as number).toLocaleDateString(),
      hide: true,
    },
  ]
  return (
    <div style={{ width: '100%' }}>
      <DataGrid
        rows={characterDatas}
        columns={columns}
        autoHeight
        density="compact"
        hideFooter={true}
        sortModel={[
          {
            field: 'isFavorite',
            sort: 'desc' as SortDirection,
          },
        ]}
        components={{
          LoadingOverlay: CustomGridLoadingOverlay,
          Toolbar: GridToolbar,
        }}
        loading={props.loading}
      />
    </div>
  )
}
