import React from 'react';
import {TaskCard} from "./TaskCard";
import {CardGroup} from "semantic-ui-react";

export const TaskDashboard = ({tasks, ...props}) => (<CardGroup>{tasks.map((task) => <TaskCard {...task} />)}</CardGroup>)