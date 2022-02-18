import {Checkbox, Form, Input, ResetButton, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {
    DOCUMENT_NAME,
    RESEARCH_ATTACHMENT,
    RESEARCH_ATTACHMENT_NAME,
    RESEARCH_ID,
    RESEARCH_IS_NEW,
    RESEARCH_MATERIAL_DESCRIPTION,
    RESEARCH_NAME,
    RESEARCH_NAME_ID
} from "../document/form/create/researchdata/fieldNames";
import {FormikSearchInputComponent} from "../search_input/formik-searchinput-component";
import {ResearchEntitySearchContainer} from "../researchsearch/researchsearch-component-container";
import {FileInput} from "../fileinput/fileinput-component";
import {Menu, MenuItem} from "semantic-ui-react";
import {Formik} from "formik";
import React from "react";

export const ResearchDataForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues,
        translations
    } = props

    const inputProps = {
        name: `${RESEARCH_NAME_ID}.${RESEARCH_NAME}`,
        placeholder: translations(`field.${RESEARCH_NAME_ID}.placeholder`),
        ...props
    }

    const searchProps = {
        name: `${RESEARCH_NAME_ID}.${RESEARCH_ID}`,
        placeholder: translations(`field.${RESEARCH_NAME_ID}.placeholder`),
        ...props
    }

    return (
        <Formik initialValues={initialValues}
                onSubmit={onSubmit}>
            <Form>
                <Input name={DOCUMENT_NAME} placeholder={translations(`field.${DOCUMENT_NAME}.placeholder`)}/>
                <FormikSearchInputComponent name={RESEARCH_IS_NEW}
                                            Input={Input} input={inputProps}
                                            Search={ResearchEntitySearchContainer} search={searchProps}/>

                <Checkbox name={RESEARCH_IS_NEW}
                          label={translations(`field.${RESEARCH_IS_NEW}.label`)}/>

                <FileInput name={RESEARCH_ATTACHMENT}/>

                <Input name={RESEARCH_ATTACHMENT_NAME} placeholder={translations(`field.${RESEARCH_ATTACHMENT_NAME}.placeholder`)}/>

                <TextArea name={RESEARCH_MATERIAL_DESCRIPTION}
                          placeholder={translations(`field.${RESEARCH_MATERIAL_DESCRIPTION}.placeholder`)}
                          {...props}/>
                <Menu secondary>
                    <MenuItem>
                        <SubmitButton floated={'left'} positive>{translations('submit')}</SubmitButton>
                    </MenuItem>
                    <MenuItem position={'right'}>
                        <ResetButton floated={'right'} negative onClick={onCancel}>{translations('cancel')}</ResetButton>
                    </MenuItem>
                </Menu>
            </Form>
        </Formik>
    )

}