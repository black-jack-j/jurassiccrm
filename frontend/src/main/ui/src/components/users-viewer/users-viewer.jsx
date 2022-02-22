import React from "react";
import {Avatar} from "../avatar/avatar";
import './users-viewer.css'
import {useTranslation} from "react-i18next";
import {Button, Container, Header, Menu, MenuItem} from "semantic-ui-react";

export const UserEntry = ({username, lastName, firstName, avatarSrc, department, onSelect}) => (
    <div className={'users-viewer__item'} onClick={onSelect}>
        <Avatar className={'users-viewer__item_avatar'} src={avatarSrc}/>
        <div className={'users-viewer__item_info'}>
            <div className={'users-viewer__item_name'}>{`${firstName} ${lastName}`}</div>
            <div className={'users-viewer__item_username'}>{username}</div>
            <div className={'users-viewer__item_department'}>{department}</div>
        </div>
    </div>
)

const renderUsers = (users, onSelect) => {
    return users.map(({id, username, lastName, firstName, avatarSrc, department}) => (
        <UserEntry
            key={id}
            id={id}
            username={username}
            lastName={lastName}
            firstName={firstName}
            avatarSrc={avatarSrc}
            department={department}
            onSelect={() => onSelect(id)}
        />
    ))
}

export const UsersViewer = props => {

    const {
        users = [],
        onSelect,
        canAdd,
        onAdd,
        refresh
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.users_viewer'})

    return (
        <Container className={'users-viewer'}>
            <Menu className={'users-viewer__header'} text secondary>
                <MenuItem>
                    <Header as={'h4'}>
                        {t('title')}
                    </Header>
                </MenuItem>
                {
                    canAdd &&
                        <MenuItem>
                            <Button icon={'plus'} onClick={onAdd}/>
                        </MenuItem>
                }
                <MenuItem>
                    <Button icon={'refresh'} onClick={refresh}/>
                </MenuItem>
            </Menu>
            <div className={'users-viewer__list'}>
                {renderUsers(users, onSelect)}
            </div>
        </Container>
    )

}