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
var UserOutputTO_1 = require("./UserOutputTO");
/**
* @export
* @enum {string}
*/
var DocumentOutputTOTypeEnum;
(function (DocumentOutputTOTypeEnum) {
    DocumentOutputTOTypeEnum["ThemeZoneProject"] = "THEME_ZONE_PROJECT";
    DocumentOutputTOTypeEnum["DinosaurPassport"] = "DINOSAUR_PASSPORT";
    DocumentOutputTOTypeEnum["TechnologicalMap"] = "TECHNOLOGICAL_MAP";
    DocumentOutputTOTypeEnum["AviaryPassport"] = "AVIARY_PASSPORT";
    DocumentOutputTOTypeEnum["ResearchData"] = "RESEARCH_DATA";
})(DocumentOutputTOTypeEnum = exports.DocumentOutputTOTypeEnum || (exports.DocumentOutputTOTypeEnum = {}));
function DocumentOutputTOFromJSON(json) {
    return DocumentOutputTOFromJSONTyped(json, false);
}
exports.DocumentOutputTOFromJSON = DocumentOutputTOFromJSON;
function DocumentOutputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'id': !runtime_1.exists(json, 'id') ? undefined : json['id'],
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
        'type': !runtime_1.exists(json, 'type') ? undefined : json['type'],
        'author': !runtime_1.exists(json, 'author') ? undefined : UserOutputTO_1.UserOutputTOFromJSON(json['author']),
        'lastUpdater': !runtime_1.exists(json, 'lastUpdater') ? undefined : UserOutputTO_1.UserOutputTOFromJSON(json['lastUpdater']),
        'created': !runtime_1.exists(json, 'created') ? undefined : json['created'],
        'lastUpdate': !runtime_1.exists(json, 'lastUpdate') ? undefined : json['lastUpdate'],
        'description': !runtime_1.exists(json, 'description') ? undefined : json['description'],
    };
}
exports.DocumentOutputTOFromJSONTyped = DocumentOutputTOFromJSONTyped;
function DocumentOutputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'id': value.id,
        'name': value.name,
        'type': value.type,
        'author': UserOutputTO_1.UserOutputTOToJSON(value.author),
        'lastUpdater': UserOutputTO_1.UserOutputTOToJSON(value.lastUpdater),
        'created': value.created,
        'lastUpdate': value.lastUpdate,
        'description': value.description,
    };
}
exports.DocumentOutputTOToJSON = DocumentOutputTOToJSON;
