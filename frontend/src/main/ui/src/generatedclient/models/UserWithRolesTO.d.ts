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
import { SimpleGroupTO } from './SimpleGroupTO';
/**
 *
 * @export
 * @interface UserWithRolesTO
 */
export interface UserWithRolesTO {
    /**
     *
     * @type {number}
     * @memberof UserWithRolesTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof UserWithRolesTO
     */
    avatarSrc?: string;
    /**
     *
     * @type {string}
     * @memberof UserWithRolesTO
     */
    username?: string;
    /**
     *
     * @type {string}
     * @memberof UserWithRolesTO
     */
    firstName?: string;
    /**
     *
     * @type {string}
     * @memberof UserWithRolesTO
     */
    lastName?: string;
    /**
     *
     * @type {string}
     * @memberof UserWithRolesTO
     */
    department?: UserWithRolesTODepartmentEnum;
    /**
     *
     * @type {Array<string>}
     * @memberof UserWithRolesTO
     */
    roles?: Array<UserWithRolesTORolesEnum>;
    /**
     *
     * @type {Array<SimpleGroupTO>}
     * @memberof UserWithRolesTO
     */
    groups?: Array<SimpleGroupTO>;
}
/**
* @export
* @enum {string}
*/
export declare enum UserWithRolesTODepartmentEnum {
    Research = "RESEARCH",
    Incubation = "INCUBATION",
    Security = "SECURITY",
    Administration = "ADMINISTRATION",
    Maintenance = "MAINTENANCE",
    Accommodation = "ACCOMMODATION"
} /**
* @export
* @enum {string}
*/
export declare enum UserWithRolesTORolesEnum {
    DocumentReader = "DOCUMENT_READER",
    DinosaurPassportReader = "DINOSAUR_PASSPORT_READER",
    AviaryPassportReader = "AVIARY_PASSPORT_READER",
    ThemeZoneProjectReader = "THEME_ZONE_PROJECT_READER",
    TechnologicalMapReader = "TECHNOLOGICAL_MAP_READER",
    ResearchDataReader = "RESEARCH_DATA_READER",
    DocumentWriter = "DOCUMENT_WRITER",
    DinosaurPassportWriter = "DINOSAUR_PASSPORT_WRITER",
    AviaryPassportWriter = "AVIARY_PASSPORT_WRITER",
    ThemeZoneProjectWriter = "THEME_ZONE_PROJECT_WRITER",
    TechnologicalMapWriter = "TECHNOLOGICAL_MAP_WRITER",
    ResearchDataWriter = "RESEARCH_DATA_WRITER",
    TaskReader = "TASK_READER",
    IncubationTaskReader = "INCUBATION_TASK_READER",
    AviaryBuildingTaskReader = "AVIARY_BUILDING_TASK_READER",
    ResearchTaskReader = "RESEARCH_TASK_READER",
    TaskWriter = "TASK_WRITER",
    IncubationTaskWriter = "INCUBATION_TASK_WRITER",
    AviaryBuildingTaskWriter = "AVIARY_BUILDING_TASK_WRITER",
    ResearchTaskWriter = "RESEARCH_TASK_WRITER",
    SecurityReader = "SECURITY_READER",
    SecurityWriter = "SECURITY_WRITER",
    Admin = "ADMIN"
}
export declare function UserWithRolesTOFromJSON(json: any): UserWithRolesTO;
export declare function UserWithRolesTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): UserWithRolesTO;
export declare function UserWithRolesTOToJSON(value?: UserWithRolesTO | null): any;