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
function AviaryRevisionEntryTOFromJSON(json) {
    return AviaryRevisionEntryTOFromJSONTyped(json, false);
}
exports.AviaryRevisionEntryTOFromJSON = AviaryRevisionEntryTOFromJSON;
function AviaryRevisionEntryTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'aviary': !runtime_1.exists(json, 'aviary') ? undefined : SimpleEntityOutputTO_1.SimpleEntityOutputTOFromJSON(json['aviary']),
        'revisionDate': !runtime_1.exists(json, 'revisionDate') ? undefined : json['revisionDate'],
    };
}
exports.AviaryRevisionEntryTOFromJSONTyped = AviaryRevisionEntryTOFromJSONTyped;
function AviaryRevisionEntryTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'aviary': SimpleEntityOutputTO_1.SimpleEntityOutputTOToJSON(value.aviary),
        'revisionDate': value.revisionDate,
    };
}
exports.AviaryRevisionEntryTOToJSON = AviaryRevisionEntryTOToJSON;
