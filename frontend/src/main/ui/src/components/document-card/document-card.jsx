import {Button, Card, CardContent, CardHeader, CardMeta, Header, Menu, MenuItem, MenuMenu} from "semantic-ui-react";
import React from "react";

export const DocumentCard = props => {

    const {
        name,
        type,
        onSelect
    } = props

    return (
        <Card onClick={onSelect}>
            <CardContent>
                <Menu secondary text>
                    <MenuItem>
                        <Header as={'h3'} content={name}/>
                    </MenuItem>
                </Menu>
                <CardMeta>{type}</CardMeta>
            </CardContent>
        </Card>
    )

}