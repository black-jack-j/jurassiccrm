import {TASK_PRIORITY_ID} from "../aviary-creation-task-form/fieldNames";

export default form => ({
    ...form,
    taskPriorityId: form[TASK_PRIORITY_ID].value
})