import {TASK_PRIORITY_ID} from "./fieldNames";

export default form => ({
    ...form,
    taskPriorityId: form[TASK_PRIORITY_ID].value
})