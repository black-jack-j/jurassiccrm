import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {TechnologicalMapFormContainer} from "./technologicalmap-form-container";
import {DINOSAUR_TYPE_ID} from "./fieldNames";

export const SuspendableUpdateTechnologicalMapFormContainer = props => {

    const API = useContext(ApiContext)

    const [dinosaurTypesReader] = useAsyncResource(API.dinosaur.getAllDinosaurTypes.bind(API.dinosaur), {})

    const innerProps = {
        ...props,
        [DINOSAUR_TYPE_ID]: {
            ...props[DINOSAUR_TYPE_ID],
            disabled: true
        }
    }

    return (
        <Suspense fallback={'Loading...'}>
            <TechnologicalMapFormContainer
                dinosaurTypesReader={dinosaurTypesReader}
                {...innerProps}
            />
        </Suspense>
    )

}