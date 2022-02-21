import {AVIARY_TYPE_ID, TASK_PRIORITY_ID} from "./fieldNames";

export default form => ({
    ...form,
    aviaryTypeId: form[AVIARY_TYPE_ID].value,
    taskPriorityId: form[TASK_PRIORITY_ID].value
})