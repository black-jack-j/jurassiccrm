import {TableCell, TableRow} from "semantic-ui-react";
import React from "react";

import './aviary-revision-entry.css'
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../../time/time-utils";

export const AviaryRevisionEntry = props => {

    const {
        revisionDate,
        aviaryName
    } = props

    const formattedDate = int64FieldToZonedDateTime(revisionDate).format(DATE_FORMATTER)

    return (
        <TableRow className={'aviary-revision-viewer__aviary-revision-entry'}>
            <TableCell className={'aviary-revision-viewer__entry-revision-date'}>{formattedDate}</TableCell>
            <TableCell className={'aviary-revision-viewer__entry-aviary-name'}>{aviaryName}</TableCell>
        </TableRow>
    )

}