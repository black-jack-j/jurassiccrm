import {USER_DEPARTMENT, USER_FIRSTNAME, USER_GROUPS, USER_LASTNAME, USER_USERNAME} from "./fieldNames";

const groupToOption = ({id, name}) => ({id, value: id, text: name})

export default TO => ({
    [USER_USERNAME]: TO.username,
    [USER_FIRSTNAME]: TO.firstName,
    [USER_LASTNAME]: TO.lastName,
    [USER_DEPARTMENT]: {value: TO.department, text: TO.department},
    [USER_GROUPS]: TO.groupIds.map(groupToOption)
})