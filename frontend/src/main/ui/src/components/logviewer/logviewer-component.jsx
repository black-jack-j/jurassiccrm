import React, {useState} from "react";
import {
    Menu,
    MenuItem,
    SegmentInline,
    Table,
    TableBody,
    TableHeader,
    TableHeaderCell,
    TableRow
} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import {LogViewerEntry} from "./entry/logviewer-entry-component";
import {LogViewerUsernameFilter} from "./filter/logviewer-filter-component";
import './logviewer.css'

const renderLogEntries = items => items.map(item => <LogViewerEntry key={item.id} {...item}/>)

export const LogViewerComponent = ({items, refresh, ...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.logviewer'})
    const [usernameFilter, setUsernameFilter] = useState(undefined)

    return (
        <div className={'logviewer'}>
            <Menu text className={'logviewer__menu'}>
                <MenuItem header className={'logviewer__menu_label'}>
                    {t('filter.placeholder')}
                </MenuItem>
                <MenuItem>
                    <LogViewerUsernameFilter filter={setUsernameFilter}/>
                </MenuItem>
                <MenuItem icon={'refresh'} onClick={refresh}/>
            </Menu>
            <Table className={'logviewer__table'}>
                <TableHeader className={'logviewer__header'}>
                    <TableRow>
                        <TableHeaderCell className={'logviewer__username'}>{t('header.username')}</TableHeaderCell>
                        <TableHeaderCell className={'logviewer__action'}>{t('header.action')}</TableHeaderCell>
                        <TableHeaderCell className={'logviewer__timestamp'}>{t('header.timestamp')}</TableHeaderCell>
                    </TableRow>
                </TableHeader>
                <TableBody className={'logviewer__body'}>
                    {
                        typeof usernameFilter === 'undefined' ?
                            renderLogEntries(items) :
                            renderLogEntries(items.filter(item => item.username === usernameFilter))
                    }
                </TableBody>
            </Table>
        </div>
    )

}