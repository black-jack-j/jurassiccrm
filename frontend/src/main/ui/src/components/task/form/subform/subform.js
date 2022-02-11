import AviarySubformInitialValues from './createaviary/initialValues'
import ResearchSubformInitialValues from './research/initialValues'
import IncubationSubformInitialValues from './incubation/initialValues'
import {IncubationSubFormContainer} from "./incubation/incubation-subform-container";
import {
    AviaryTaskDTOToJSON,
    IncubationTaskDTOToJSON,
    ResearchTaskDTOToJSON,
    TaskTOTaskTypeEnum
} from "../../../../generatedclient/models";
import {AviarySubFormContainer} from "./createaviary/aviary-subform-container";
import {ResearchSubForm} from "./research";

export const INCUBATION_TYPE = 'INCUBATION'
export const RESEARCH_TYPE = 'RESEARCH'
export const AVIARY_CREATION_TYPE = 'AVIARY_CREATION'

export const withType = (taskType) => {
    switch (taskType) {
        case TaskTOTaskTypeEnum.Incubation: {
            return [IncubationSubFormContainer, IncubationSubformInitialValues, IncubationTaskDTOToJSON]
        }
        case TaskTOTaskTypeEnum.Research: {
            return [ResearchSubForm, ResearchSubformInitialValues, ResearchTaskDTOToJSON]
        }
        case TaskTOTaskTypeEnum.AviaryCreation: {
            return [AviarySubFormContainer, AviarySubformInitialValues, AviaryTaskDTOToJSON]
        }
        default: {
            console.error(`Unknown task type: ${taskType}`)
            return [() => {}]
        }
    }
}