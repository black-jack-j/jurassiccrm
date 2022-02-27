import {useDispatch} from "react-redux";
import {TaskCard} from "./task-card";
import {viewTask} from "../task-viewer/viewer-slice";
import React from "react";

export const TaskCardContainer = props => {

    const dispatch = useDispatch()

    return <TaskCard onSelected={() => dispatch(viewTask({...props}))} {...props}/>

}