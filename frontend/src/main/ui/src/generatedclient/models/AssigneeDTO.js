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
function AssigneeDTOFromJSON(json) {
    return AssigneeDTOFromJSONTyped(json, false);
}
exports.AssigneeDTOFromJSON = AssigneeDTOFromJSON;
function AssigneeDTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'id': !runtime_1.exists(json, 'id') ? undefined : json['id'],
        'username': !runtime_1.exists(json, 'username') ? undefined : json['username'],
    };
}
exports.AssigneeDTOFromJSONTyped = AssigneeDTOFromJSONTyped;
function AssigneeDTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'id': value.id,
        'username': value.username,
    };
}
exports.AssigneeDTOToJSON = AssigneeDTOToJSON;
