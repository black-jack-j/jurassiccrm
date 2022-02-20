import {DINOSAUR_TYPE_ID} from "./fieldNames";
import {TASK_PRIORITY_ID} from "../aviary-creation-task-form/fieldNames";

export default form => ({
    ...form,
    dinosaurTypeId: form[DINOSAUR_TYPE_ID].value,
    taskPriorityId: form[TASK_PRIORITY_ID].value
})