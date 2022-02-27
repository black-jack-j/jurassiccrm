import {AVIARY_TYPE_ID, TASK_PRIORITY_ID} from "./fieldNames";

export default form => ({
    ...form,
    aviaryType: {id: form[AVIARY_TYPE_ID].value},
    priority: {id: form[TASK_PRIORITY_ID].value}
})