import {resourceCache, useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {withType} from "./utils";
import {useTranslation} from "react-i18next";
import {Header} from "semantic-ui-react";
import './update-document-form.css'

const getDocumentById = ({documentType, id}) => fetch(`/api/document/${documentType}/${id}`).then(response => response.json())

const UpdateDocumentFormContainer = props => {

    const {
        documentReader,
        deserializer,
        Form,
        onSubmit,
        onCancel,
        translations
    } = props

    const initialValues = documentReader(deserializer)

    return (
        <Form
            initialValues={initialValues}
            onSubmit={onSubmit}
            onCancel={onCancel}
            translations={translations}
        />
    )

}

export const UpdateDocumentForm = props => {

    const {
        documentType,
        id,
        onSubmit,
        onCancel
    } = props

    const {t} = useTranslation('translation', {keyPrefix: `crm.document.form.update.${documentType}`})

    const API = useContext(ApiContext)

    const [documentReader] = useAsyncResource(getDocumentById, {documentType, id})

    const [Form, onSubmitBuilder, deserializer] = withType(documentType)

    const updateDocument = onSubmitBuilder(API)(id)

    const submit = values => {
        updateDocument(values).then(() => {
            onSubmit && onSubmit(values)
            resourceCache(getDocumentById).clear()
        })
    }

    return (
        <div className={'update-document-form'}>
            <Header>{t('title')}</Header>
            <Suspense fallback={'Loading...'}>
                <UpdateDocumentFormContainer
                    documentReader={documentReader}
                    Form={Form}
                    onSubmit={submit}
                    onCancel={onCancel}
                    deserializer={deserializer}
                    translations={t}
                />
            </Suspense>
        </div>
    )

}