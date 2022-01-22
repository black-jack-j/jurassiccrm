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
function ScheduleItemFromJSON(json) {
    return ScheduleItemFromJSONTyped(json, false);
}
exports.ScheduleItemFromJSON = ScheduleItemFromJSON;
function ScheduleItemFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'date': !runtime_1.exists(json, 'date') ? undefined : (new Date(json['date'])),
        'name': !runtime_1.exists(json, 'name') ? undefined : json['name'],
    };
}
exports.ScheduleItemFromJSONTyped = ScheduleItemFromJSONTyped;
function ScheduleItemToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'date': value.date === undefined ? undefined : (value.date.toISOString().substr(0, 10)),
        'name': value.name,
    };
}
exports.ScheduleItemToJSON = ScheduleItemToJSON;
