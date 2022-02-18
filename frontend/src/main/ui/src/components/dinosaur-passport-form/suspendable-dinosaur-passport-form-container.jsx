import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {DinosaurPassportFormContainer} from "./dinosaur-passport-form-container";

export const SuspendableDinosaurPassportFormContainer = props => {

    const API = useContext(ApiContext)

    const [dinosaurTypesReader] = useAsyncResource(API.dinosaur.getAllDinosaurTypes.bind(API.dinosaur), {})
    const [dinosaurStatusesReader] = useAsyncResource(API.dinosaur.getAllDinosaurStatuses.bind(API.dinosaur), {})

    return (
        <Suspense fallback={'Loading...'}>
            <DinosaurPassportFormContainer
                dinosaurTypesReader={dinosaurTypesReader}
                dinosaurStatusesReader={dinosaurStatusesReader}
                {...props}
            />
        </Suspense>
    )

}