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
var ViolationTO_1 = require("./ViolationTO");
function ValidationResponseTOFromJSON(json) {
    return ValidationResponseTOFromJSONTyped(json, false);
}
exports.ValidationResponseTOFromJSON = ValidationResponseTOFromJSON;
function ValidationResponseTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'violations': !runtime_1.exists(json, 'violations') ? undefined : (json['violations'].map(ViolationTO_1.ViolationTOFromJSON)),
    };
}
exports.ValidationResponseTOFromJSONTyped = ValidationResponseTOFromJSONTyped;
function ValidationResponseTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'violations': value.violations === undefined ? undefined : (value.violations.map(ViolationTO_1.ViolationTOToJSON)),
    };
}
exports.ValidationResponseTOToJSON = ValidationResponseTOToJSON;
