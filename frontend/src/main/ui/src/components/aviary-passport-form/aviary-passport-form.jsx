import {Form, Input, ResetButton, Select, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {Menu, MenuItem} from "semantic-ui-react";
import {Formik} from "formik";
import React from "react";
import {
    AVIARY_BUILT_DATE,
    AVIARY_CODE,
    AVIARY_DESCRIPTION,
    AVIARY_REVISION_PERIOD,
    AVIARY_SQUARE,
    AVIARY_STATUS,
    AVIARY_TYPE_ID,
    DOCUMENT_NAME
} from "./fieldNames";
import {FormikSelect} from "../formik-select/formik-select";

export const AviaryPassportForm = props => {

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
                <FormikSelect name={AVIARY_TYPE_ID}
                        placeholder={translations(`field.${AVIARY_TYPE_ID}.placeholder`)}
                        {...props[AVIARY_TYPE_ID]}/>

                <Input name={AVIARY_SQUARE}
                       placeholder={translations(`field.${AVIARY_SQUARE}.placeholder`)}
                       {...props[AVIARY_SQUARE]}/>

                <Input name={AVIARY_BUILT_DATE}
                       placeholder={translations(`field.${AVIARY_BUILT_DATE}.placeholder`)}
                       {...props[AVIARY_BUILT_DATE]}/>

                <Input name={AVIARY_REVISION_PERIOD}
                       placeholder={translations(`field.${AVIARY_REVISION_PERIOD}.placeholder`)}
                       {...props[AVIARY_REVISION_PERIOD]}/>

                <Input name={AVIARY_CODE}
                       placeholder={translations(`field.${AVIARY_CODE}.placeholder`)}
                       {...props[AVIARY_CODE]}/>

                <Input name={AVIARY_STATUS}
                       placeholder={translations(`field.${AVIARY_STATUS}.placeholder`)}
                       {...props[AVIARY_STATUS]}/>
                <TextArea name={AVIARY_DESCRIPTION}
                          placeholder={translations(`field.${AVIARY_DESCRIPTION}.placeholder`)}
                          {...props[AVIARY_DESCRIPTION]}/>
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