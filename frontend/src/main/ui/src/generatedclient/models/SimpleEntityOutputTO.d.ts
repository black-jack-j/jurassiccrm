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
 * @interface SimpleEntityOutputTO
 */
export interface SimpleEntityOutputTO {
    /**
     *
     * @type {number}
     * @memberof SimpleEntityOutputTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof SimpleEntityOutputTO
     */
    name?: string;
}
export declare function SimpleEntityOutputTOFromJSON(json: any): SimpleEntityOutputTO;
export declare function SimpleEntityOutputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): SimpleEntityOutputTO;
export declare function SimpleEntityOutputTOToJSON(value?: SimpleEntityOutputTO | null): any;
