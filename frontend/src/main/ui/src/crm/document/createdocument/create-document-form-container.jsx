import ApiContext from "../../../api";
import {CreateDocumentForm} from "./create-document-form";
import React, {useContext} from "react";
import {withType} from "./subform/createdocument-subform";

const createDocumentProvider = API => (documentType, documentTO) => API.document.createDocument({documentType, httpEntity: {...documentTO}}).then(console.log).catch(console.error)

export const CreateDocumentFormContainer = ({type, onSubmit, onCancel, ...props}) => {

    const API = useContext(ApiContext)

    const [SubForm] = withType(type)

    const createDocumentOnSubmit = values => {
        createDocumentProvider(API)(type, values)
        onSubmit(values)
    }

    return (
        <CreateDocumentForm {...props} onSubmit={createDocumentOnSubmit} onCancel={onCancel}>
            {props => <SubForm {...props}/>}
        </CreateDocumentForm>
    )

}
