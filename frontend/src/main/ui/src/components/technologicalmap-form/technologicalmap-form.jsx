import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import {Menu, MenuItem} from "semantic-ui-react";
import {FieldArray, Formik} from "formik";
import React from "react";
import {DINOSAUR_EGG_CREATION_STEPS, DINOSAUR_INCUBATION_STEPS, DINOSAUR_TYPE_ID, DOCUMENT_NAME} from "./fieldNames";
import {Container} from "../container/container";
import {FormikSelect} from "../formik-select/formik-select";

const TextInputSteps = ({fieldName, ...props}) => {
    return <Input name={fieldName} {...props}/>
}

const TextInputStepsContainer = Container(TextInputSteps)

export const TechnologicalMapForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues,
        translations
    } = props

    const IncubationStepsField = TextInputStepsContainer(DINOSAUR_INCUBATION_STEPS, translations(`field.${DINOSAUR_INCUBATION_STEPS}.title`))

    const EggCreationStepsField = TextInputStepsContainer(DINOSAUR_EGG_CREATION_STEPS, translations(`field.${DINOSAUR_EGG_CREATION_STEPS}.title`))

    return (
        <Formik initialValues={initialValues}
                onSubmit={onSubmit}>
            <Form>
                <Input name={DOCUMENT_NAME} placeholder={translations(`field.${DOCUMENT_NAME}.placeholder`)}/>
                <FormikSelect name={DINOSAUR_TYPE_ID}
                        placeholder={translations(`field.${DINOSAUR_TYPE_ID}.placeholder`)}
                        {...props[DINOSAUR_TYPE_ID]}/>

                <FieldArray name={DINOSAUR_EGG_CREATION_STEPS}
                            component={EggCreationStepsField}/>

                <FieldArray name={DINOSAUR_INCUBATION_STEPS}
                            component={IncubationStepsField}/>
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