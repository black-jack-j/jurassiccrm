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
var DocumentOutputTO_1 = require("./DocumentOutputTO");
var ThemeZoneElementOutputTO_1 = require("./ThemeZoneElementOutputTO");
var UserOutputTO_1 = require("./UserOutputTO");
function ThemeZoneProjectOutputTOFromJSON(json) {
    return ThemeZoneProjectOutputTOFromJSONTyped(json, false);
}
exports.ThemeZoneProjectOutputTOFromJSON = ThemeZoneProjectOutputTOFromJSON;
function ThemeZoneProjectOutputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return __assign({}, DocumentOutputTO_1.DocumentOutputTOFromJSONTyped(json, ignoreDiscriminator), { 'projectName': !runtime_1.exists(json, 'projectName') ? undefined : json['projectName'], 'manager': !runtime_1.exists(json, 'manager') ? undefined : UserOutputTO_1.UserOutputTOFromJSON(json['manager']), 'dinosaurs': !runtime_1.exists(json, 'dinosaurs') ? undefined : (json['dinosaurs'].map(ThemeZoneElementOutputTO_1.ThemeZoneElementOutputTOFromJSON)), 'aviaries': !runtime_1.exists(json, 'aviaries') ? undefined : (json['aviaries'].map(ThemeZoneElementOutputTO_1.ThemeZoneElementOutputTOFromJSON)), 'decorations': !runtime_1.exists(json, 'decorations') ? undefined : (json['decorations'].map(ThemeZoneElementOutputTO_1.ThemeZoneElementOutputTOFromJSON)) });
}
exports.ThemeZoneProjectOutputTOFromJSONTyped = ThemeZoneProjectOutputTOFromJSONTyped;
function ThemeZoneProjectOutputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return __assign({}, DocumentOutputTO_1.DocumentOutputTOToJSON(value), { 'projectName': value.projectName, 'manager': UserOutputTO_1.UserOutputTOToJSON(value.manager), 'dinosaurs': value.dinosaurs === undefined ? undefined : (value.dinosaurs.map(ThemeZoneElementOutputTO_1.ThemeZoneElementOutputTOToJSON)), 'aviaries': value.aviaries === undefined ? undefined : (value.aviaries.map(ThemeZoneElementOutputTO_1.ThemeZoneElementOutputTOToJSON)), 'decorations': value.decorations === undefined ? undefined : (value.decorations.map(ThemeZoneElementOutputTO_1.ThemeZoneElementOutputTOToJSON)) });
}
exports.ThemeZoneProjectOutputTOToJSON = ThemeZoneProjectOutputTOToJSON;