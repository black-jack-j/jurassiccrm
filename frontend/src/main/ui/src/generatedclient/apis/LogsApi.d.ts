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
import { LogEntry } from '../models';
/**
 *
 */
export declare class LogsApi extends runtime.BaseAPI {
    /**
     * getLogs
     */
    getLogsRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<LogEntry>>>;
    /**
     * getLogs
     */
    getLogs(initOverrides?: RequestInit): Promise<Array<LogEntry>>;
}
