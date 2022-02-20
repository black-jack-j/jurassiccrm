import {RESEARCH_GOAL, TASK_ASSIGNEE_ID, TASK_DESCRIPTION, TASK_NAME, TASK_PRIORITY_ID, TASK_TYPE} from "./fieldNames";
import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";

export default {
    [TASK_NAME]: '',
    [TASK_DESCRIPTION]: '',
    [TASK_PRIORITY_ID]: '',
    [TASK_ASSIGNEE_ID]: '',
    [RESEARCH_GOAL]: '',
    [TASK_TYPE]: TaskType.Research
}