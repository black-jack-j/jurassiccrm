import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {AviaryCreationTaskFormContainer} from "./aviary-creation-task-form-container";

export const SuspendableAviaryCreationTaskFormContainer = props => {

    const API = useContext(ApiContext)

    const [aviaryReader] = useAsyncResource(API.aviary.getAllAviaryTypes.bind(API.aviary), {})
    const [priorityReader] = useAsyncResource(API.task.getPriorities.bind(API.task), {})

    return (
        <Suspense fallback={'Loading...'}>
            <AviaryCreationTaskFormContainer
                aviaryTypesReader={aviaryReader}
                prioritiesReader={priorityReader}
                {...props}
            />
        </Suspense>
    )

}