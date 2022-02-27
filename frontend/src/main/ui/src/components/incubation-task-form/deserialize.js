import {DINOSAUR_TYPE_ID} from "./fieldNames";
import {TASK_PRIORITY_ID} from "../aviary-creation-task-form/fieldNames";

export default TO => ({
    ...TO,
    [DINOSAUR_TYPE_ID]: {
        value: TO.dinosaurType ? TO.dinosaurType.id : '',
        text: TO.dinosaurType ? TO.dinosaurType.name : ''
    },
    [TASK_PRIORITY_ID]: {
        value: TO.priority.id,
        text: TO.priority.name
    }
})