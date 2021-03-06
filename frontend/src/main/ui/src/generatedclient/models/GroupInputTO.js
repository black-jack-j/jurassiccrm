"use strict";
/* tslint:disable */
/* eslint-disable */
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
Object.defineProperty(exports, "__esModule", { value: true });
var runtime_1 = require("../runtime");
/**
* @export
* @enum {string}
*/
var GroupInputTORolesEnum;
(function (GroupInputTORolesEnum) {
    GroupInputTORolesEnum["DocumentReader"] = "DOCUMENT_READER";
    GroupInputTORolesEnum["DinosaurPassportReader"] = "DINOSAUR_PASSPORT_READER";
    GroupInputTORolesEnum["AviaryPassportReader"] = "AVIARY_PASSPORT_READER";
    GroupInputTORolesEnum["ThemeZoneProjectReader"] = "THEME_ZONE_PROJECT_READER";
    GroupInputTORolesEnum["TechnologicalMapReader"] = "TECHNOLOGICAL_MAP_READER";
    GroupInputTORolesEnum["ResearchDataReader"] = "RESEARCH_DATA_READER";
    GroupInputTORolesEnum["DocumentWriter"] = "DOCUMENT_WRITER";
    GroupInputTORolesEnum["DinosaurPassportWriter"] = "DINOSAUR_PASSPORT_WRITER";
    GroupInputTORolesEnum["AviaryPassportWriter"] = "AVIARY_PASSPORT_WRITER";
    GroupInputTORolesEnum["ThemeZoneProjectWriter"] = "THEME_ZONE_PROJECT_WRITER";
    GroupInputTORolesEnum["TechnologicalMapWriter"] = "TECHNOLOGICAL_MAP_WRITER";
    GroupInputTORolesEnum["ResearchDataWriter"] = "RESEARCH_DATA_WRITER";
    GroupInputTORolesEnum["TaskReader"] = "TASK_READER";
    GroupInputTORolesEnum["IncubationTaskReader"] = "INCUBATION_TASK_READER";
    GroupInputTORolesEnum["AviaryBuildingTaskReader"] = "AVIARY_BUILDING_TASK_READER";
    GroupInputTORolesEnum["ResearchTaskReader"] = "RESEARCH_TASK_READER";
    GroupInputTORolesEnum["TaskWriter"] = "TASK_WRITER";
    GroupInputTORolesEnum["IncubationTaskWriter"] = "INCUBATION_TASK_WRITER";
    GroupInputTORolesEnum["AviaryBuildingTaskWriter"] = "AVIARY_BUILDING_TASK_WRITER";
    GroupInputTORolesEnum["ResearchTaskWriter"] = "RESEARCH_TASK_WRITER";
    GroupInputTORolesEnum["SecurityReader"] = "SECURITY_READER";
    GroupInputTORolesEnum["SecurityWriter"] = "SECURITY_WRITER";
    GroupInputTORolesEnum["Admin"] = "ADMIN";
})(GroupInputTORolesEnum = exports.GroupInputTORolesEnum || (exports.GroupInputTORolesEnum = {}));
function GroupInputTOFromJSON(json) {
    return GroupInputTOFromJSONTyped(json, false);
}
exports.GroupInputTOFromJSON = GroupInputTOFromJSON;
function GroupInputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
        'roles': !runtime_1.exists(json, 'roles') ? undefined : json['roles'],
        'userIds': !runtime_1.exists(json, 'userIds') ? undefined : json['userIds'],
        'description': !runtime_1.exists(json, 'description') ? undefined : json['description'],
    };
}
exports.GroupInputTOFromJSONTyped = GroupInputTOFromJSONTyped;
function GroupInputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'name': value.name,
        'roles': value.roles,
        'userIds': value.userIds,
        'description': value.description,
    };
}
exports.GroupInputTOToJSON = GroupInputTOToJSON;
