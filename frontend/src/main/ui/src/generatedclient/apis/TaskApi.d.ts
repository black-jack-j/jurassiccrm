/**
 * Jurassic CRM API
 * Jurassic CRM
 *
 * The version of the OpenAPI document: v0.0.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import * as runtime from '../runtime';
import { AssigneeDTO, TaskTO } from '../models';
export interface ChangeStatusRequest {
    taskId: number;
    taskState: ChangeStatusTaskStateEnum;
}
export interface CreateTaskRequest {
    taskType: CreateTaskTaskTypeEnum;
    taskTO: TaskTO;
}
export interface UpdateTaskRequest {
    taskId: number;
    taskTO: TaskTO;
}
/**
 *
 */
export declare class TaskApi extends runtime.BaseAPI {
    /**
     */
    changeStatusRaw(requestParameters: ChangeStatusRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<TaskTO>>;
    /**
     */
    changeStatus(requestParameters: ChangeStatusRequest, initOverrides?: RequestInit): Promise<TaskTO>;
    /**
     */
    createTaskRaw(requestParameters: CreateTaskRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<TaskTO>>;
    /**
     */
    createTask(requestParameters: CreateTaskRequest, initOverrides?: RequestInit): Promise<TaskTO>;
    /**
     */
    getPossibleAssigneesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<AssigneeDTO>>>;
    /**
     */
    getPossibleAssignees(initOverrides?: RequestInit): Promise<Array<AssigneeDTO>>;
    /**
     */
    getTaskTypesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<string>>>;
    /**
     */
    getTaskTypes(initOverrides?: RequestInit): Promise<Array<string>>;
    /**
     */
    getTasksRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<TaskTO>>>;
    /**
     */
    getTasks(initOverrides?: RequestInit): Promise<Array<TaskTO>>;
    /**
     */
    updateTaskRaw(requestParameters: UpdateTaskRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<TaskTO>>;
    /**
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