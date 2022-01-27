import {Header} from "semantic-ui-react";
import {Formik} from "formik";
import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import React from "react";

export const CreateDocumentForm = ({onSubmit, onCancel, children, title, formik, ...props}) => {

    return (
        <>
            <Header as='h2' className={"center aligned"}>{title}</Header>
            <Formik enableReinitialize
                    initialValues={formik.initialValues}
                    onSubmit={values => {
                        console.log(values)
                        onSubmit(values)
                    }}>
                <Form>
                    <Input name='name' placeholder={'Название документа'}/>
                    {children(props)}
                    <SubmitButton positive>Сохранить</SubmitButton>
                    <ResetButton negative onClick={onCancel}>Отмена</ResetButton>
                </Form>
            </Formik>
        </>

    )

}