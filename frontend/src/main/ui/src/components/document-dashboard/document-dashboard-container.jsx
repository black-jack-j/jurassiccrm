import {DocumentDashboard} from "./document-dashboard";
import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../api";
import {useDispatch} from "react-redux";
import {open} from "../document-form-selector-popup/documentform-selector-popup-slice"
import {DocumentFormSelectorPopup} from "../document-form-selector-popup/documentform-selector-popup";
import {CreateDocumentFormPopup} from "../create-document-form-popup/create-document-form-popup";
import {UpdateDocumentFormPopup} from "../update-document-form-popup/update-document-form-popup";
import {TabPane} from "semantic-ui-react";
import UserContext from "../../user/user-context";

export const DocumentDashboardContainer = ({...props}) => {

    const [documents, setDocuments] = useState([])
    const [loading, setLoading] = useState(false)

    const API = useContext(ApiContext)

    const {user: currentUser} = useContext(UserContext)

    const dispatch = useDispatch()
    const openPopup = () => dispatch(open())

    const refresh = () => {
        setLoading(true)
        API.document.getAllDocuments().then(setDocuments).then(() => setLoading(false)).catch(console.error)
    }

    useEffect(() => {
        refresh()
    }, [])

    return (
        <>
            <DocumentDashboard
                items={documents}
                loading={loading}
                refresh={refresh}
                onAdd={openPopup}
                currentUser={currentUser}
            />
            <DocumentFormSelectorPopup/>
            <CreateDocumentFormPopup />
            <UpdateDocumentFormPopup />
        </>

    )
}