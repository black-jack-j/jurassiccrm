import {DINOSAUR_TYPE_ID, TASK_PRIORITY_ID} from "./fieldNames";

export default form => ({
    ...form,
    dinosaurType: {id: form[DINOSAUR_TYPE_ID].value},
    priority: {id: form[TASK_PRIORITY_ID].value}
})