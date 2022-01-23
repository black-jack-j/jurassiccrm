import React from "react";
import {Card, CardContent, CardDescription, CardHeader, CardMeta} from "semantic-ui-react";

export const TaskCard = ({name, taskType, description, onSelected, ...props}) => {
    return (
        <Card onClick={onSelected}>
            <CardContent>
                <CardHeader content={name}/>
                <CardMeta content={taskType}/>
                <CardDescription content={description || ''}/>
            </CardContent>
        </Card>
    )
}