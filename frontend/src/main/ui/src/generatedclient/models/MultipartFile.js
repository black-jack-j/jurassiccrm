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
function MultipartFileFromJSON(json) {
    return MultipartFileFromJSONTyped(json, false);
}
exports.MultipartFileFromJSON = MultipartFileFromJSON;
function MultipartFileFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
        'bytes': !runtime_1.exists(json, 'bytes') ? undefined : json['bytes'],
        'empty': !runtime_1.exists(json, 'empty') ? undefined : json['empty'],
        'size': !runtime_1.exists(json, 'size') ? undefined : json['size'],
        'inputStream': !runtime_1.exists(json, 'inputStream') ? undefined : json['inputStream'],
        'contentType': !runtime_1.exists(json, 'contentType') ? undefined : json['contentType'],
        'originalFilename': !runtime_1.exists(json, 'originalFilename') ? undefined : json['originalFilename'],
    };
}
exports.MultipartFileFromJSONTyped = MultipartFileFromJSONTyped;
function MultipartFileToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'name': value.name,
        'bytes': value.bytes,
        'empty': value.empty,
        'size': value.size,
        'inputStream': value.inputStream,
        'contentType': value.contentType,
        'originalFilename': value.originalFilename,
    };
}
exports.MultipartFileToJSON = MultipartFileToJSON;