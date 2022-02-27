import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";
import {SuspendableIncubationTaskFormContainer} from "../incubation-task-form/suspendable-incubation-task-form-container";
import serializeIT from "../incubation-task-form/serialize";
import {SuspendableAviaryCreationTaskFormContainer} from "../aviary-creation-task-form/suspendable-aviary-creation-task-form-container";
import serializeACT from "../aviary-creation-task-form/serialize";
import {SuspendableResearchTaskFormContainer} from "../research-task-form/suspendable-research-task-form-container";
import serializeR from "../research-task-form/serialize";
import deserializeIT from "../incubation-task-form/deserialize";
import deserializeACT from "../aviary-creation-task-form/deserialize";
import deserializeR from "../research-task-form/deserialize";

export const withType = taskType => {
    switch (taskType) {
        case TaskType.Incubation: return [SuspendableIncubationTaskFormContainer, serializeIT, deserializeIT]
        case TaskType.AviaryCreation: return [SuspendableAviaryCreationTaskFormContainer, serializeACT, deserializeACT]
        case TaskType.Research: return [SuspendableResearchTaskFormContainer, serializeR, deserializeR]
        default: return []
    }
}

export default withType