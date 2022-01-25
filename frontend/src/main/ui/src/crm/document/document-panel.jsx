import React from "react";
import {Button, Tab} from "semantic-ui-react";
import {useDispatch} from "react-redux";

import {open as openCreateDocumentPopup} from "./createdocument/create-document-popup-slice"
import {CreateDocumentPopup} from "./createdocument/create-document-popup";

const DocumentPanelContent = () => {

    const dispatch = useDispatch()

    return (
        <>
            <Button onClick={() => dispatch(openCreateDocumentPopup())}>Create</Button>
            <CreateDocumentPopup />
        </>
    )

}

export const DocumentPanel = () => <Tab.Pane><DocumentPanelContent/></Tab.Pane>