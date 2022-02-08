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
import { ResearchDataNameIdTO } from './ResearchDataNameIdTO';
/**
 *
 * @export
 * @interface ResearchDataInputTO
 */
export interface ResearchDataInputTO {
    /**
     *
     * @type {string}
     * @memberof ResearchDataInputTO
     */
    name?: string;
    /**
     *
     * @type {string}
     * @memberof ResearchDataInputTO
     */
    description?: string;
    /**
     *
     * @type {ResearchDataNameIdTO}
     * @memberof ResearchDataInputTO
     */
    researchNameId: ResearchDataNameIdTO;
    /**
     *
     * @type {boolean}
     * @memberof ResearchDataInputTO
     */
    newResearch?: boolean;
}
export declare function ResearchDataInputTOFromJSON(json: any): ResearchDataInputTO;
export declare function ResearchDataInputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ResearchDataInputTO;
export declare function ResearchDataInputTOToJSON(value?: ResearchDataInputTO | null): any;
