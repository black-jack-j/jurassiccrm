import {Icon, Table, TableBody, TableCell, TableHeader, TableHeaderCell, TableRow} from "semantic-ui-react";
import React from "react";
import {useTranslation} from "react-i18next";

import './groups-viewer.css'

const GroupEntry = ({name, description, onSelect}) => (
    <TableRow className={'groups-viewer__entry'} onClick={onSelect}>
        <TableCell className={'groups-viewer__group-name'}>{name}</TableCell>
        <TableCell className={'groups-viewer__group-description'}>{description}</TableCell>
    </TableRow>
)

export const GroupsViewer = props => {

    const {
        groups = [],
        canAdd = false,
        onAdd,
        onSelect
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.groups_viewer'})
    const handleGroupSelected = (groupId) => onSelect && onSelect(groupId)
    const handleOnAdd = () => onAdd && onAdd()

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
                        groups.map(({id, name, description}) => <GroupEntry key={id} name={name} description={description} onSelect={() => handleGroupSelected(id)}/>)
                    }
                </TableBody>
            </Table>
            <button className={'groups-viewer__add'} disabled={!canAdd} type={'button'} onClick={handleOnAdd}>
                <Icon name={'plus'}/>
            </button>
        </div>
    )

}