import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {IncubationTaskFormContainer} from "./incubation-task-form-container";

export const SuspendableIncubationTaskFormContainer = props => {

    const API = useContext(ApiContext)

    const reader = useAsyncResource(API.dinosaur.getAllDinosaurTypes.bind(API.dinosaur), {})

    return (
        <Suspense fallback={'Loading...'}>
            <IncubationTaskFormContainer dinosaurTypesReader={reader} {...props}/>
        </Suspense>
    )

}