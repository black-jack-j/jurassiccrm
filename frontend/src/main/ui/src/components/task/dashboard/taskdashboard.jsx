import React from 'react';
import {CardGroup} from "semantic-ui-react";
import {TaskCardContainer} from "./task-card-container";

export const TaskDashboard = ({tasks, loading, refresh, onAdd, currentUser,...props}) => {


    return (
        <>
            <CardGroup>{tasks.map((task) => <TaskCardContainer {...task} />)}</CardGroup>
        </>
    )
}