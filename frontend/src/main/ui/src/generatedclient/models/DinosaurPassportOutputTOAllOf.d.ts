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
 * @interface DinosaurPassportOutputTOAllOf
 */
export interface DinosaurPassportOutputTOAllOf {
    /**
     *
     * @type {SimpleEntityOutputTO}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    dinosaurType?: SimpleEntityOutputTO;
    /**
     *
     * @type {string}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    dinosaurName?: string;
    /**
     *
     * @type {number}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    weight?: number;
    /**
     *
     * @type {number}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    height?: number;
    /**
     *
     * @type {number}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    incubated?: number;
    /**
     *
     * @type {number}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    revisionPeriod?: number;
    /**
     *
     * @type {string}
     * @memberof DinosaurPassportOutputTOAllOf
     */
    status?: string;
}
export declare function DinosaurPassportOutputTOAllOfFromJSON(json: any): DinosaurPassportOutputTOAllOf;
export declare function DinosaurPassportOutputTOAllOfFromJSONTyped(json: any, ignoreDiscriminator: boolean): DinosaurPassportOutputTOAllOf;
export declare function DinosaurPassportOutputTOAllOfToJSON(value?: DinosaurPassportOutputTOAllOf | null): any;
