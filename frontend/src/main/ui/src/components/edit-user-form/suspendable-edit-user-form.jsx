import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";
import {useDepartments} from "../../department/department";
import {EditUserForm} from "./edit-user-form";

export const SuspendableEditUserForm = props => {

    const {
        userId,
        ...rest
    } = props

    const API = useContext(ApiContext)

    const [userReader] = useAsyncResource(API.user.getUserById.bind(API.user), {userId})
    const [groupsReader] = useAsyncResource(API.group.getAllGroups.bind(API.group), {})

    const departments = [useDepartments()]

    const departmentsReader = () => departments

    return (
        <Suspense fallback={'Loading...'}>
            <EditUserForm
                userId={userId}
                userReader={userReader}
                groupsReader={groupsReader}
                departmentsReader={departmentsReader}
                {...rest}
            />
        </Suspense>
    )
}