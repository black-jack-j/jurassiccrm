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
 * @interface TechnologicalMapInputTOAllOf
 */
export interface TechnologicalMapInputTOAllOf {
    /**
     *
     * @type {number}
     * @memberof TechnologicalMapInputTOAllOf
     */
    dinosaurTypeId: number;
    /**
     *
     * @type {Array<string>}
     * @memberof TechnologicalMapInputTOAllOf
     */
    incubationSteps?: Array<string>;
    /**
     *
     * @type {Array<string>}
     * @memberof TechnologicalMapInputTOAllOf
     */
    eggCreationSteps?: Array<string>;
}
export declare function TechnologicalMapInputTOAllOfFromJSON(json: any): TechnologicalMapInputTOAllOf;
export declare function TechnologicalMapInputTOAllOfFromJSONTyped(json: any, ignoreDiscriminator: boolean): TechnologicalMapInputTOAllOf;
export declare function TechnologicalMapInputTOAllOfToJSON(value?: TechnologicalMapInputTOAllOf | null): any;
