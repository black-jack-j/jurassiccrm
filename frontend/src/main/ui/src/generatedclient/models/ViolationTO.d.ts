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
 * @interface ViolationTO
 */
export interface ViolationTO {
    /**
     *
     * @type {string}
     * @memberof ViolationTO
     */
    fieldName?: string;
    /**
     *
     * @type {string}
     * @memberof ViolationTO
     */
    message?: string;
}
export declare function ViolationTOFromJSON(json: any): ViolationTO;
export declare function ViolationTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ViolationTO;
export declare function ViolationTOToJSON(value?: ViolationTO | null): any;
