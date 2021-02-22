import { Avatar, Grid, makeStyles } from '@material-ui/core'
import React from 'react'
import { PlayableClassDTO } from '../../api/Entities'

const useStyles = makeStyles({
  imageClass: {
    height: '30px',
    width: '30px',
    marginRight: '10px',
    border: '1px solid',
  },
})

const PlayableClassCell = React.memo(
  (data: PlayableClassDTO): JSX.Element => {
    const classes = useStyles()

    return (
      <div style={{ height: '100%' }}>
        <Grid
          container
          direction="row"
          justify="flex-start"
          alignItems="center"
          alignContent="center"
        >
          <Grid item style={{ height: '100%' }}>
            <Avatar
              alt={data.localizedStringDTO.en_GB}
              src={data.mediaURL}
              className={classes.imageClass}
            />
          </Grid>
          <Grid item>{data.localizedStringDTO.en_GB}</Grid>
        </Grid>
      </div>
    )
  },
)

export { PlayableClassCell }
