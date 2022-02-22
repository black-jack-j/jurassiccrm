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
var CreateUserTODepartmentEnum;
(function (CreateUserTODepartmentEnum) {
    CreateUserTODepartmentEnum["Research"] = "RESEARCH";
    CreateUserTODepartmentEnum["Incubation"] = "INCUBATION";
    CreateUserTODepartmentEnum["Security"] = "SECURITY";
    CreateUserTODepartmentEnum["Administration"] = "ADMINISTRATION";
    CreateUserTODepartmentEnum["Maintenance"] = "MAINTENANCE";
    CreateUserTODepartmentEnum["Accommodation"] = "ACCOMMODATION";
})(CreateUserTODepartmentEnum = exports.CreateUserTODepartmentEnum || (exports.CreateUserTODepartmentEnum = {}));
function CreateUserTOFromJSON(json) {
    return CreateUserTOFromJSONTyped(json, false);
}
exports.CreateUserTOFromJSON = CreateUserTOFromJSON;
function CreateUserTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'username': !runtime_1.exists(json, 'username') ? undefined : json['username'],
        'password': !runtime_1.exists(json, 'password') ? undefined : json['password'],
        'firstName': !runtime_1.exists(json, 'firstName') ? undefined : json['firstName'],
        'lastName': !runtime_1.exists(json, 'lastName') ? undefined : json['lastName'],
        'department': json['department'],
        'groupIds': !runtime_1.exists(json, 'groupIds') ? undefined : json['groupIds'],
    };
}
exports.CreateUserTOFromJSONTyped = CreateUserTOFromJSONTyped;
function CreateUserTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'username': value.username,
        'password': value.password,
        'firstName': value.firstName,
        'lastName': value.lastName,
        'department': value.department,
        'groupIds': value.groupIds,
    };
}
exports.CreateUserTOToJSON = CreateUserTOToJSON;
