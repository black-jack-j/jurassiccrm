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
import { SimpleEntityOutputTO } from './SimpleEntityOutputTO';
/**
 *
 * @export
 * @interface AviaryPassportOutputTOAllOf
 */
export interface AviaryPassportOutputTOAllOf {
    /**
     *
     * @type {SimpleEntityOutputTO}
     * @memberof AviaryPassportOutputTOAllOf
     */
    aviaryType?: SimpleEntityOutputTO;
    /**
     *
     * @type {string}
     * @memberof AviaryPassportOutputTOAllOf
     */
    code?: string;
    /**
     *
     * @type {number}
     * @memberof AviaryPassportOutputTOAllOf
     */
    builtDate?: number;
    /**
     *
     * @type {number}
     * @memberof AviaryPassportOutputTOAllOf
     */
    revisionPeriod?: number;
    /**
     *
     * @type {string}
     * @memberof AviaryPassportOutputTOAllOf
     */
    status?: string;
}
export declare function AviaryPassportOutputTOAllOfFromJSON(json: any): AviaryPassportOutputTOAllOf;
export declare function AviaryPassportOutputTOAllOfFromJSONTyped(json: any, ignoreDiscriminator: boolean): AviaryPassportOutputTOAllOf;
export declare function AviaryPassportOutputTOAllOfToJSON(value?: AviaryPassportOutputTOAllOf | null): any;
