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
function UserIdInputTOFromJSON(json) {
    return UserIdInputTOFromJSONTyped(json, false);
}
exports.UserIdInputTOFromJSON = UserIdInputTOFromJSON;
function UserIdInputTOFromJSONTyped(json, ignoreDiscriminator) {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        'id': json['id'],
    };
}
exports.UserIdInputTOFromJSONTyped = UserIdInputTOFromJSONTyped;
function UserIdInputTOToJSON(value) {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        'id': value.id,
    };
}
exports.UserIdInputTOToJSON = UserIdInputTOToJSON;
