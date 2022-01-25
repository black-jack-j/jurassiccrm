import {API} from "../../../api";
import {CreateDocumentForm} from "./create-document-form";
import React from "react";
import {withType} from "./subform/createdocument-subform";

const createDocument = (documentType, documentTO) => API.document.createDocument({documentType, httpEntity: {...documentTO}}).then(console.log).catch(console.error)

export const CreateDocumentFormContainer = ({type, onSubmit, onCancel, ...props}) => {

    const [SubForm] = withType(type)

    const createDocumentOnSubmit = values => {
        createDocument(type, values)
        onSubmit(values)
    }

    return (
        <CreateDocumentForm {...props} onSubmit={createDocumentOnSubmit} onCancel={onCancel}>
            {props => <SubForm {...props}/>}
        </CreateDocumentForm>
    )

}
