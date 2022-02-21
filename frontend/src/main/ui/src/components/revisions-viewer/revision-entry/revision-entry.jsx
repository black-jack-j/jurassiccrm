import {TableCell, TableRow} from "semantic-ui-react";
import React from "react";

import './revision-entry.css'
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../../time/time-utils";

export const RevisionEntry = props => {

    const {
        revisionDate,
        name
    } = props

    const formattedDate = int64FieldToZonedDateTime(revisionDate).format(DATE_FORMATTER)

    return (
        <TableRow className={'revision-viewer__revision-entry'}>
            <TableCell className={'revision-viewer__entry-revision-date'}>{formattedDate}</TableCell>
            <TableCell className={'revision-viewer__entry-entity-name'}>{name}</TableCell>
        </TableRow>
    )

}