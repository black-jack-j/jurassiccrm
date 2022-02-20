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
var SimpleEntityOutputTO_1 = require("./SimpleEntityOutputTO");
function TechnologicalMapOutputTOAllOfFromJSON(json) {
    return TechnologicalMapOutputTOAllOfFromJSONTyped(json, false);
}
exports.TechnologicalMapOutputTOAllOfFromJSON = TechnologicalMapOutputTOAllOfFromJSON;
function TechnologicalMapOutputTOAllOfFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'dinosaurType': !runtime_1.exists(json, 'dinosaurType') ? undefined : SimpleEntityOutputTO_1.SimpleEntityOutputTOFromJSON(json['dinosaurType']),
        'incubationSteps': !runtime_1.exists(json, 'incubationSteps') ? undefined : json['incubationSteps'],
        'eggCreationSteps': !runtime_1.exists(json, 'eggCreationSteps') ? undefined : json['eggCreationSteps'],
    };
}
exports.TechnologicalMapOutputTOAllOfFromJSONTyped = TechnologicalMapOutputTOAllOfFromJSONTyped;
function TechnologicalMapOutputTOAllOfToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'dinosaurType': SimpleEntityOutputTO_1.SimpleEntityOutputTOToJSON(value.dinosaurType),
        'incubationSteps': value.incubationSteps,
        'eggCreationSteps': value.eggCreationSteps,
    };
}
exports.TechnologicalMapOutputTOAllOfToJSON = TechnologicalMapOutputTOAllOfToJSON;