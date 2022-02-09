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
import { AssigneeDTO, TaskPriorityTO, TaskTO } from '../models';
export interface ChangeStatusRequest {
    taskId: number;
    taskState: ChangeStatusTaskStateEnum;
}
export interface CreateTaskRequest {
    taskType: CreateTaskTaskTypeEnum;
    body?: TaskTO;
}
export interface UpdateTaskRequest {
    taskId: number;
    body?: TaskTO;
}
/**
 *
 */
export declare class TaskApi extends runtime.BaseAPI {
    /**
     * changeStatus
     */
    changeStatusRaw(requestParameters: ChangeStatusRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<TaskTO>>;
    /**
     * changeStatus
     */
    changeStatus(requestParameters: ChangeStatusRequest, initOverrides?: RequestInit): Promise<TaskTO>;
    /**
     * createTask
     */
    createTaskRaw(requestParameters: CreateTaskRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<TaskTO>>;
    /**
     * createTask
     */
    createTask(requestParameters: CreateTaskRequest, initOverrides?: RequestInit): Promise<TaskTO>;
    /**
     * getPossibleAssignees
     */
    getPossibleAssigneesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<AssigneeDTO>>>;
    /**
     * getPossibleAssignees
     */
    getPossibleAssignees(initOverrides?: RequestInit): Promise<Array<AssigneeDTO>>;
    /**
     * get available priorities
     */
    getPrioritiesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<TaskPriorityTO>>>;
    /**
     * get available priorities
     */
    getPriorities(initOverrides?: RequestInit): Promise<Array<TaskPriorityTO>>;
    /**
     * getTaskTypes
     */
    getTaskTypesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<string>>>;
    /**
     * getTaskTypes
     */
    getTaskTypes(initOverrides?: RequestInit): Promise<Array<string>>;
    /**
     * getTasks
     */
    getTasksRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<TaskTO>>>;
    /**
     * getTasks
     */
    getTasks(initOverrides?: RequestInit): Promise<Array<TaskTO>>;
    /**
     * updateTask
     */
    updateTaskRaw(requestParameters: UpdateTaskRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<TaskTO>>;
    /**
     * updateTask
     */
    updateTask(requestParameters: UpdateTaskRequest, initOverrides?: RequestInit): Promise<TaskTO>;
}
/**
    * @export
    * @enum {string}
    */
export declare enum ChangeStatusTaskStateEnum {
    Closed = "CLOSED",
    Resolved = "RESOLVED",
    InProgress = "IN_PROGRESS",
    Open = "OPEN"
}
/**
    * @export
    * @enum {string}
    */
export declare enum CreateTaskTaskTypeEnum {
    Research = "RESEARCH",
    Incubation = "INCUBATION",
    AviaryCreation = "AVIARY_CREATION"
}
