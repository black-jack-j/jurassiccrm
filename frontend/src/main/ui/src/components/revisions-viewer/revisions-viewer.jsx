import React, {useContext, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import ApiContext from "../../api";
import {Table, TableBody, TableHeader, TableHeaderCell, TableRow} from "semantic-ui-react";
import {RevisionEntry} from "./revision-entry/revision-entry";

import './revisions-viewer.css'

const getAviaryRevisionEntry = ({revisionDate, entity}) => (
    <RevisionEntry key={entity.id} revisionDate={revisionDate} name={entity.name}/>
)

export const RevisionsViewer = props => {
    const {
        revisions,
        title
    } = props

    const {t} = useTranslation()

    const sortedRevisions = [...revisions]
    sortedRevisions.sort(({revisionDate: revisionDateA}, {revisionDate: revisionDateB}) => revisionDateA - revisionDateB)

    return (
        <Table className={'revisions-viewer'}>
            <TableHeader>
                <TableRow>
                    <TableHeaderCell className={'revisions-viewer__header'} colSpan={2}>
                        <div>{title}</div>
                    </TableHeaderCell>
                </TableRow>
            </TableHeader>
            <TableBody className={'revision-viewer__body'}>
                {sortedRevisions.map(getAviaryRevisionEntry).sort()}
            </TableBody>
        </Table>
    )
}