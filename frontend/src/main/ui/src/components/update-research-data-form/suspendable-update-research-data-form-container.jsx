import {RESEARCH_IS_NEW, RESEARCH_NAME_ID} from "../research-data-form/fieldNames";
import {ResearchDataFormContainer} from "../research-data-form/research-data-form-container";
import React, {Suspense} from "react";

export const SuspendableUpdateResearchDataFormContainer = props => {

    const innerProps = {
        ...props,
        [RESEARCH_NAME_ID]: {
            ...props[RESEARCH_NAME_ID],
            disabled: true
        },
        [RESEARCH_IS_NEW]: {
            ...props[RESEARCH_IS_NEW],
            disabled: true
        }
    }

    return (
        <Suspense fallback={'Loading...'}>
            <ResearchDataFormContainer {...innerProps}/>
        </Suspense>
    )

}