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
 * @interface SimpleGroupTO
 */
export interface SimpleGroupTO {
    /**
     *
     * @type {number}
     * @memberof SimpleGroupTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof SimpleGroupTO
     */
    name?: string;
    /**
     *
     * @type {string}
     * @memberof SimpleGroupTO
     */
    description?: string;
}
export declare function SimpleGroupTOFromJSON(json: any): SimpleGroupTO;
export declare function SimpleGroupTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): SimpleGroupTO;
export declare function SimpleGroupTOToJSON(value?: SimpleGroupTO | null): any;
