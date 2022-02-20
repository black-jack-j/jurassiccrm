import {
    AVIARY_SQUARE,
    AVIARY_TYPE_ID,
    TASK_ASSIGNEE_ID,
    TASK_DESCRIPTION,
    TASK_NAME,
    TASK_PRIORITY_ID, TASK_TYPE
} from "./fieldNames";
import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";

export default {
    [AVIARY_TYPE_ID]: {
        value: '',
        text: ''
    },
    [TASK_NAME]: '',
    [TASK_DESCRIPTION]: '',
    [TASK_PRIORITY_ID]: '',
    [TASK_ASSIGNEE_ID]: '',
    [AVIARY_SQUARE]: '',
    [TASK_TYPE]: TaskType.AviaryCreation
}