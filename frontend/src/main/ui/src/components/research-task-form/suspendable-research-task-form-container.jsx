import {useAsyncResource} from "use-async-resource";
import React, {Suspense, useContext} from "react";
import ApiContext from "../../api";
import {ResearchTaskFormContainer} from "./research-task-form-container";

export const SuspendableResearchTaskFormContainer = props => {

    const API = useContext(ApiContext)
    const [priorityReader] = useAsyncResource(API.task.getPriorities.bind(API.task), {})

    return (
        <Suspense fallback={'Loading...'}>
            <ResearchTaskFormContainer
                prioritiesReader={priorityReader}
                {...props}
            />
        </Suspense>
    )

}