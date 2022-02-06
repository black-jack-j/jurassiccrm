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
import { ScheduleInputTO, ScheduleItem } from '../models';
export interface GetAviaryScheduleRequest {
    body?: ScheduleInputTO;
}
export interface GetDinosaurScheduleRequest {
    body?: ScheduleInputTO;
}
export interface GetScheduleForUserRequest {
    body?: ScheduleInputTO;
}
/**
 *
 */
export declare class ScheduleApi extends runtime.BaseAPI {
    /**
     * getAviarySchedule
     */
    getAviaryScheduleRaw(requestParameters: GetAviaryScheduleRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<ScheduleItem>>>;
    /**
     * getAviarySchedule
     */
    getAviarySchedule(requestParameters: GetAviaryScheduleRequest, initOverrides?: RequestInit): Promise<Array<ScheduleItem>>;
    /**
     * getDinosaurSchedule
     */
    getDinosaurScheduleRaw(requestParameters: GetDinosaurScheduleRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<ScheduleItem>>>;
    /**
     * getDinosaurSchedule
     */
    getDinosaurSchedule(requestParameters: GetDinosaurScheduleRequest, initOverrides?: RequestInit): Promise<Array<ScheduleItem>>;
    /**
     * getScheduleForUser
     */
    getScheduleForUserRaw(requestParameters: GetScheduleForUserRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<ScheduleItem>>>;
    /**
     * getScheduleForUser
     */
    getScheduleForUser(requestParameters: GetScheduleForUserRequest, initOverrides?: RequestInit): Promise<Array<ScheduleItem>>;
}
