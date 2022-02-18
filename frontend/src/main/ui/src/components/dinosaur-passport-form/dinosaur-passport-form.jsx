import {Form, Input, ResetButton, Select, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {Menu, MenuItem} from "semantic-ui-react";
import {Formik} from "formik";
import React from "react";
import {
    DINOSAUR_DESCRIPTION,
    DINOSAUR_HEIGHT,
    DINOSAUR_INCUBATION_DATE,
    DINOSAUR_NAME,
    DINOSAUR_REVISION_PERIOD,
    DINOSAUR_STATUS,
    DINOSAUR_TYPE,
    DINOSAUR_WEIGHT,
    DOCUMENT_NAME
} from "./fieldNames";

export const DinosaurPassportForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues,
        translations
    } = props

    return (
        <Formik initialValues={initialValues}
                onSubmit={onSubmit}>
            <Form>
                <Input name={DOCUMENT_NAME} placeholder={translations(`field.${DOCUMENT_NAME}.placeholder`)}/>
                <Select name={DINOSAUR_TYPE}
                        placeholder={translations(`field.${DINOSAUR_TYPE}.placeholder`)}
                        {...props[DINOSAUR_TYPE]}/>

                <Input name={DINOSAUR_NAME}
                       placeholder={translations(`field.${DINOSAUR_NAME}.placeholder`)}
                       {...props[DINOSAUR_NAME]}/>

                <Input name={DINOSAUR_WEIGHT}
                       placehiolder={translations(`field.${DINOSAUR_WEIGHT}.placeholder`)}
                       {...props[DINOSAUR_WEIGHT]}/>

                <Input name={DINOSAUR_HEIGHT}
                       placeholder={translations(`field.${DINOSAUR_HEIGHT}.placeholder`)}
                       {...props[DINOSAUR_HEIGHT]}/>

                <Input name={DINOSAUR_INCUBATION_DATE}
                       placeholder={translations(`field.${DINOSAUR_INCUBATION_DATE}.placeholder`)}
                       {...props[DINOSAUR_INCUBATION_DATE]}/>

                <Input name={DINOSAUR_REVISION_PERIOD}
                       placeholder={translations(`field.${DINOSAUR_REVISION_PERIOD}.placeholder`)}
                       {...props[DINOSAUR_REVISION_PERIOD]}/>

                <Select name={DINOSAUR_STATUS}
                        placeholder={translations(`field.${DINOSAUR_STATUS}.placeholder`)}
                        {...props[DINOSAUR_STATUS]}/>

                <TextArea name={DINOSAUR_DESCRIPTION}
                          placeholder={translations(`field.${DINOSAUR_DESCRIPTION}.placeholder`)}
                          {...props[DINOSAUR_DESCRIPTION]}/>
                <Menu secondary>
                    <MenuItem>
                        <SubmitButton style={{width: 150}} floated={'left'} positive>{translations('submit')}</SubmitButton>
                    </MenuItem>
                    <MenuItem position={'right'}>
                        <ResetButton style={{width: 150}} floated={'right'} negative onClick={onCancel}>{translations('cancel')}</ResetButton>
                    </MenuItem>
                </Menu>
            </Form>
        </Formik>
    )

}