import {DINOSAUR_TYPE_ID} from "./fieldNames";
import {TASK_PRIORITY_ID} from "../aviary-creation-task-form/fieldNames";

export default TO => ({
    ...TO,
    [DINOSAUR_TYPE_ID]: {
        value: TO.dinosaurType.id,
        text: TO.dinosaurType.name
    },
    [TASK_PRIORITY_ID]: {
        value: TO.taskPriority.id,
        text: TO.taskPriority.name
    }
})