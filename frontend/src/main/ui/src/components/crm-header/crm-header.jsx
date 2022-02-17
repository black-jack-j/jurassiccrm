import {Header, Menu, MenuItem, MenuMenu} from "semantic-ui-react";
import {Avatar} from "../avatar/avatar";
import React from "react";

export const CRMHeader = props => {

    const {
        avatarSrc
    } = props

    return (
        <Menu secondary text>
            <MenuItem>
                <Header>
                    JurassicCRM
                </Header>
            </MenuItem>
            <MenuMenu position={"right"}>
                <Avatar size='medium' src={avatarSrc}/>
            </MenuMenu>
        </Menu>
    )
}