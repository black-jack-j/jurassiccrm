import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {ResearchTaskFormContainer} from "./research-task-form-container";

export const SuspendableResearchTaskFormContainer = props => {

    return (
        <Suspense fallback={'Loading...'}>
            <ResearchTaskFormContainer {...props}/>
        </Suspense>
    )

}