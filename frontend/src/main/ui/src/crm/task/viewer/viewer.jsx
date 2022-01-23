import {Header, Label, LabelGroup} from "semantic-ui-react";
import React from "react";
import {UserInfoContainer} from "../../components/userinfo/userinfo-container";

export const Viewer = props => {

    if (typeof props.task === "undefined") {
        return <div>No data available. Select task</div>
    }

    console.log(props)

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
    } = props.task

    return (
        <>
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