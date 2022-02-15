import {useGroupById} from "../../group/group";
import {GROUP_DESCRIPTION, GROUP_ICON, GROUP_MEMBERS, GROUP_NAME, GROUP_PRIVILEGES} from "../group-form/fieldNames";
import React, {useContext} from "react";
import ApiContext from "../../api";
import {GroupForm} from "../group-form/group-form";
import {useTranslation} from "react-i18next";

const userToOption = ({id, firstName, lastName}) => ({id, value: id, text: `${firstName} ${lastName}`})

const privilegeToOption = t => privilege => ({id: privilege, value: privilege, text: t(`crm.privilege.${privilege}`)})

const mapGroupTOtoFormik = t => groupTO => ({
    [GROUP_NAME]: groupTO.name,
    [GROUP_DESCRIPTION]: groupTO.description,
    [GROUP_MEMBERS]: Array.from(groupTO.users).map(userToOption),
    [GROUP_PRIVILEGES]: Array.from(groupTO.roles).map(privilegeToOption(t)),
    [GROUP_ICON]: `/api/group/${groupTO.id}/icon`
})

export const EditGroupForm = props => {

    const {
        id,
        onSubmit,
        onCancel,
        LoadingPlaceholder,
        ErrorPlaceholder
    } = props

    const API = useContext(ApiContext)

    const {t} = useTranslation()


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
            .then(avatar => {
                return API.group.updateGroup({groupId: id, avatar, groupInfo: JSON.stringify(TO)})
            })
            .then(console.log)
            .catch(console.error)
    }

    const {group, state} = useGroupById(id)

    if (state === 'loading') {
        return <LoadingPlaceholder />
    } else if (state === 'loaded') {
        const initialValues = mapGroupTOtoFormik(t)(group)
        return (
            <GroupForm
                translations={key => t(`crm.group.form.update.${key}`)}
                initialValues={initialValues}
                onSubmit={submit}
                onCancel={onCancel}
            />
        )
    } else {
        console.error("something went wrong")
        return <ErrorPlaceholder/>
    }

}