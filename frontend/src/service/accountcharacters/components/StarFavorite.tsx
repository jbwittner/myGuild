import { Button } from '@material-ui/core'
import { useCallback, useState } from 'react'
import React from 'react'
import StarIcon from '@material-ui/icons/Star'
import StarBorderIcon from '@material-ui/icons/StarBorder'

interface StarFavoriteProps {
  isFavorite: boolean
  id: number
  updateFavorite: (id: number, isFavorite: boolean) => void
}

const StarFavorite = React.memo(
  (props: StarFavoriteProps): JSX.Element => {
    const [isFavorite, setIsFavorite] = useState(props.isFavorite)

    const onClick = useCallback(() => {
      props.updateFavorite(props.id, !isFavorite)
      setIsFavorite(!isFavorite)
    }, [props.id, isFavorite])

    return (
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          width: '100%',
          height: '100%',
        }}
      >
        <Button onClick={onClick}>
          {isFavorite === true && <StarIcon />}
          {isFavorite === false && <StarBorderIcon />}
        </Button>
      </div>
    )
  },
)

export { StarFavorite }
