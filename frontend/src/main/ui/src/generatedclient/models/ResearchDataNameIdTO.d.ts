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
/**
 *
 * @export
 * @interface ResearchDataNameIdTO
 */
export interface ResearchDataNameIdTO {
    /**
     *
     * @type {number}
     * @memberof ResearchDataNameIdTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof ResearchDataNameIdTO
     */
    name?: string;
}
export declare function ResearchDataNameIdTOFromJSON(json: any): ResearchDataNameIdTO;
export declare function ResearchDataNameIdTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ResearchDataNameIdTO;
export declare function ResearchDataNameIdTOToJSON(value?: ResearchDataNameIdTO | null): any;
