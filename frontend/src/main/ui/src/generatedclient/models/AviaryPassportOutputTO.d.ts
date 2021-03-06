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
import { DocumentOutputTO } from './DocumentOutputTO';
import { SimpleEntityOutputTO } from './SimpleEntityOutputTO';
/**
 *
 * @export
 * @interface AviaryPassportOutputTO
 */
export interface AviaryPassportOutputTO extends DocumentOutputTO {
    /**
     *
     * @type {SimpleEntityOutputTO}
     * @memberof AviaryPassportOutputTO
     */
    aviaryType?: SimpleEntityOutputTO;
    /**
     *
     * @type {string}
     * @memberof AviaryPassportOutputTO
     */
    code?: string;
    /**
     *
     * @type {number}
     * @memberof AviaryPassportOutputTO
     */
    builtDate?: number;
    /**
     *
     * @type {number}
     * @memberof AviaryPassportOutputTO
     */
    revisionPeriod?: number;
    /**
     *
     * @type {string}
     * @memberof AviaryPassportOutputTO
     */
    status?: string;
    /**
     *
     * @type {number}
     * @memberof AviaryPassportOutputTO
     */
    square?: number;
}
export declare function AviaryPassportOutputTOFromJSON(json: any): AviaryPassportOutputTO;
export declare function AviaryPassportOutputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): AviaryPassportOutputTO;
export declare function AviaryPassportOutputTOToJSON(value?: AviaryPassportOutputTO | null): any;
