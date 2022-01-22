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
import { ScheduleInputTO, ScheduleItem } from '../models';
export interface GetAviaryScheduleRequest {
    scheduleInputTO: ScheduleInputTO;
}
export interface GetDinosaurScheduleRequest {
    scheduleInputTO: ScheduleInputTO;
}
export interface GetScheduleForUserRequest {
    scheduleInputTO: ScheduleInputTO;
}
/**
 *
 */
export declare class ScheduleApi extends runtime.BaseAPI {
    /**
     */
    getAviaryScheduleRaw(requestParameters: GetAviaryScheduleRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<ScheduleItem>>>;
    /**
     */
    getAviarySchedule(requestParameters: GetAviaryScheduleRequest, initOverrides?: RequestInit): Promise<Array<ScheduleItem>>;
    /**
     */
    getDinosaurScheduleRaw(requestParameters: GetDinosaurScheduleRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<ScheduleItem>>>;
    /**
     */
    getDinosaurSchedule(requestParameters: GetDinosaurScheduleRequest, initOverrides?: RequestInit): Promise<Array<ScheduleItem>>;
    /**
     */
    getScheduleForUserRaw(requestParameters: GetScheduleForUserRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<ScheduleItem>>>;
    /**
     */
    getScheduleForUser(requestParameters: GetScheduleForUserRequest, initOverrides?: RequestInit): Promise<Array<ScheduleItem>>;
}
