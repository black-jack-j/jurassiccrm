import {Header} from "semantic-ui-react";
import {Formik} from "formik";
import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import React from "react";

export const CreateDocumentForm = ({onSubmit, onCancel, children, initialValues, ...props}) => {

    return (
        <>
            <Header as='h2'>Создать документ</Header>
            <Formik enableReinitialize
                    initialValues={initialValues}
                    onSubmit={onSubmit}>
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