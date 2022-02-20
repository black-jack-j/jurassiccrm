import React, {useContext, useEffect, useState} from 'react'
import {TaskDashboard} from "./taskdashboard";

import ApiContext from "../../../api";

export const TaskDashboardContainer = () => {

    const API = useContext(ApiContext)

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
            <TaskDashboard tasks={state.tasks}
                           refresh={refresh}
                           loading={state.isLoading}/>
        </>
    )

}