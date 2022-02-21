import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../api";

import {RevisionsViewer} from "../revisions-viewer/revisions-viewer";
import {useTranslation} from "react-i18next";

export const DinosaurRevisionsViewer = props => {

    const [revisions, setRevisions] = useState([])

    const API = useContext(ApiContext)
    const {t} = useTranslation()

    useEffect(() => {
        API.dinosaur.getUpcomingRevisions()
            .then(setRevisions)
            .catch(console.error)
    }, [])

    return <RevisionsViewer revisions={revisions} title={t('crm.widget.dinosaur_revisions_viewer.title')}/>

}