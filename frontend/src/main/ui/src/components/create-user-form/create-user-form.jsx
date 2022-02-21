import {UserForm} from "../user-form/user-form";
import React, {useContext} from "react";
import {USER_DEPARTMENT, USER_GROUPS} from "../user-form/fieldNames";
import {useTranslation} from "react-i18next";
import ApiContext from "../../api";

const groupToOption = ({id, name}) => ({id, value: id, text: name})
const departmentToOption = t=> (department) => {
    return ({key: department, value: department, text: t(`crm.department.${department}`)})
}

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
        groupsReader,
        departmentsReader
    } = props;

    const groups = groupsReader()
    const departments = departmentsReader()

    const API = useContext(ApiContext)



    return (
        <Form
            initialValues={initialValues}
            groups={groups}
            departments={departments}
            onSubmit={onSubmit}
            onCancel={onCancel}
        />
    )

}