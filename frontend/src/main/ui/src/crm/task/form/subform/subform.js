import AviarySubform, {paramsFormatter as aviaryFormatter} from './createaviary/index'
import ResearchSubform, {paramsFormatter as researchFormatter} from './research/index'
import IncubationSubform, {paramsFormatter as incubationFormatter} from './incubation/index'

import AviarySubformInitialValues from './createaviary/initialValues'
import ResearchSubformInitialValues from './research/initialValues'
import IncubationSubformInitialValues from './incubation/initialValues'

export const INCUBATION_TYPE = 'INCUBATION'
export const RESEARCH_TYPE = 'RESEARCH'
export const AVIARY_CREATION_TYPE = 'AVIARY_CREATION'

export const withType = (taskType) => {
    switch (taskType) {
        case INCUBATION_TYPE: {
            return [IncubationSubform, IncubationSubformInitialValues, incubationFormatter]
        }
        case RESEARCH_TYPE: {
            return [ResearchSubform, ResearchSubformInitialValues, researchFormatter]
        }
        case AVIARY_CREATION_TYPE: {
            return [AviarySubform, AviarySubformInitialValues, aviaryFormatter]
        }
        default: {
            console.error(`Unknown task type: ${taskType}`)
            return [() => {}]
        }
    }
}