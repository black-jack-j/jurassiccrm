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
import { TaskTO } from './TaskTO';
/**
 *
 * @export
 * @interface IncubationTaskDTO
 */
export interface IncubationTaskDTO extends TaskTO {
    /**
     *
     * @type {number}
     * @memberof IncubationTaskDTO
     */
    dinosaurTypeId?: number;
}
export declare function IncubationTaskDTOFromJSON(json: any): IncubationTaskDTO;
export declare function IncubationTaskDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): IncubationTaskDTO;
export declare function IncubationTaskDTOToJSON(value?: IncubationTaskDTO | null): any;
