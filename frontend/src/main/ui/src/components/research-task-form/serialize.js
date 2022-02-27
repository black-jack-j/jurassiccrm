import {TASK_PRIORITY_ID} from "./fieldNames";

export default form => ({
    ...form,
    priority: {id: form[TASK_PRIORITY_ID].value}
})