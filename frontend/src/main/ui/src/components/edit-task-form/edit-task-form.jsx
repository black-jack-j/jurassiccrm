import {Form} from "formik-semantic-ui-react";
import {useTranslation} from "react-i18next";
import {Header} from "semantic-ui-react";
import withType from "./utils";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";

const getTaskById = (taskType, id) => fetch(`/api/task/${taskType}/${id}`).then(it => it.json())

export const EditTaskForm = props => {

    const {
        taskReader,
        Form,
        onSubmit,
        onCancel,
        translations
    } = props

    const initialValues = taskReader()

    return (
        <>
            <Header content={translations('title')}/>
            <Form
                onSubmit={onSubmit}
                onCancel={onCancel}
                translations={translations}
                initialValues={initialValues}
            />
        </>
    )
}

export const EditTaskFormContainer = props => {

    const {
        taskId,
        taskType,
        onSubmit,
        ...rest
    } = props


    const API = useContext(ApiContext)

    const [taskReader] = useAsyncResource(getTaskById, taskType, taskId)
    const {t} = useTranslation('translation', {keyPrefix: `crm.task.form.update.${taskType}`})
    const [Form, serialize, deserialize] = withType(taskType)

    const submit = values => API.task.updateTask(
        {taskId, body: serialize(values)},
        {body: JSON.stringify(serialize(values))}
    ).then(() => {
        onSubmit && onSubmit(serialize(values))
    }).catch(console.error)

    return (
        <Suspense fallback={'Loading...'}>
            <EditTaskForm
                taskReader={() => taskReader(deserialize)}
                Form={Form}
                onSubmit={submit}
                translations={t}
                {...rest}
            />
        </Suspense>
    )

}