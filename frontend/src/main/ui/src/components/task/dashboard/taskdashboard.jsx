import React from 'react';
import {Button, CardGroup, Grid, GridColumn, GridRow, Header} from "semantic-ui-react";
import {TaskCardContainer} from "./task-card-container";

export const TaskDashboard = ({tasks, isLoading, refresh,...props}) => (
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
        <CardGroup>{tasks.map((task) => <TaskCardContainer {...task} />)}</CardGroup>
    </>
)