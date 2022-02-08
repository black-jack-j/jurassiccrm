import {DocumentDashboard} from "./document-dashboard";
import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../api";

export const DocumentDashboardContainer = ({...props}) => {

    const [documents, setDocuments] = useState([])
    const [loading, setLoading] = useState(false)

    const API = useContext(ApiContext)

    const refresh = () => {
        setLoading(true)
        API.document.getAllDocuments().then(setDocuments).then(() => setLoading(false)).catch(console.error)
    }

    useEffect(() => {
        refresh()
    }, [])

    return (
        <DocumentDashboard items={documents}
                           loading={loading}
                           refresh={refresh}/>
    )
}