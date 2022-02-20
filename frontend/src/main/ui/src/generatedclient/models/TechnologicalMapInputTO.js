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
var __assign = (this && this.__assign) || Object.assign || function(t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
        s = arguments[i];
        for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
            t[p] = s[p];
    }
    return t;
};
Object.defineProperty(exports, "__esModule", { value: true });
var runtime_1 = require("../runtime");
var DocumentInputTO_1 = require("./DocumentInputTO");
function TechnologicalMapInputTOFromJSON(json) {
    return TechnologicalMapInputTOFromJSONTyped(json, false);
}
exports.TechnologicalMapInputTOFromJSON = TechnologicalMapInputTOFromJSON;
function TechnologicalMapInputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return __assign({}, DocumentInputTO_1.DocumentInputTOFromJSONTyped(json, ignoreDiscriminator), { 'dinosaurTypeId': json['dinosaurTypeId'], 'incubationSteps': !runtime_1.exists(json, 'incubationSteps') ? undefined : json['incubationSteps'], 'eggCreationSteps': !runtime_1.exists(json, 'eggCreationSteps') ? undefined : json['eggCreationSteps'] });
}
exports.TechnologicalMapInputTOFromJSONTyped = TechnologicalMapInputTOFromJSONTyped;
function TechnologicalMapInputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return __assign({}, DocumentInputTO_1.DocumentInputTOToJSON(value), { 'dinosaurTypeId': value.dinosaurTypeId, 'incubationSteps': value.incubationSteps, 'eggCreationSteps': value.eggCreationSteps });
}
exports.TechnologicalMapInputTOToJSON = TechnologicalMapInputTOToJSON;