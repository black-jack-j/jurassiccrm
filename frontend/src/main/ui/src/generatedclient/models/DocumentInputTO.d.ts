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
 * @interface DocumentInputTO
 */
export interface DocumentInputTO {
    /**
     *
     * @type {string}
     * @memberof DocumentInputTO
     */
    name?: string;
    /**
     *
     * @type {string}
     * @memberof DocumentInputTO
     */
    description?: string;
    /**
     *
     * @type {string}
     * @memberof DocumentInputTO
     */
    documentType: string;
}
export declare function DocumentInputTOFromJSON(json: any): DocumentInputTO;
export declare function DocumentInputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): DocumentInputTO;
export declare function DocumentInputTOToJSON(value?: DocumentInputTO | null): any;