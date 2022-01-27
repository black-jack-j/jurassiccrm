import {Card, CardHeader, CardMeta} from "semantic-ui-react";
import React from "react";

export const DocumentCard = props => {

    const {
        name,
        type,
        onSelect
    } = props

    return (
        <Card onClick={onSelect}>
            <CardHeader>{name}</CardHeader>
            <CardMeta>{type}</CardMeta>
        </Card>
    )

}