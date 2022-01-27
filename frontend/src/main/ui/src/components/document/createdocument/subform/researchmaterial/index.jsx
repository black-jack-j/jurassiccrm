import {Checkbox, Input, TextArea} from "formik-semantic-ui-react";
import React from "react";
import {
    RESEARCH_ATTACHMENT,
    RESEARCH_ID,
    RESEARCH_IS_NEW,
    RESEARCH_MATERIAL_DESCRIPTION,
    RESEARCH_NAME,
    RESEARCH_NAME_ID
} from "./fieldNames";

export const ResearchMaterialSubform = props => {

    return (
        <>
            <Input name={RESEARCH_NAME_ID} {...props}/>
            <Checkbox name={RESEARCH_IS_NEW} label={"New research"}/>
            <Input name={RESEARCH_ATTACHMENT} type={"file"} {...props}/>
            <TextArea name={RESEARCH_MATERIAL_DESCRIPTION} {...props}/>
        </>
    )

}

export const onSubmitValueTransformer = values => {
    const researchDescriptorField = values[RESEARCH_IS_NEW] ? RESEARCH_NAME : RESEARCH_ID

    return {
        [RESEARCH_ATTACHMENT]: values[RESEARCH_ATTACHMENT],
        [RESEARCH_MATERIAL_DESCRIPTION]: values[RESEARCH_MATERIAL_DESCRIPTION],
        [researchDescriptorField]: values[RESEARCH_NAME_ID][researchDescriptorField]
    }
}