import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {AviaryCreationTaskFormContainer} from "./aviary-creation-task-form-container";

export const SuspendableAviaryCreationTaskFormContainer = props => {

    const API = useContext(ApiContext)

    const reader = useAsyncResource(API.aviary.getAllAviaryTypes.bind(API.aviary), {})

    return (
        <Suspense fallback={'Loading...'}>
            <AviaryCreationTaskFormContainer aviaryTypesReader={reader} {...props}/>
        </Suspense>
    )

}