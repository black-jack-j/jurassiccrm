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
function RevisionEntryTOFromJSON(json) {
    return RevisionEntryTOFromJSONTyped(json, false);
}
exports.RevisionEntryTOFromJSON = RevisionEntryTOFromJSON;
function RevisionEntryTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'entity': !runtime_1.exists(json, 'entity') ? undefined : SimpleEntityOutputTO_1.SimpleEntityOutputTOFromJSON(json['entity']),
        'revisionDate': !runtime_1.exists(json, 'revisionDate') ? undefined : json['revisionDate'],
    };
}
exports.RevisionEntryTOFromJSONTyped = RevisionEntryTOFromJSONTyped;
function RevisionEntryTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'entity': SimpleEntityOutputTO_1.SimpleEntityOutputTOToJSON(value.entity),
        'revisionDate': value.revisionDate,
    };
}
exports.RevisionEntryTOToJSON = RevisionEntryTOToJSON;
