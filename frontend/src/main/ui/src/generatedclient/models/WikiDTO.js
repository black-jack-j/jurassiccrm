"use strict";
/* tslint:disable */
/* eslint-disable */
/**
 * Jurassic CRM API
 * Jurassic CRM
 *
 * The version of the OpenAPI document: v0.0.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
Object.defineProperty(exports, "__esModule", { value: true });
var runtime_1 = require("../runtime");
function WikiDTOFromJSON(json) {
    return WikiDTOFromJSONTyped(json, false);
}
exports.WikiDTOFromJSON = WikiDTOFromJSON;
function WikiDTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'id': !runtime_1.exists(json, 'id') ? undefined : json['id'],
        'title': !runtime_1.exists(json, 'title') ? undefined : json['title'],
        'text': !runtime_1.exists(json, 'text') ? undefined : json['text'],
        'image': !runtime_1.exists(json, 'image') ? undefined : json['image'],
        'relatedPages': !runtime_1.exists(json, 'relatedPages') ? undefined : json['relatedPages'],
    };
}
exports.WikiDTOFromJSONTyped = WikiDTOFromJSONTyped;
function WikiDTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'id': value.id,
        'title': value.title,
        'text': value.text,
        'image': value.image,
        'relatedPages': value.relatedPages,
    };
}
exports.WikiDTOToJSON = WikiDTOToJSON;
