import React, {useContext} from "react";
import deserialize from "../user-form/deserialize";
import {USER_DEPARTMENT, USER_GROUPS, USER_PASSWORD, USER_PASSWORD_CHECK} from "../user-form/fieldNames";
import {UserForm} from "../user-form/user-form";
import {useTranslation} from "react-i18next";
import ApiContext from "../../api";
import serialize from "../user-form/serialize";

const groupToOption = ({id, name}) => ({id, value: id, text: name})
const departmentToOption = t=> (department) => {
    return ({key: department, value: department, text: t(`crm.department.${department}`)})
}

export const EditUserForm = props => {

    const {
        userId,
        userReader,
        groupsReader,
        departmentsReader,
        onSubmit,
        ...rest
    } = props

    const {t} = useTranslation()
    const initialValues = userReader(deserialize)
    initialValues[USER_DEPARTMENT].text = t(`crm.department.${initialValues[USER_DEPARTMENT].value}`)

    const API = useContext(ApiContext)

    const submit = values => API.user.updateUser({userId, body: serialize(values)}).then(() => {
        onSubmit && onSubmit(deserialize(values))
    }).catch(console.error)

    const innerProps = {
        ...rest,
        [USER_PASSWORD]: {
            ...rest[USER_PASSWORD],
            disabled: true
        },
        [USER_PASSWORD_CHECK]: {
            ...rest[USER_PASSWORD_CHECK],
            disabled: true
        },
        [USER_GROUPS]: {
            ...rest[USER_GROUPS],
            options: groupsReader().map(groupToOption)
        },
        [USER_DEPARTMENT]: {
            ...rest[USER_DEPARTMENT],
            options: departmentsReader().map(departmentToOption)
        }
    }

    return (
        <UserForm
            initialValues={initialValues}
            translations={key => t(`crm.user.form.update.${key}`)}
            onSubmit={submit}
            {...innerProps}
        />
    )

}