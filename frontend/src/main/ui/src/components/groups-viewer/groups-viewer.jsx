import {Icon, Table, TableBody, TableCell, TableHeader, TableHeaderCell, TableRow} from "semantic-ui-react";
import React, {useContext, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";

import './groups-viewer.css'
import ApiContext from "../../api";
import Popup from "reactjs-popup";
import {CreateGroupFormContainer} from "../create-group-form/create-group-form";

const GroupEntry = ({name, description}) => (
    <TableRow className={'groups-viewer__entry'}>
        <TableCell className={'groups-viewer__group-name'}>{name}</TableCell>
        <TableCell className={'groups-viewer__group-description'}>{description}</TableCell>
    </TableRow>
)

export const GroupsViewer = props => {

    const {
        groups = [],
        canAdd = false
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.groups_viewer'})
    const [opened, setOpen] = useState(false)
    const close = () => setOpen(false)
    const open = () => setOpen(true)

    return (
        <div className={'groups-viewer'}>
            <Table className={'groups-viewer__table'}>
                <TableHeader className={'groups-viewer__header'}>
                    <TableRow>
                        <TableHeaderCell className={'groups-viewer__header_cell groups-viewer__group-name'}>{t('header.name')}</TableHeaderCell>
                        <TableHeaderCell className={'groups-viewer__header_cell groups-viewer__group-description'}>{t('header.description')}</TableHeaderCell>
                    </TableRow>
                </TableHeader>
                <TableBody className={'groups-viewer__body'}>
                    {
                        groups.map(({id, name, description}) => <GroupEntry key={id} name={name} description={description}/>)
                    }
                </TableBody>
            </Table>
            <Popup onClose={close} onOpen={open} open={opened} modal closeOnDocumentClick={false}>
                <CreateGroupFormContainer onSubmit={close} onCancel={close} formik={{initialValues: {}}}/>
            </Popup>
            <button className={'groups-viewer__add'} disabled={!canAdd} type={'button'} onClick={open}>
                <Icon name={'plus'}/>
            </button>
        </div>
    )

}

export const GroupsViewerContainer = ({canAdd}) => {

    const API = useContext(ApiContext)

    const [groups, setGroups] = useState([])

    const refresh = () => API.group.getAllGroups().then(setGroups).catch(console.error)

    useEffect(() => {
        refresh()
    }, [])

    return <GroupsViewer groups={groups} canAdd={canAdd}/>

}