import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";
import CreateGroupForm from "./create-group-form";

export const SuspendableCreateGroupForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues
    } = props

    const API = useContext(ApiContext)

    const [usersReader] = useAsyncResource(API.user.getUsersSimple.bind(API.user), {})
    const [rolesReader] = useAsyncResource(API.role.getAllRoles.bind(API.role), {})

    return (
        <Suspense fallback={'Loading...'}>
            <CreateGroupForm
                onSubmit={onSubmit}
                onCancel={onCancel}
                initialValues={initialValues}
                usersReader={usersReader}
                rolesReader={rolesReader}
            />
        </Suspense>
    )

}