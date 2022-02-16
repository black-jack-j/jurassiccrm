import React from "react";
import {Avatar} from "../avatar/avatar";
import './users-viewer.css'
import {useTranslation} from "react-i18next";

export const UserEntry = ({username, lastName, firstName, avatarSrc, department}) => (
    <div className={'users-viewer__item'}>
        <Avatar className={'users-viewer__item_avatar'} avatarSrc={avatarSrc}/>
        <div className={'users-viewer__item_info'}>
            <div className={'users-viewer__item_name'}>{`${firstName} ${lastName}`}</div>
            <div className={'users-viewer__item_username'}>{username}</div>
            <div className={'users-viewer__item_department'}>{department}</div>
        </div>
    </div>
)

const renderUsers = users => {
    return users.map(({id, username, lastName, firstName, avatarSrc, department}) => (
        <UserEntry
            key={id}
            id={id}
            username={username}
            lastName={lastName}
            firstName={firstName}
            avatarSrc={avatarSrc}
            department={department}
        />
    ))
}

export const UsersViewer = props => {

    const {
        users = [],
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.users_viewer'})

    return (
        <div className={'users-viewer'}>
            <div className={'users-viewer__header'}>
                <span className={'users-viewer__title'}>{t('title')}</span>
            </div>
            <div className={'users-viewer__list'}>
                {renderUsers(users)}
            </div>
        </div>
    )

}