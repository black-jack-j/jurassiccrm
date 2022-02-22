import {TASK_PRIORITY_ID} from "./fieldNames";

export default TO => ({
    ...TO,
    [TASK_PRIORITY_ID]: {
        value: TO.taskPriority.id,
        text: TO.taskPriority.name
    }
})