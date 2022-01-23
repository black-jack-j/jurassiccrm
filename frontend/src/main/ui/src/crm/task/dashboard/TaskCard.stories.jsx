import React from 'react'
import {TaskCard} from "./TaskCard";
import {INCUBATION_TYPE} from "../form/subform/subform";

export default {
    title: 'Task Card',
    components: [TaskCard]
}

export const DefaultCard = () => (<TaskCard name={'Создать T-Rex'} taskType={INCUBATION_TYPE} description={''}/>)