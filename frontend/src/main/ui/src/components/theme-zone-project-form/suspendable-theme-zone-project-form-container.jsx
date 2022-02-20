import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {ThemeZoneProjectFormContainer} from "./theme-zone-project-form-container";

export const SuspendableThemeZoneProjectFormContainer = props => {

    const API = useContext(ApiContext)

    const [dinosaurTypesReader] = useAsyncResource(API.dinosaur.getAllDinosaurTypes.bind(API.dinosaur), {})
    const [aviaryTypesReader] = useAsyncResource(API.aviary.getAllAviaryTypes.bind(API.aviary), {})
    const [decorationTypesReader] = useAsyncResource(API.decorationType.getAllDecorations.bind(API.decorationType), {})

    return (
        <Suspense fallback={'Loading...'}>
            <ThemeZoneProjectFormContainer
                dinosaurTypesReader={dinosaurTypesReader}
                aviaryTypesReader={aviaryTypesReader}
                decorationTypesReader={decorationTypesReader}
                {...props}
            />
        </Suspense>
    )

}