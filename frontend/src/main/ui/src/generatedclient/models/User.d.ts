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
import { Group } from './Group';
import { Task } from './Task';
/**
 *
 * @export
 * @interface User
 */
export interface User {
    /**
     *
     * @type {number}
     * @memberof User
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof User
     */
    username?: string;
    /**
     *
     * @type {string}
     * @memberof User
     */
    password?: string;
    /**
     *
     * @type {string}
     * @memberof User
     */
    firstName?: string;
    /**
     *
     * @type {string}
     * @memberof User
     */
    lastName?: string;
    /**
     *
     * @type {string}
     * @memberof User
     */
    department?: UserDepartmentEnum;
    /**
     *
     * @type {Set<Task>}
     * @memberof User
     */
    tasks?: Set<Task>;
    /**
     *
     * @type {Set<Group>}
     * @memberof User
     */
    groups?: Set<Group>;
    /**
     *
     * @type {Set<string>}
     * @memberof User
     */
    roles?: Set<UserRolesEnum>;
}
/**
* @export
* @enum {string}
*/
export declare enum UserDepartmentEnum {
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
export declare enum UserRolesEnum {
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
export declare function UserFromJSON(json: any): User;
export declare function UserFromJSONTyped(json: any, ignoreDiscriminator: boolean): User;
export declare function UserToJSON(value?: User | null): any;