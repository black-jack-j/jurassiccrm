import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";
import {AviaryPassportFormContainer} from "./aviary-passport-form-container";

export const SuspendableAviaryPassportFormContainer = props => {

    const API = useContext(ApiContext)

    const [aviaryTypesReader] = useAsyncResource(API.aviary.getAllAviaryTypes.bind(API.aviary), {})

    return (
        <Suspense fallback={'Loading...'}>
            <AviaryPassportFormContainer
                aviaryTypesReader={aviaryTypesReader}
                {...props}
            />
        </Suspense>
    )

}