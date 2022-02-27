import {AVIARY_TYPE_ID, TASK_PRIORITY_ID} from "./fieldNames";

export default TO => ({
    ...TO,
    [AVIARY_TYPE_ID]: {
        value: TO.aviaryType ? TO.aviaryType.id : '',
        text: TO.aviaryType ? TO.aviaryType.name : ''
    },
    [TASK_PRIORITY_ID]: {
        value: TO.priority.id,
        text: TO.priority.name
    }
})