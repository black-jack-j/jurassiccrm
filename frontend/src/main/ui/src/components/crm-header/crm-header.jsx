import {Header, Menu, MenuItem, MenuMenu} from "semantic-ui-react";
import {Avatar} from "../avatar/avatar";
import React, {useContext} from "react";
import UserContext from "../../user/user-context";

export const CRMHeader = props => {

    const {user} = useContext(UserContext)

    const avatarSrc = user && user.avatarSrc ? user.avatarSrc : '/img/avatar.png'

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