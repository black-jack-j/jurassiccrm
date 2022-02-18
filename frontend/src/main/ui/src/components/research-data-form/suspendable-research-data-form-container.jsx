import React, {Suspense} from "react";
import {ResearchDataFormContainer} from "./research-data-form-container";

export const SuspendableResearchDataFormContainer = props => {

    return (
        <Suspense fallback={'Loading...'}>
            <ResearchDataFormContainer {...props}/>
        </Suspense>
    )

}