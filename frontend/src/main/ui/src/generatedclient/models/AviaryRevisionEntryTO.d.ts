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
 * @interface AviaryRevisionEntryTO
 */
export interface AviaryRevisionEntryTO {
    /**
     *
     * @type {SimpleEntityOutputTO}
     * @memberof AviaryRevisionEntryTO
     */
    aviary?: SimpleEntityOutputTO;
    /**
     *
     * @type {number}
     * @memberof AviaryRevisionEntryTO
     */
    revisionDate?: number;
}
export declare function AviaryRevisionEntryTOFromJSON(json: any): AviaryRevisionEntryTO;
export declare function AviaryRevisionEntryTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): AviaryRevisionEntryTO;
export declare function AviaryRevisionEntryTOToJSON(value?: AviaryRevisionEntryTO | null): any;
