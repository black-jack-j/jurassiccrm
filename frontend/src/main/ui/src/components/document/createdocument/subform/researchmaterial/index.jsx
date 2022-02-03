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
import {SearchInputComponent} from "../../../../search_input/searchinput-component";
import {useField} from "formik";
import {ResearchSearchContainer} from "../../../../researchsearch/researchsearch-component-container";

export const ResearchMaterialSubform = props => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create.research_material.field'})

    const [isInput] = useField(RESEARCH_IS_NEW)

    const inputProps = {
        name: `${RESEARCH_NAME_ID}.${RESEARCH_NAME}`,
        placeholder: t(`${RESEARCH_NAME_ID}.placeholder`),
        ...props
    }

    const searchProps = {
        fieldName: `${RESEARCH_NAME_ID}.${RESEARCH_ID}`,
        placeholder: t(`${RESEARCH_NAME_ID}.placeholder`),
        ...props
    }

    return (
        <>

            <SearchInputComponent isInput={isInput.value}
                                  Input={Input} input={inputProps}
                                  Search={ResearchSearchContainer} search={searchProps}/>

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