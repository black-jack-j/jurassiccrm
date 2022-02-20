import React from 'react'
import {TaskCard} from "./task-card";
import {TaskTOTaskTypeEnum as TaskType} from "../../../generatedclient/models";

export default {
    title: 'Task Card',
    components: [TaskCard]
}

export const DefaultCard = () => (
    <TaskCard
        name={'Создать T-Rex'}
        taskType={TaskType.Incubation}
        description={''}
    />
)