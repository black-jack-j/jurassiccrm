import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {withType} from "./utils";
import {useTranslation} from "react-i18next";
import {Header} from "semantic-ui-react";


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

    const [documentReader] = useAsyncResource(API.document.getDocumentById.bind(API.document,), {documentType, id})

    const [Form, onSubmitBuilder, deserializer] = withType(documentType)

    const updateDocument = onSubmitBuilder(API)(id)

    const submit = values => {
        updateDocument(values).then(() => {
            onSubmit && onSubmit(values)
        })
    }

    return (
        <>
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
        </>
    )

}