import ApiContext from "../../../../../api";
import {CreateDocumentForm} from "../create-document-form";
import React, {useContext} from "react";
import {withType} from "../subform/createdocument-subform";
import CommonInitialValues from "../initialValues"

const createDocumentProvider = API => (documentType, body) => API.document.createDocument({documentType, body: {...body, documentType}}, {
    headers: [["Content-Type", "application/json"]]
}).then(console.log).catch(console.error)

export const CreateDocumentFormContainer = ({type, onSubmit, onCancel, ...props}) => {

    const API = useContext(ApiContext)

    const [SubForm, onSubmitTransformer, subFormInitialValues] = withType(type)

    const createDocumentOnSubmit = values => {
        const transformedValues = onSubmitTransformer(values)
        createDocumentProvider(API)(type, transformedValues)
        onSubmit(transformedValues)
    }

    const initialValues = {
        ...subFormInitialValues,
        ...CommonInitialValues
    }

    return (
        <CreateDocumentForm {...props} onSubmit={createDocumentOnSubmit} onCancel={onCancel} formik={{initialValues}}>
            {props => <SubForm {...props}/>}
        </CreateDocumentForm>
    )

}
