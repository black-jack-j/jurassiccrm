import React from 'react'
import {TaskDashboard} from "./TaskDashboard";
import {INCUBATION_TYPE, RESEARCH_TYPE} from "../form/subform/subform";

export default {
    title: 'Task Dashboard',
    components: [TaskDashboard]
}

const tasks = [
    {name: 'Создать T-Rex', type: INCUBATION_TYPE, description: 'Вывести поскорее'},
    {name: 'Увеличить выносливость рапторов', type: RESEARCH_TYPE, description: 'Рапторы слишком быстро выдыхаются, бегая за посетителями'}
]

export const DefaultDashboard = () => (<TaskDashboard tasks={tasks} />)