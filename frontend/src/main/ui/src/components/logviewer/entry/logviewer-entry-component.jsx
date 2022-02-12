import {TableCell, TableRow} from "semantic-ui-react";
import React from "react";
import {useTranslation} from "react-i18next";
import './logviewer-entry.css'
import '../logviewer.css'
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../../time/time-utils";

export const LogViewerEntry = ({username, action, timestamp,...props}) => {

    const {t, i18n} = useTranslation()

    const formattedTimestamp = int64FieldToZonedDateTime(timestamp).format(DATE_FORMATTER)

    return (
        <TableRow className={'logviewer__entry'}>
            <TableCell className={'logviewer__username'}>{username}</TableCell>
            <TableCell className={'logviewer__action'}>{action}</TableCell>
            <TableCell className={'logviewer__timestamp'}>{formattedTimestamp}</TableCell>
        </TableRow>
    )
}