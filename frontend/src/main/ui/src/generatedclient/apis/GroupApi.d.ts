/**
 * JurassicCRM
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: fitisovdmtr@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import * as runtime from '../runtime';
import { GroupInputTO, GroupOutputTO, UserIdInputTO, UserOutputTO } from '../models';
export interface AddUserRequest {
    groupId: number;
    body?: UserIdInputTO;
}
export interface CreateGroupRequest {
    body?: GroupInputTO;
}
export interface RemoveUserRequest {
    groupId: number;
    userId: number;
}
export interface UpdateGroupRequest {
    groupId: number;
    body?: GroupInputTO;
}
/**
 *
 */
export declare class GroupApi extends runtime.BaseAPI {
    /**
     * addUser
     */
    addUserRaw(requestParameters: AddUserRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<string>>;
    /**
     * addUser
     */
    addUser(requestParameters: AddUserRequest, initOverrides?: RequestInit): Promise<string>;
    /**
     * createGroup
     */
    createGroupRaw(requestParameters: CreateGroupRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<GroupOutputTO>>;
    /**
     * createGroup
     */
    createGroup(requestParameters: CreateGroupRequest, initOverrides?: RequestInit): Promise<GroupOutputTO>;
    /**
     * getGroup
     */
    getGroupRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<GroupOutputTO>>>;
    /**
     * getGroup
     */
    getGroup(initOverrides?: RequestInit): Promise<Array<GroupOutputTO>>;
    /**
     * getRoles
     */
    getRolesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<string>>>;
    /**
     * getRoles
     */
    getRoles(initOverrides?: RequestInit): Promise<Array<string>>;
    /**
     * getUsers
     */
    getUsersRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<UserOutputTO>>>;
    /**
     * getUsers
     */
    getUsers(initOverrides?: RequestInit): Promise<Array<UserOutputTO>>;
    /**
     * removeUser
     */
    removeUserRaw(requestParameters: RemoveUserRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<string>>;
    /**
     * removeUser
     */
    removeUser(requestParameters: RemoveUserRequest, initOverrides?: RequestInit): Promise<string>;
    /**
     * updateGroup
     */
    updateGroupRaw(requestParameters: UpdateGroupRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<GroupOutputTO>>;
    /**
     * updateGroup
     */
    updateGroup(requestParameters: UpdateGroupRequest, initOverrides?: RequestInit): Promise<GroupOutputTO>;
}
