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
function SimpleEntityOutputTOFromJSON(json) {
    return SimpleEntityOutputTOFromJSONTyped(json, false);
}
exports.SimpleEntityOutputTOFromJSON = SimpleEntityOutputTOFromJSON;
function SimpleEntityOutputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'id': !runtime_1.exists(json, 'id') ? undefined : json['id'],
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
    };
}
exports.SimpleEntityOutputTOFromJSONTyped = SimpleEntityOutputTOFromJSONTyped;
function SimpleEntityOutputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'id': value.id,
        'name': value.name,
    };
}
exports.SimpleEntityOutputTOToJSON = SimpleEntityOutputTOToJSON;
