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
import { ThemeZoneElementOutputTO } from './ThemeZoneElementOutputTO';
import { UserOutputTO } from './UserOutputTO';
/**
 *
 * @export
 * @interface ThemeZoneProjectOutputTOAllOf
 */
export interface ThemeZoneProjectOutputTOAllOf {
    /**
     *
     * @type {string}
     * @memberof ThemeZoneProjectOutputTOAllOf
     */
    projectName?: string;
    /**
     *
     * @type {UserOutputTO}
     * @memberof ThemeZoneProjectOutputTOAllOf
     */
    manager?: UserOutputTO;
    /**
     *
     * @type {Array<ThemeZoneElementOutputTO>}
     * @memberof ThemeZoneProjectOutputTOAllOf
     */
    dinosaurs?: Array<ThemeZoneElementOutputTO>;
    /**
     *
     * @type {Array<ThemeZoneElementOutputTO>}
     * @memberof ThemeZoneProjectOutputTOAllOf
     */
    aviaries?: Array<ThemeZoneElementOutputTO>;
    /**
     *
     * @type {Array<ThemeZoneElementOutputTO>}
     * @memberof ThemeZoneProjectOutputTOAllOf
     */
    decorations?: Array<ThemeZoneElementOutputTO>;
}
export declare function ThemeZoneProjectOutputTOAllOfFromJSON(json: any): ThemeZoneProjectOutputTOAllOf;
export declare function ThemeZoneProjectOutputTOAllOfFromJSONTyped(json: any, ignoreDiscriminator: boolean): ThemeZoneProjectOutputTOAllOf;
export declare function ThemeZoneProjectOutputTOAllOfToJSON(value?: ThemeZoneProjectOutputTOAllOf | null): any;
