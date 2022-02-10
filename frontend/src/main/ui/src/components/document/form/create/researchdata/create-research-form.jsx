import {useTranslation} from "react-i18next";
import {Header, Menu, MenuItem} from "semantic-ui-react";
import {Formik} from "formik";
import {Checkbox, Form, Input, ResetButton, SubmitButton, TextArea} from "formik-semantic-ui-react";
import React from "react";
import {ResearchEntitySearchContainer} from "../../../../researchsearch/researchsearch-component-container";
import {
    DOCUMENT_NAME,
    RESEARCH_ATTACHMENT,
    RESEARCH_ATTACHMENT_NAME,
    RESEARCH_ID,
    RESEARCH_IS_NEW,
    RESEARCH_MATERIAL_DESCRIPTION,
    RESEARCH_NAME,
    RESEARCH_NAME_ID
} from "./fieldNames";
import {FileInput} from "../../../../fileinput/fileinput-component";
import {FormikSearchInputComponent} from "../../../../search_input/formik-searchinput-component";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../../generatedclient/apis";

export const CreateResearchForm = ({onSubmit, onCancel, title, formik, ...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create'})

    const inputProps = {
        name: `${RESEARCH_NAME_ID}.${RESEARCH_NAME}`,
        placeholder: t(`${DocumentTypeEnum.ResearchData}.field.${RESEARCH_NAME_ID}.placeholder`),
        ...props
    }

    const searchProps = {
        fieldName: `${RESEARCH_NAME_ID}.${RESEARCH_ID}`,
        placeholder: t(`${DocumentTypeEnum.ResearchData}.field.${RESEARCH_NAME_ID}.placeholder`),
        ...props
    }

    return (
        <>
            <Header as='h2' className={"center aligned"}>{title}</Header>
            <Formik enableReinitialize
                    initialValues={formik.initialValues}
                    onSubmit={values => {
                        console.log(values)
                        onSubmit(values)
                    }}>
                <Form style={{width: 600}}>
                    <Input name={DOCUMENT_NAME} placeholder={t(`field.${DOCUMENT_NAME}.placeholder`)}/>
                    <FormikSearchInputComponent name={RESEARCH_IS_NEW}
                                                Input={Input} input={inputProps}
                                                Search={ResearchEntitySearchContainer} search={searchProps}/>

                    <Checkbox name={RESEARCH_IS_NEW}
                              label={t(`${DocumentTypeEnum.ResearchData}.field.${RESEARCH_IS_NEW}.label`)}/>

                    <FileInput name={RESEARCH_ATTACHMENT}/>

                    <Input name={RESEARCH_ATTACHMENT_NAME} placeholder={t(`${DocumentTypeEnum.ResearchData}.field.${RESEARCH_ATTACHMENT_NAME}.placeholder`)}/>

                    <TextArea name={RESEARCH_MATERIAL_DESCRIPTION}
                              placeholder={t(`${DocumentTypeEnum.ResearchData}.field.${RESEARCH_MATERIAL_DESCRIPTION}.placeholder`)}
                              {...props}/>
                    <Menu secondary>
                        <MenuItem>
                            <SubmitButton style={{width: 150}} floated={'left'} positive>{t('submit')}</SubmitButton>
                        </MenuItem>
                        <MenuItem position={'right'}>
                            <ResetButton style={{width: 150}} floated={'right'} negative onClick={onCancel}>{t('cancel')}</ResetButton>
                        </MenuItem>
                    </Menu>
                </Form>
            </Formik>
        </>

    )

}