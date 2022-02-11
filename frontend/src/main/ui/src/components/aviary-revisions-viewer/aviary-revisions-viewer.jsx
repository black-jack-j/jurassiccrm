import React, {useContext, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import ApiContext from "../../api";
import {Table, TableBody, TableHeader, TableHeaderCell, TableRow} from "semantic-ui-react";
import {AviaryRevisionEntry} from "./aviary-revision-entry/aviary-revision-entry";

import './aviary-revision-viewer.css'
import {Instant, LocalDate, LocalDateTime, nativeJs} from "js-joda";

const getAviaryRevisionEntry = ({revisionDate, aviary}) => (
    <AviaryRevisionEntry key={aviary.id} revisionDate={revisionDate} aviaryName={aviary.name}/>
)

export const AviaryRevisionsViewer = props => {
    const {
        revisions
    } = props

    console.log(revisions)

    const {t} = useTranslation()

    const sortedRevisions = [...revisions]
    sortedRevisions.sort(({revisionDate: revisionDateA}, {revisionDate: revisionDateB}) => revisionDateA.compareTo(revisionDateB))

    return (
        <Table className={'aviary-revision-viewer'}>
            <TableHeader>
                <TableRow>
                    <TableHeaderCell className={'aviary-revision-viewer__header'} colSpan={2}>
                        <div>{t('crm.widget.aviary_revisions_viewer.title')}</div>
                    </TableHeaderCell>
                </TableRow>
            </TableHeader>
            <TableBody className={'aviary-revision-viewer__body'}>
                {sortedRevisions.map(getAviaryRevisionEntry).sort()}
            </TableBody>
        </Table>
    )
}

const convertRevisionDateToJoda = ({revisionDate, aviary}) => {

    return {
        revisionDate: LocalDateTime.ofInstant(Instant.from(nativeJs(revisionDate))),
        aviary
    }

}

export const AviaryRevisionViewerContainer = props => {

    const [revisions, setRevisions] = useState([])

    const API = useContext(ApiContext)

    useEffect(() => {
        API.aviary.getNextAviaryRevisions()
            .then(setRevisions)
            .catch(console.error)
    }, [])

    return <AviaryRevisionsViewer revisions={revisions.map(convertRevisionDateToJoda)}/>

}