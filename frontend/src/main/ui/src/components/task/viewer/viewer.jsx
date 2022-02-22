import {Button, Header, Label, LabelGroup} from "semantic-ui-react";
import React from "react";
import {UserInfoContainer} from "../../userinfo/userinfo-container";
import {useDispatch, useSelector} from "react-redux";
import {selectTaskViewerTask} from "./viewer-slice";
import {editTask} from "../editor/edit-task-popup-slice";
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../../time/time-utils";

export const Viewer = () => {

    const task = useSelector(selectTaskViewerTask)

    const dispatch = useDispatch()

    if (typeof task === "undefined") {
        return <div>No data available. Select task</div>
    }

    const {
        id,
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

    const formattedCreated = int64FieldToZonedDateTime(created).format(DATE_FORMATTER)
    const formattedLastUpdated = int64FieldToZonedDateTime(lastUpdated).format(DATE_FORMATTER)

    return (
        <>
            <Button onClick={() => dispatch(editTask({id, type: taskType}))}>Edit</Button>
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
            {formattedCreated}
            <Header as={'h5'}>Last Updated By</Header>
            <UserInfoContainer id={lastUpdaterId}/>
            <Header as={'h5'}>Last Updated</Header>
            {formattedLastUpdated}
            <p>
                {description}
            </p>
        </>

    )

}