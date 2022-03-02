import {Header} from "semantic-ui-react";
import React, {useContext} from "react";
import {useTranslation} from "react-i18next";
import {withType} from "./utils";
import ApiContext from "../../api";
import './create-document-form.css'

export const CreateDocumentForm = props => {

    const {
        documentType,
        onSubmit,
        onCancel
    } = props

    const {t} = useTranslation('translation', {keyPrefix: `crm.document.form.create.${documentType}`})
    const API = useContext(ApiContext)
    const [Form, onFormSubmit, initialValues] = withType(documentType)

    const formSubmit = onFormSubmit(API)

    const submit = values => {
        onSubmit && onSubmit(values)
        formSubmit({documentType, ...values})
    }

    return (
        <div className={'create-document-form'}>
            <Header as='h2' className={"center aligned"}>{t('title')}</Header>
            <Form onSubmit={submit} onCancel={onCancel} initialValues={initialValues} translations={t}/>
        </div>
    )

}