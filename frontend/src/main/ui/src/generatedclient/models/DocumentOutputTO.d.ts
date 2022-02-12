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
import { UserOutputTO } from './UserOutputTO';
/**
 *
 * @export
 * @interface DocumentOutputTO
 */
export interface DocumentOutputTO {
    /**
     *
     * @type {number}
     * @memberof DocumentOutputTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof DocumentOutputTO
     */
    name?: string;
    /**
     *
     * @type {string}
     * @memberof DocumentOutputTO
     */
    type?: DocumentOutputTOTypeEnum;
    /**
     *
     * @type {UserOutputTO}
     * @memberof DocumentOutputTO
     */
    author?: UserOutputTO;
    /**
     *
     * @type {UserOutputTO}
     * @memberof DocumentOutputTO
     */
    lastUpdater?: UserOutputTO;
    /**
     *
     * @type {number}
     * @memberof DocumentOutputTO
     */
    created?: number;
    /**
     *
     * @type {number}
     * @memberof DocumentOutputTO
     */
    lastUpdate?: number;
    /**
     *
     * @type {string}
     * @memberof DocumentOutputTO
     */
    description?: string;
}
/**
* @export
* @enum {string}
*/
export declare enum DocumentOutputTOTypeEnum {
    ThemeZoneProject = "THEME_ZONE_PROJECT",
    DinosaurPassport = "DINOSAUR_PASSPORT",
    TechnologicalMap = "TECHNOLOGICAL_MAP",
    AviaryPassport = "AVIARY_PASSPORT",
    ResearchData = "RESEARCH_DATA"
}
export declare function DocumentOutputTOFromJSON(json: any): DocumentOutputTO;
export declare function DocumentOutputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): DocumentOutputTO;
export declare function DocumentOutputTOToJSON(value?: DocumentOutputTO | null): any;
