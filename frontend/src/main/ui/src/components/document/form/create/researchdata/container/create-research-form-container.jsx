import React, {useContext} from "react";
import {CreateResearchForm} from "../create-research-form";
import {RESEARCH_ATTACHMENT} from "../fieldNames";
import ApiContext from "../../../../../../api";
import initialValues from "./initialValues";

export const CreateResearchDataFormContainer = ({type, onSubmit, onCancel, ...props}) => {

    const API = useContext(ApiContext)

    const createDocumentOnSubmit = values => {

        const researchData = {...values, documentType: type}
        delete researchData[RESEARCH_ATTACHMENT]

        API.document.createResearchData({
            researchData: JSON.stringify(researchData), attachment: values[RESEARCH_ATTACHMENT]
        }).then(console.log).catch(console.error)

        onSubmit && onSubmit(values)
    }

    return (
        <CreateResearchForm {...props} onSubmit={createDocumentOnSubmit} onCancel={onCancel} formik={{initialValues}}/>
    )

}
