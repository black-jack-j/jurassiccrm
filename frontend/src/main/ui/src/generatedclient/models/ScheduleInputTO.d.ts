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
 * @interface ScheduleInputTO
 */
export interface ScheduleInputTO {
    /**
     *
     * @type {Date}
     * @memberof ScheduleInputTO
     */
    maxDate: Date;
}
export declare function ScheduleInputTOFromJSON(json: any): ScheduleInputTO;
export declare function ScheduleInputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ScheduleInputTO;
export declare function ScheduleInputTOToJSON(value?: ScheduleInputTO | null): any;
