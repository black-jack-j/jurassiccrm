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
import { UserOutputTO } from './UserOutputTO';
/**
 *
 * @export
 * @interface GroupOutputTO
 */
export interface GroupOutputTO {
    /**
     *
     * @type {number}
     * @memberof GroupOutputTO
     */
    id?: number;
    /**
     *
     * @type {string}
     * @memberof GroupOutputTO
     */
    name?: string;
    /**
     *
     * @type {Set<string>}
     * @memberof GroupOutputTO
     */
    roles?: Set<GroupOutputTORolesEnum>;
    /**
     *
     * @type {Set<UserOutputTO>}
     * @memberof GroupOutputTO
     */
    users?: Set<UserOutputTO>;
}
/**
* @export
* @enum {string}
*/
export declare enum GroupOutputTORolesEnum {
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
export declare function GroupOutputTOFromJSON(json: any): GroupOutputTO;
export declare function GroupOutputTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): GroupOutputTO;
export declare function GroupOutputTOToJSON(value?: GroupOutputTO | null): any;