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
 * @interface UserOutputTO
 */
export interface UserOutputTO {
    /**
     *
     * @type {number}
     * @memberof UserOutputTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof UserOutputTO
     */
    username?: string;
    /**
     *
     * @type {string}
     * @memberof UserOutputTO
     */
    firstName?: string;
    /**
     *
     * @type {string}
     * @memberof UserOutputTO
     */
    lastName?: string;
    /**
     *
     * @type {string}
     * @memberof UserOutputTO
     */
    department?: UserOutputTODepartmentEnum;
}
/**
* @export
* @enum {string}
*/
export declare enum UserOutputTODepartmentEnum {
    Research = "RESEARCH",
    Incubation = "INCUBATION",
    Security = "SECURITY",
    Administration = "ADMINISTRATION",
    Maintenance = "MAINTENANCE",
    Accommodation = "ACCOMMODATION"
}
export declare function UserOutputTOFromJSON(json: any): UserOutputTO;
export declare function UserOutputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): UserOutputTO;
export declare function UserOutputTOToJSON(value?: UserOutputTO | null): any;
