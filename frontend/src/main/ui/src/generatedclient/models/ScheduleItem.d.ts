/**
 * Jurassic CRM API
 * Jurassic CRM
 *
 * The version of the OpenAPI document: v0.0.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
/**
 *
 * @export
 * @interface ScheduleItem
 */
export interface ScheduleItem {
    /**
     *
     * @type {Date}
     * @memberof ScheduleItem
     */
    date?: Date;
    /**
     *
     * @type {string}
     * @memberof ScheduleItem
     */
    name?: string;
}
export declare function ScheduleItemFromJSON(json: any): ScheduleItem;
export declare function ScheduleItemFromJSONTyped(json: any, ignoreDiscriminator: boolean): ScheduleItem;
export declare function ScheduleItemToJSON(value?: ScheduleItem | null): any;