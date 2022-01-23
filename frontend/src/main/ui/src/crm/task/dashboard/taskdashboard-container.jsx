import React, {useEffect, useState} from 'react'
import {TaskDashboard} from "./taskdashboard";

import {API} from "../../../api";

export const TaskDashboardContainer = ({onTaskSelected}) => {

    const [state, setState] = useState({tasks: [], isLoading: false})

    const refresh = () => {
        setState({tasks: [], isLoading: true})
        API.task.getTasks().then(tasks => {
            console.log(tasks)
            setState({tasks, isLoading: false})
        })
    }

    useEffect(() => {
        refresh()
    }, [])

    return (
        <>
            <TaskDashboard onTaskSelected={onTaskSelected}
                           tasks={state.tasks}
                           refresh={refresh}
                           isLoading={state.isLoading}/>
        </>
    )

}