import {Button, Header, Menu, MenuItem, MenuMenu} from "semantic-ui-react";
import {Avatar} from "../avatar/avatar";
import React, {useContext} from "react";
import UserContext from "../../user/user-context";

export const CRMHeader = props => {

    const {user} = useContext(UserContext)

    const avatarSrc = user && user.avatarSrc ? user.avatarSrc : '/img/avatar.png'

    const logout = () => {
        window.location = window.location.origin + "/logout"
    }

    return (
        <Menu secondary text>
            <MenuItem>
                <Header>
                    JurassicCRM
                </Header>
            </MenuItem>
            <MenuMenu position={"right"}>
                <Avatar size='medium' src={avatarSrc}/>
                <MenuItem>
                    <Button type={'button'} onClick={logout}>Log out</Button>
                </MenuItem>
            </MenuMenu>
        </Menu>
    )
}