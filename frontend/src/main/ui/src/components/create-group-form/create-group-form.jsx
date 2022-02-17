import {useTranslation} from "react-i18next";
import React, {useContext} from "react";
import {GROUP_DESCRIPTION, GROUP_ICON, GROUP_MEMBERS, GROUP_NAME, GROUP_PRIVILEGES} from "../group-form/fieldNames";
import {GroupForm} from "../group-form/group-form";
import ApiContext from "../../api";

const userToOption = ({id, firstName, lastName}) => ({id, value: id, text: `${firstName} ${lastName}`})

const privilegeToOption = t => privilege => ({id: privilege, value: privilege, text: t(`crm.privilege.${privilege}`)})

export const CreateGroupForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues,
        usersReader,
        rolesReader,
    } = props

    const {t} = useTranslation()

    const translations = key => t(`crm.group.form.create.${key}`)

    const API = useContext(ApiContext)

    const groupMemberOptions = usersReader().map(userToOption)
    const privilegesOptions = rolesReader().map(privilegeToOption)

    const submit = values => {

        onSubmit && onSubmit(values)

        const TO = {
            [GROUP_NAME]: values[GROUP_NAME],
            [GROUP_DESCRIPTION]: values[GROUP_DESCRIPTION],
            [GROUP_MEMBERS]: values[GROUP_MEMBERS].map(entity => entity.value),
            [GROUP_PRIVILEGES]: values[GROUP_PRIVILEGES].map(entity => entity.value),
        }

        fetch(values[GROUP_ICON])
            .then(it => it.blob())
            .then(avatar => API.group.createGroup({avatar, groupInfo: JSON.stringify(TO)}))
            .catch(console.error)
    }

    const innerProps = {
        [GROUP_MEMBERS]: {
            ...props[GROUP_MEMBERS],
            options: groupMemberOptions
        },
        [GROUP_PRIVILEGES]: {
            ...props[GROUP_PRIVILEGES],
            options: privilegesOptions
        },
    }

    return (
        <GroupForm
            translations={translations}
            onSubmit={submit}
            onCancel={onCancel}
            initialValues={initialValues}
            {...innerProps}
        />
    )

}

export default CreateGroupForm