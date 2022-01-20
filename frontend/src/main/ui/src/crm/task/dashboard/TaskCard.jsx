import React from "react";
import {Card, CardContent, CardHeader, CardMeta, CardDescription} from "semantic-ui-react";

export const TaskCard = ({name, type, description, ...props}) => {
    return (
        <Card>
            <CardContent>
                <CardHeader content={name}/>
                <CardMeta content={type}/>
                <CardDescription content={description || ''}/>
            </CardContent>
        </Card>
    )
}