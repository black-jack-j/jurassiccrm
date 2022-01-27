import {Button, Header, Label, LabelGroup} from "semantic-ui-react";
import React from "react";
import {UserInfoContainer} from "../../userinfo/userinfo-container";
import {useDispatch, useSelector} from "react-redux";
import {selectTaskViewerTask} from "./viewer-slice";
import {editTask} from "../editor/edit-task-popup-slice";

export const Viewer = () => {

    const task = useSelector(selectTaskViewerTask)

    const dispatch = useDispatch()

    if (typeof task === "undefined") {
        return <div>No data available. Select task</div>
    }

    const {
        name,
        taskType,
        assigneeId,
        description,
        createdById,
        lastUpdaterId,
        created,
        lastUpdated,
        currentState
    } = task

    return (
        <>
            <Button onClick={() => dispatch(editTask({...task}))}>Edit</Button>
            <Header as={'h4'}>{name}</Header>
            <LabelGroup>
                <Label>{taskType}</Label>
                <Label>{currentState}</Label>
            </LabelGroup>
            <Header as={'h5'}>Assignee</Header>
            <UserInfoContainer id={assigneeId}/>
            <Header as={'h5'}>Created By</Header>
            <UserInfoContainer id={createdById}/>
            <Header as={'h5'}>Created</Header>
            {created.toLocaleDateString()}
            <Header as={'h5'}>Last Updated By</Header>
            <UserInfoContainer id={lastUpdaterId}/>
            <Header as={'h5'}>Last Updated</Header>
            {lastUpdated.toLocaleDateString()}
            <p>
                {description}
            </p>
        </>

    )

}