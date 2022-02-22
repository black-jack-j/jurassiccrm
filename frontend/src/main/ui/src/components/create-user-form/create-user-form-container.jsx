import {useAsyncResource} from "use-async-resource";
import {useDepartments} from "../../department/department";
import {CreateUserForm} from "./create-user-form";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import serialize from "../user-form/serialize";

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

        const TO = serialize(values)

        return API.user.createUser({body: TO}).then(() => {
            onSubmit && onSubmit(TO)
        }).catch(console.error)

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