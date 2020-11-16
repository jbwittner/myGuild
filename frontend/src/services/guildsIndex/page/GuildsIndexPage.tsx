import { Button } from '@material-ui/core';
import React, { useEffect, useState } from 'react'
import { BlizzardHttpClient } from '../../../api/clients/BlizzardHttpClient';
import { GuildSummaryDTO } from '../../../api/Entities';
import CircularProgressScreen from '../../common/CircularProgressScreen';

export default function GuildsIndexPage() {

    const [downloadInProgress, setDownloadInProgress] = useState(false);
    const [data, setData]= useState<GuildSummaryDTO[] | null>(null);

    useEffect(() => {
        fetchGuilds()
    }, [])

    const fetchGuilds = async() => {
        console.log("fetchGuilds")
        const blizzardHttpClient = new BlizzardHttpClient();

        setDownloadInProgress(true);

        const data = await blizzardHttpClient.getGuildsAccount()

        console.log(data.join())

        setData(data)


        setDownloadInProgress(false);

        
    }

    console.log("render")

    const plotGuilds = (data: GuildSummaryDTO[]) => {

        const plot = data.map((guild) => {
            return plotGuild(guild)
        })

        return(
            <div>
                {plot}
            </div>
        )

    }

    const plotGuild = (data: GuildSummaryDTO) => {
        return(
            <div>
                <div>
                    {"id :" + data.id}
                </div>
                <div>
                    {"indexFaction :" + data.indexFaction}
                </div>
                <div>
                    {"isGuildMaster :" + data.isGuildMaster}
                </div>
                <div>
                    {"name :" + data.name}
                </div>
                <div>
                    {"realmDTO.slug :" + data.realmDTO.slug}
                </div>
                <div>
                    {"useApplication :" + data.useApplication}
                </div>
            </div>
        )
    }

    
    
    return(
        <React.Fragment>
            <Button onClick={fetchGuilds}>{"Test"}</ Button>
            <div>
                {"jdfslkfj"}
                {console.log(data)}
                {(data !== null) && plotGuilds(data)}
            </div>
            <CircularProgressScreen open={downloadInProgress}>{"Download in progress"}</CircularProgressScreen>
        </React.Fragment>
    )
}