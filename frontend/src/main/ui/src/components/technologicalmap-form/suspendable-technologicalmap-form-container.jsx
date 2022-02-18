import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {TechnologicalMapFormContainer} from "./technologicalmap-form-container";

export const SuspendableTechnologicalMapFormContainer = props => {

    const API = useContext(ApiContext)

    const [dinosaurTypesReader] = useAsyncResource(API.dinosaur.getAllDinosaurTypes.bind(API.dinosaur), {})

    return (
        <Suspense fallback={'Loading...'}>
            <TechnologicalMapFormContainer
                dinosaurTypesReader={dinosaurTypesReader}
                {...props}
            />
        </Suspense>
    )

}