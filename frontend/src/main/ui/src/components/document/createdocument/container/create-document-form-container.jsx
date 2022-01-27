import ApiContext from "../../../../api";
import {CreateDocumentForm} from "../create-document-form";
import React, {useContext} from "react";
import {withType} from "../subform/createdocument-subform";
import CommonInitialValues from "../initialValues"

const createDocumentProvider = API => (documentType, documentTO) => API.document.createDocument({documentType, httpEntity: {...documentTO}}).then(console.log).catch(console.error)

export const CreateDocumentFormContainer = ({type, onSubmit, onCancel, ...props}) => {

    const API = useContext(ApiContext)

    const [SubForm, onSubmitTransformer, subFormInitialValues] = withType(type)

    const createDocumentOnSubmit = values => {
        const transformedValues = {
            ...values,
            ...onSubmitTransformer(values)
        }
        createDocumentProvider(API)(type, transformedValues)
        onSubmit(values)
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
