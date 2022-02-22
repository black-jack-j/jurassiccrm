import React from "react";
import {Card, CardContent, CardDescription, CardMeta, Header, Menu, MenuItem} from "semantic-ui-react";

export const TaskCard = ({name, taskType, description, onSelected, ...props}) => {
    return (
        <Card onClick={onSelected}>
            <CardContent>
                <Menu secondary text>
                    <MenuItem>
                        <Header as={'h3'} content={name}/>
                    </MenuItem>
                </Menu>
                <CardMeta>{taskType}</CardMeta>
                <CardDescription>{description}</CardDescription>
            </CardContent>
        </Card>
    )
}