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
var UserOutputTO_1 = require("./UserOutputTO");
/**
* @export
* @enum {string}
*/
var GroupOutputTORolesEnum;
(function (GroupOutputTORolesEnum) {
    GroupOutputTORolesEnum["DocumentReader"] = "DOCUMENT_READER";
    GroupOutputTORolesEnum["DinosaurPassportReader"] = "DINOSAUR_PASSPORT_READER";
    GroupOutputTORolesEnum["AviaryPassportReader"] = "AVIARY_PASSPORT_READER";
    GroupOutputTORolesEnum["ThemeZoneProjectReader"] = "THEME_ZONE_PROJECT_READER";
    GroupOutputTORolesEnum["TechnologicalMapReader"] = "TECHNOLOGICAL_MAP_READER";
    GroupOutputTORolesEnum["ResearchDataReader"] = "RESEARCH_DATA_READER";
    GroupOutputTORolesEnum["DocumentWriter"] = "DOCUMENT_WRITER";
    GroupOutputTORolesEnum["DinosaurPassportWriter"] = "DINOSAUR_PASSPORT_WRITER";
    GroupOutputTORolesEnum["AviaryPassportWriter"] = "AVIARY_PASSPORT_WRITER";
    GroupOutputTORolesEnum["ThemeZoneProjectWriter"] = "THEME_ZONE_PROJECT_WRITER";
    GroupOutputTORolesEnum["TechnologicalMapWriter"] = "TECHNOLOGICAL_MAP_WRITER";
    GroupOutputTORolesEnum["ResearchDataWriter"] = "RESEARCH_DATA_WRITER";
    GroupOutputTORolesEnum["TaskReader"] = "TASK_READER";
    GroupOutputTORolesEnum["IncubationTaskReader"] = "INCUBATION_TASK_READER";
    GroupOutputTORolesEnum["AviaryBuildingTaskReader"] = "AVIARY_BUILDING_TASK_READER";
    GroupOutputTORolesEnum["ResearchTaskReader"] = "RESEARCH_TASK_READER";
    GroupOutputTORolesEnum["TaskWriter"] = "TASK_WRITER";
    GroupOutputTORolesEnum["IncubationTaskWriter"] = "INCUBATION_TASK_WRITER";
    GroupOutputTORolesEnum["AviaryBuildingTaskWriter"] = "AVIARY_BUILDING_TASK_WRITER";
    GroupOutputTORolesEnum["ResearchTaskWriter"] = "RESEARCH_TASK_WRITER";
    GroupOutputTORolesEnum["SecurityReader"] = "SECURITY_READER";
    GroupOutputTORolesEnum["SecurityWriter"] = "SECURITY_WRITER";
    GroupOutputTORolesEnum["Admin"] = "ADMIN";
})(GroupOutputTORolesEnum = exports.GroupOutputTORolesEnum || (exports.GroupOutputTORolesEnum = {}));
function GroupOutputTOFromJSON(json) {
    return GroupOutputTOFromJSONTyped(json, false);
}
exports.GroupOutputTOFromJSON = GroupOutputTOFromJSON;
function GroupOutputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'id': !runtime_1.exists(json, 'id') ? undefined : json['id'],
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
        'roles': !runtime_1.exists(json, 'roles') ? undefined : json['roles'],
        'users': !runtime_1.exists(json, 'users') ? undefined : (new Set(json['users'].map(UserOutputTO_1.UserOutputTOFromJSON))),
    };
}
exports.GroupOutputTOFromJSONTyped = GroupOutputTOFromJSONTyped;
function GroupOutputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'id': value.id,
        'name': value.name,
        'roles': value.roles,
        'users': value.users === undefined ? undefined : (Array.from(value.users).map(UserOutputTO_1.UserOutputTOToJSON)),
    };
}
exports.GroupOutputTOToJSON = GroupOutputTOToJSON;
