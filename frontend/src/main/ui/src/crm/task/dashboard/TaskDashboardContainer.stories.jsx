import React from 'react'
import {TaskDashboardContainer} from "./TaskDashboardContainer";
import {INCUBATION_TYPE, RESEARCH_TYPE} from "../form/subform/subform";

export default {
    title: 'Task Dashboard Container with 3 seconds delay',
    components: [TaskDashboardContainer]
}

const tasks = [
    {name: 'Создать T-Rex', type: INCUBATION_TYPE, description: 'Вывести поскорее'},
    {name: 'Увеличить выносливость рапторов', type: RESEARCH_TYPE, description: 'Рапторы слишком быстро выдыхаются, бегая за посетителями'}
]

const mockGetAllTasks = () => new Promise(resolve => setTimeout(() => resolve({data: tasks}), 3000))

export const DelayedTaskDashboard = () => (<TaskDashboardContainer getTasks={mockGetAllTasks}/>)