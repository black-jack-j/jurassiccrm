import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";
import {AviaryPassportFormContainer} from "./aviary-passport-form-container";
import {AVIARY_BUILT_DATE, AVIARY_TYPE_ID} from "./fieldNames";

export const SuspendableUpdateAviaryPassportFormContainer = props => {

    const API = useContext(ApiContext)

    const [aviaryTypesReader] = useAsyncResource(API.aviary.getAllAviaryTypes.bind(API.aviary), {})

    const innerProps = {
        ...props,
        [AVIARY_TYPE_ID]: {
            ...props[AVIARY_TYPE_ID],
            disabled: true
        },
        [AVIARY_BUILT_DATE]: {
            ...props[AVIARY_BUILT_DATE],
            disabled: true
        }
    }

    return (
        <Suspense fallback={'Loading...'}>
            <AviaryPassportFormContainer
                aviaryTypesReader={aviaryTypesReader}
                {...innerProps}
            />
        </Suspense>
    )

}