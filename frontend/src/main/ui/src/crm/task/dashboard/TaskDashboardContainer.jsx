import React, {useEffect, useState} from 'react'
import {Button, Grid, GridColumn, GridRow, Header} from "semantic-ui-react";
import {TaskDashboard} from "./TaskDashboard";

export const TaskDashboardContainer = ({getTasks}) => {

    const [state, setState] = useState({tasks: [], isLoading: false})

    const refresh = () => {
        getTasks && setState({tasks: [], isLoading: true})
        getTasks && getTasks().then(tasks => {
            console.log(tasks)
            setState({tasks, isLoading: false})
        })
    }

    useEffect(() => {
        refresh()
    }, [])

    return (
        <>

            <Grid>
                <GridRow>
                    <Header as={'h2'}>
                        Task Dashboard
                    </Header>
                    <GridColumn>
                        <Button onClick={refresh} loading={state.isLoading}>Refresh</Button>
                    </GridColumn>
                </GridRow>
            </Grid>
            <TaskDashboard tasks={state.tasks}/>
        </>
    )

}