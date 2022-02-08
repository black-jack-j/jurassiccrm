import {TableCell, TableRow} from "semantic-ui-react";
import React from "react";
import {useTranslation} from "react-i18next";
import './logviewer-entry.css'
import '../logviewer.css'

const options = {year: 'numeric', month: '2-digit', day: '2-digit'}

export const LogViewerEntry = ({username, action, timestamp,...props}) => {

    const {t, i18n} = useTranslation()

    return (
        <TableRow className={'logviewer__entry'}>
            <TableCell className={'logviewer__username'}>{username}</TableCell>
            <TableCell className={'logviewer__action'}>{action}</TableCell>
            <TableCell className={'logviewer__timestamp'}>{timestamp.toLocaleDateString(i18n.resolvedLanguage, options)}</TableCell>
        </TableRow>
    )
}