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
var ResearchDataNameIdTO_1 = require("./ResearchDataNameIdTO");
function ResearchDataInputTOFromJSON(json) {
    return ResearchDataInputTOFromJSONTyped(json, false);
}
exports.ResearchDataInputTOFromJSON = ResearchDataInputTOFromJSON;
function ResearchDataInputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
        'description': !runtime_1.exists(json, 'description') ? undefined : json['description'],
        'documentType': json['documentType'],
        'researchNameId': ResearchDataNameIdTO_1.ResearchDataNameIdTOFromJSON(json['researchNameId']),
        'newResearch': !runtime_1.exists(json, 'newResearch') ? undefined : json['newResearch'],
        'attachmentName': !runtime_1.exists(json, 'attachmentName') ? undefined : json['attachmentName'],
    };
}
exports.ResearchDataInputTOFromJSONTyped = ResearchDataInputTOFromJSONTyped;
function ResearchDataInputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'name': value.name,
        'description': value.description,
        'documentType': value.documentType,
        'researchNameId': ResearchDataNameIdTO_1.ResearchDataNameIdTOToJSON(value.researchNameId),
        'newResearch': value.newResearch,
        'attachmentName': value.attachmentName,
    };
}
exports.ResearchDataInputTOToJSON = ResearchDataInputTOToJSON;
