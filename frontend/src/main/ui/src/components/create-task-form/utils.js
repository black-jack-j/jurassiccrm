import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";
import {SuspendableIncubationTaskFormContainer} from "../incubation-task-form/suspendable-incubation-task-form-container";
import serializeIT from "../incubation-task-form/serialize";
import initialValuesIT from "../incubation-task-form/initialValues";
import {SuspendableAviaryCreationTaskFormContainer} from "../aviary-creation-task-form/suspendable-aviary-creation-task-form-container";
import serializeACT from "../aviary-creation-task-form/serialize";
import initialValuesACT from "../aviary-creation-task-form/initialValues";
import {SuspendableResearchTaskFormContainer} from "../research-task-form/suspendable-research-task-form-container";
import serializeR from "../research-task-form/serialize";
import initialValuesR from "../research-task-form/initialValues";

export const withType = taskType => {
    switch (taskType) {
        case TaskType.Incubation: return [SuspendableIncubationTaskFormContainer, serializeIT, initialValuesIT]
        case TaskType.AviaryCreation: return [SuspendableAviaryCreationTaskFormContainer, serializeACT, initialValuesACT]
        case TaskType.Research: return [SuspendableResearchTaskFormContainer, serializeR, initialValuesR]
        default: return []
    }
}

export default withType