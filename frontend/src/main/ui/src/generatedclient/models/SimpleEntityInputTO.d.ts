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
 * @interface SimpleEntityInputTO
 */
export interface SimpleEntityInputTO {
    /**
     *
     * @type {string}
     * @memberof SimpleEntityInputTO
     */
    name?: string;
}
export declare function SimpleEntityInputTOFromJSON(json: any): SimpleEntityInputTO;
export declare function SimpleEntityInputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): SimpleEntityInputTO;
export declare function SimpleEntityInputTOToJSON(value?: SimpleEntityInputTO | null): any;
