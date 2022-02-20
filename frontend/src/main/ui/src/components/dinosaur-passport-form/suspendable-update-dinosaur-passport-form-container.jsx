import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {DinosaurPassportFormContainer} from "./dinosaur-passport-form-container";
import {DINOSAUR_INCUBATION_DATE, DINOSAUR_TYPE_ID} from "./fieldNames";

export const SuspendableUpdateDinosaurPassportFormContainer = props => {

    const API = useContext(ApiContext)

    const [dinosaurTypesReader] = useAsyncResource(API.dinosaur.getAllDinosaurTypes.bind(API.dinosaur), {})
    const [dinosaurStatusesReader] = useAsyncResource(API.dinosaur.getAllDinosaurStatuses.bind(API.dinosaur), {})

    const innerProps = {
        ...props,
        [DINOSAUR_TYPE_ID]: {
            ...props[DINOSAUR_TYPE_ID],
            disabled: true
        },
        [DINOSAUR_INCUBATION_DATE]: {
            ...props[DINOSAUR_INCUBATION_DATE],
            disabled: true
        }
    }

    return (
        <Suspense fallback={'Loading...'}>
            <DinosaurPassportFormContainer
                dinosaurTypesReader={dinosaurTypesReader}
                dinosaurStatusesReader={dinosaurStatusesReader}
                {...innerProps}
            />
        </Suspense>
    )

}