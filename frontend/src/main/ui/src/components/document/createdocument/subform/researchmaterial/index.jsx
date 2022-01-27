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
import {useTranslation} from "react-i18next";

export const ResearchMaterialSubform = props => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create.research_material.field'})

    return (
        <>
            <Input name={RESEARCH_NAME_ID}
                   placeholder={t(`${RESEARCH_NAME_ID}.placeholder`)}
                   {...props}/>

            <Checkbox name={RESEARCH_IS_NEW}
                      label={t(`${RESEARCH_IS_NEW}.label`)}/>

            <Input name={RESEARCH_ATTACHMENT} type={"file"} {...props}/>

            <TextArea name={RESEARCH_MATERIAL_DESCRIPTION}
                      placeholder={t(`${RESEARCH_MATERIAL_DESCRIPTION}.placeholder`)}
                      {...props}/>
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