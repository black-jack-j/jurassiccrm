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
 * @interface ResearchTaskDTO
 */
export interface ResearchTaskDTO extends TaskTO {
    /**
     *
     * @type {string}
     * @memberof ResearchTaskDTO
     */
    purpose: string;
}
export declare function ResearchTaskDTOFromJSON(json: any): ResearchTaskDTO;
export declare function ResearchTaskDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ResearchTaskDTO;
export declare function ResearchTaskDTOToJSON(value?: ResearchTaskDTO | null): any;
