import {Button, Card, CardContent, CardMeta, Header, Menu, MenuItem, MenuMenu} from "semantic-ui-react";
import React from "react";

export const EditableDocumentCard = props => {

    const {
        name,
        type,
        onEdit
    } = props

    return (
        <Card>
            <CardContent>
                <Menu secondary text>
                    <MenuItem>
                        <Header as={'h3'} content={name}/>
                    </MenuItem>
                    <MenuMenu position={'right'}>
                        <MenuItem>
                            <Button icon={'edit'} size={'mini'} onClick={onEdit}/>
                        </MenuItem>
                    </MenuMenu>
                </Menu>
                <CardMeta>{type}</CardMeta>
            </CardContent>
        </Card>
    )

}