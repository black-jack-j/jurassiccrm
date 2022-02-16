import {
    USER_DEPARTMENT,
    USER_FIRSTNAME,
    USER_GROUPS, USER_ICON,
    USER_LASTNAME,
    USER_PASSWORD,
    USER_PASSWORD_CHECK,
    USER_USERNAME
} from "./fieldNames";

export const UserFormInitialValues = {
    [USER_FIRSTNAME]: '',
    [USER_LASTNAME]: '',
    [USER_DEPARTMENT]: '',
    [USER_USERNAME]: '',
    [USER_PASSWORD]: '',
    [USER_PASSWORD_CHECK]: '',
    [USER_GROUPS]: [],
    [USER_ICON]: ''
}