import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";
import EditGroupForm from "./edit-group-form";

export const SuspendableEditGroupForm = props => {

    const {
        id,
        onSubmit,
        onCancel
    } = props

    const API = useContext(ApiContext)

    const [usersReader] = useAsyncResource(API.user.getUsersSimple.bind(API.user), {})
    const [rolesReader] = useAsyncResource(API.role.getAllRoles.bind(API.role), {})
    const [groupReader] = useAsyncResource(API.group.getGroup.bind(API.group), {id})

    return (
        <Suspense fallback={'Loading...'}>
            <EditGroupForm
                id={id}
                onSubmit={onSubmit}
                onCancel={onCancel}
                usersReader={usersReader}
                rolesReader={rolesReader}
                groupReader={groupReader}
            />
        </Suspense>
    )

}