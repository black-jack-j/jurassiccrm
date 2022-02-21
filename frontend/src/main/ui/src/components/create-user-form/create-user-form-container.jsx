import {useAsyncResource} from "use-async-resource";
import {useDepartments} from "../../department/department";
import {CreateUserForm} from "./create-user-form";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {
    USER_DEPARTMENT,
    USER_FIRSTNAME,
    USER_GROUPS,
    USER_ICON,
    USER_LASTNAME,
    USER_PASSWORD,
    USER_USERNAME
} from "../user-form/fieldNames";

export const CreateUserFormContainer = props => {

    const {
        onSubmit,
        ...rest
    } = props

    const API = useContext(ApiContext)

    const [groupsReader] = useAsyncResource(API.group.getAllGroups.bind(API.group), {})

    const [departments] = useDepartments()

    const departmentReader = () => departments

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

    return (
        <Suspense fallback={'Loading...'}>
            <CreateUserForm
                groupsReader={groupsReader}
                departmentsReader={departmentReader}
                onSubmit={submit}
                {...rest}
            />
        </Suspense>
    )

}