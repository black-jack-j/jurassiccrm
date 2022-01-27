import {Header, Menu, MenuItem} from "semantic-ui-react";
import {Formik} from "formik";
import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import React from "react";
import {DOCUMENT_NAME} from "./fieldNames";
import {useTranslation} from "react-i18next";

export const CreateDocumentForm = ({onSubmit, onCancel, children, title, formik, ...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create'})

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
                    {children(props)}
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