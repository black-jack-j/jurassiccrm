import React from 'react'
import {TaskDashboard} from "./task-dashboard";
import {INCUBATION_TYPE, RESEARCH_TYPE} from "../form/subform/subform";

export default {
    title: 'Task Dashboard',
    components: [TaskDashboard]
}

const tasks = [
    {name: 'Создать T-Rex', type: INCUBATION_TYPE, description: 'Вывести поскорее'},
    {name: 'Увеличить выносливость рапторов', type: RESEARCH_TYPE, description: 'Рапторы слишком быстро выдыхаются, бегая за посетителями'}
]

const Template = (args) => <TaskDashboard {...args}/>

export const DefaultDashboard = Template.bind({})

DefaultDashboard.args = {
    tasks,
    refresh: () => console.log('refreshed'),
    isLoading: false
}

export const LoadingDashboard = Template.bind({})
LoadingDashboard.args = {
    tasks: [],
    refresh: () => console.log('refreshed'),
    isLoading: true
}
