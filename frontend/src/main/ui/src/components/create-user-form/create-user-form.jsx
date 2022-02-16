import {UserForm} from "../user-form/user-form";
import React, {useContext} from "react";
import {useGroupsSimple} from "../../group/group";
import {
    USER_DEPARTMENT,
    USER_FIRSTNAME,
    USER_GROUPS, USER_ICON,
    USER_LASTNAME,
    USER_PASSWORD,
    USER_USERNAME
} from "../user-form/fieldNames";
import {useTranslation} from "react-i18next";
import {useDepartments} from "../../department/department";
import ApiContext from "../../api";

const groupToOption = ({id, name}) => ({id, value: id, text: name})
const departmentToOption = t=> (department) => {
    return ({key: department, value: department, text: t(`crm.department.${department}`)})
}

const DefaultPlaceholder = () => <></>

const Form = ({initialValues, onSubmit, onCancel, groups, departments}) => {

    const {t} = useTranslation()

    const innerProps = {
        [USER_GROUPS]: {
            options: groups.map(groupToOption)
        },
        [USER_DEPARTMENT]: {
            options: departments.map(departmentToOption(t))
        }
    }

    return (
        <UserForm
            initialValues={initialValues}
            onSubmit={onSubmit}
            onCancel={onCancel}
            translations={key => t(`crm.user.form.create.${key}`)}
            {...innerProps}
        />
    )
}

export const CreateUserForm = props => {

    const {
        initialValues,
        onSubmit,
        onCancel,
        LoadingPlaceholder = DefaultPlaceholder,
        ErrorPlaceholder = DefaultPlaceholder
    } = props;

    const [groupResult, refreshGroups] = useGroupsSimple()
    const [departmentResult, refreshDepartments] = useDepartments()
    const API = useContext(ApiContext)

    const submit = values => {

        const TO = {
            [USER_FIRSTNAME]: values[USER_FIRSTNAME],
            [USER_LASTNAME]: values[USER_LASTNAME],
            [USER_USERNAME]: values[USER_USERNAME],
            [USER_PASSWORD]: values[USER_PASSWORD],
            [USER_DEPARTMENT]: values[USER_DEPARTMENT],
            [USER_GROUPS]: values[USER_GROUPS].map(({value}) => value)
        }

        fetch(values[USER_ICON])
            .then(it => it.blob())
            .then(avatar => {
                onSubmit && onSubmit({...TO, avatar})
                return API.user.createUser({avatar, userInfo: JSON.stringify(TO)})
            })
            .then(console.log)
            .catch(console.error)

    }

    if (groupResult.state === 'loaded') {
        return (
            <Form
                initialValues={initialValues}
                groups={groupResult.groups}
                departments={departmentResult.departments}
                onSubmit={submit}
                onCancel={onCancel}
            />
        )
    } else if (groupResult.state === 'loading') {
        return <LoadingPlaceholder />
    } else if (groupResult.state === 'error') {
        return <ErrorPlaceholder />
    }

}