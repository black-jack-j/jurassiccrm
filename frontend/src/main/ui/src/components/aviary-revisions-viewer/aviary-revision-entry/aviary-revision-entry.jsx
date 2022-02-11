import {TableCell, TableRow} from "semantic-ui-react";
import React from "react";

import './aviary-revision-entry.css'
import {useTranslation} from "react-i18next";
import {DateTimeFormatter} from "js-joda";

const options = {year: 'numeric', month: '2-digit', day: '2-digit'}


export const AviaryRevisionEntry = props => {

    const {
        revisionDate,
        aviaryName
    } = props

    const {i18n} = useTranslation()

    const formattedDate = revisionDate.format(DateTimeFormatter.ISO_LOCAL_DATE)

    return (
        <TableRow className={'aviary-revision-viewer__aviary-revision-entry'}>
            <TableCell className={'aviary-revision-viewer__entry-revision-date'}>{formattedDate}</TableCell>
            <TableCell className={'aviary-revision-viewer__entry-aviary-name'}>{aviaryName}</TableCell>
        </TableRow>
    )

}