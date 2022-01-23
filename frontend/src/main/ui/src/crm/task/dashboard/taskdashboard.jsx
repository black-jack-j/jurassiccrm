import React from 'react';
import {TaskCard} from "./TaskCard";
import {Button, CardGroup, Grid, GridColumn, GridRow, Header} from "semantic-ui-react";

export const TaskDashboard = ({tasks, isLoading, refresh, onTaskSelected,...props}) => (
    <>
        <Grid>
            <GridRow>
                <Header as={'h2'}>
                    Task Dashboard
                </Header>
                <GridColumn>
                    <Button onClick={refresh} loading={isLoading} disabled={isLoading}>Refresh</Button>
                </GridColumn>
            </GridRow>
        </Grid>
        <CardGroup>{tasks.map((task) => <TaskCard {...task} onSelected={() => onTaskSelected(task)}/>)}</CardGroup>
    </>
)