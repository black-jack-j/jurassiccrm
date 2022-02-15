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
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
Object.defineProperty(exports, "__esModule", { value: true });
var runtime = require("../runtime");
var models_1 = require("../models");
/**
 *
 */
var GroupApi = /** @class */ (function (_super) {
    __extends(GroupApi, _super);
    function GroupApi() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * addUser
     */
    GroupApi.prototype.addUserRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.groupId === null || requestParameters.groupId === undefined) {
                            throw new runtime.RequiredError('groupId', 'Required parameter requestParameters.groupId was null or undefined when calling addUser.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        headerParameters['Content-Type'] = 'application/json';
                        return [4 /*yield*/, this.request({
                                path: "/api/group/{groupId}/user".replace("{" + "groupId" + "}", encodeURIComponent(String(requestParameters.groupId))),
                                method: 'POST',
                                headers: headerParameters,
                                query: queryParameters,
                                body: models_1.UserIdInputTOToJSON(requestParameters.body),
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.TextApiResponse(response)];
                }
            });
        });
    };
    /**
     * addUser
     */
    GroupApi.prototype.addUser = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.addUserRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * createGroup
     */
    GroupApi.prototype.createGroupRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, consumes, canConsumeForm, formParams, useForm, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.avatar === null || requestParameters.avatar === undefined) {
                            throw new runtime.RequiredError('avatar', 'Required parameter requestParameters.avatar was null or undefined when calling createGroup.');
                        }
                        if (requestParameters.groupInfo === null || requestParameters.groupInfo === undefined) {
                            throw new runtime.RequiredError('groupInfo', 'Required parameter requestParameters.groupInfo was null or undefined when calling createGroup.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        consumes = [
                            { contentType: 'multipart/form-data' },
                        ];
                        canConsumeForm = runtime.canConsumeForm(consumes);
                        useForm = false;
                        // use FormData to transmit files using content-type "multipart/form-data"
                        useForm = canConsumeForm;
                        if (useForm) {
                            formParams = new FormData();
                        }
                        else {
                            formParams = new URLSearchParams();
                        }
                        if (requestParameters.avatar !== undefined) {
                            formParams.append('avatar', requestParameters.avatar);
                        }
                        if (requestParameters.groupInfo !== undefined) {
                            formParams.append('groupInfo', requestParameters.groupInfo);
                        }
                        return [4 /*yield*/, this.request({
                                path: "/api/group",
                                method: 'POST',
                                headers: headerParameters,
                                query: queryParameters,
                                body: formParams,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.GroupOutputTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * createGroup
     */
    GroupApi.prototype.createGroup = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.createGroupRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getGroup
     */
    GroupApi.prototype.getAllGroupsRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/group",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.GroupOutputTOFromJSON); })];
                }
            });
        });
    };
    /**
     * getGroup
     */
    GroupApi.prototype.getAllGroups = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getAllGroupsRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * get group info
     */
    GroupApi.prototype.getGroupRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.id === null || requestParameters.id === undefined) {
                            throw new runtime.RequiredError('id', 'Required parameter requestParameters.id was null or undefined when calling getGroup.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/group/{id}".replace("{" + "id" + "}", encodeURIComponent(String(requestParameters.id))),
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.GroupOutputTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * get group info
     */
    GroupApi.prototype.getGroup = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getGroupRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * get group icon
     */
    GroupApi.prototype.getGroupIconRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.id === null || requestParameters.id === undefined) {
                            throw new runtime.RequiredError('id', 'Required parameter requestParameters.id was null or undefined when calling getGroupIcon.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/group/{id}/icon".replace("{" + "id" + "}", encodeURIComponent(String(requestParameters.id))),
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response)];
                }
            });
        });
    };
    /**
     * get group icon
     */
    GroupApi.prototype.getGroupIcon = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getGroupIconRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getRoles
     */
    GroupApi.prototype.getRolesRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/group/role",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response)];
                }
            });
        });
    };
    /**
     * getRoles
     */
    GroupApi.prototype.getRoles = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getRolesRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getUsers
     */
    GroupApi.prototype.getUsersRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/group/user",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.UserOutputTOFromJSON); })];
                }
            });
        });
    };
    /**
     * getUsers
     */
    GroupApi.prototype.getUsers = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getUsersRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * removeUser
     */
    GroupApi.prototype.removeUserRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.groupId === null || requestParameters.groupId === undefined) {
                            throw new runtime.RequiredError('groupId', 'Required parameter requestParameters.groupId was null or undefined when calling removeUser.');
                        }
                        if (requestParameters.userId === null || requestParameters.userId === undefined) {
                            throw new runtime.RequiredError('userId', 'Required parameter requestParameters.userId was null or undefined when calling removeUser.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/group/{groupId}/user/{userId}".replace("{" + "groupId" + "}", encodeURIComponent(String(requestParameters.groupId))).replace("{" + "userId" + "}", encodeURIComponent(String(requestParameters.userId))),
                                method: 'DELETE',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.TextApiResponse(response)];
                }
            });
        });
    };
    /**
     * removeUser
     */
    GroupApi.prototype.removeUser = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.removeUserRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * updateGroup
     */
    GroupApi.prototype.updateGroupRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, consumes, canConsumeForm, formParams, useForm, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.groupId === null || requestParameters.groupId === undefined) {
                            throw new runtime.RequiredError('groupId', 'Required parameter requestParameters.groupId was null or undefined when calling updateGroup.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        consumes = [
                            { contentType: 'multipart/form-data' },
                        ];
                        canConsumeForm = runtime.canConsumeForm(consumes);
                        useForm = false;
                        if (useForm) {
                            formParams = new FormData();
                        }
                        else {
                            formParams = new URLSearchParams();
                        }
                        if (requestParameters.name !== undefined) {
                            formParams.append('name', requestParameters.name);
                        }
                        if (requestParameters.roles) {
                            formParams.append('roles', Array.from(requestParameters.roles).join(runtime.COLLECTION_FORMATS["csv"]));
                        }
                        if (requestParameters.userIds) {
                            formParams.append('userIds', Array.from(requestParameters.userIds).join(runtime.COLLECTION_FORMATS["csv"]));
                        }
                        if (requestParameters.avatar !== undefined) {
                            formParams.append('avatar', new Blob([JSON.stringify(models_1.MultipartFileToJSON(requestParameters.avatar))], { type: "application/json", }));
                        }
                        if (requestParameters.description !== undefined) {
                            formParams.append('description', requestParameters.description);
                        }
                        return [4 /*yield*/, this.request({
                                path: "/api/group/{groupId}".replace("{" + "groupId" + "}", encodeURIComponent(String(requestParameters.groupId))),
                                method: 'PUT',
                                headers: headerParameters,
                                query: queryParameters,
                                body: formParams,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.GroupOutputTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * updateGroup
     */
    GroupApi.prototype.updateGroup = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.updateGroupRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    return GroupApi;
}(runtime.BaseAPI));
exports.GroupApi = GroupApi;
/**
    * @export
    * @enum {string}
    */
var UpdateGroupRolesEnum;
(function (UpdateGroupRolesEnum) {
    UpdateGroupRolesEnum["DocumentReader"] = "DOCUMENT_READER";
    UpdateGroupRolesEnum["DinosaurPassportReader"] = "DINOSAUR_PASSPORT_READER";
    UpdateGroupRolesEnum["AviaryPassportReader"] = "AVIARY_PASSPORT_READER";
    UpdateGroupRolesEnum["ThemeZoneProjectReader"] = "THEME_ZONE_PROJECT_READER";
    UpdateGroupRolesEnum["TechnologicalMapReader"] = "TECHNOLOGICAL_MAP_READER";
    UpdateGroupRolesEnum["ResearchDataReader"] = "RESEARCH_DATA_READER";
    UpdateGroupRolesEnum["DocumentWriter"] = "DOCUMENT_WRITER";
    UpdateGroupRolesEnum["DinosaurPassportWriter"] = "DINOSAUR_PASSPORT_WRITER";
    UpdateGroupRolesEnum["AviaryPassportWriter"] = "AVIARY_PASSPORT_WRITER";
    UpdateGroupRolesEnum["ThemeZoneProjectWriter"] = "THEME_ZONE_PROJECT_WRITER";
    UpdateGroupRolesEnum["TechnologicalMapWriter"] = "TECHNOLOGICAL_MAP_WRITER";
    UpdateGroupRolesEnum["ResearchDataWriter"] = "RESEARCH_DATA_WRITER";
    UpdateGroupRolesEnum["TaskReader"] = "TASK_READER";
    UpdateGroupRolesEnum["IncubationTaskReader"] = "INCUBATION_TASK_READER";
    UpdateGroupRolesEnum["AviaryBuildingTaskReader"] = "AVIARY_BUILDING_TASK_READER";
    UpdateGroupRolesEnum["ResearchTaskReader"] = "RESEARCH_TASK_READER";
    UpdateGroupRolesEnum["TaskWriter"] = "TASK_WRITER";
    UpdateGroupRolesEnum["IncubationTaskWriter"] = "INCUBATION_TASK_WRITER";
    UpdateGroupRolesEnum["AviaryBuildingTaskWriter"] = "AVIARY_BUILDING_TASK_WRITER";
    UpdateGroupRolesEnum["ResearchTaskWriter"] = "RESEARCH_TASK_WRITER";
    UpdateGroupRolesEnum["SecurityReader"] = "SECURITY_READER";
    UpdateGroupRolesEnum["SecurityWriter"] = "SECURITY_WRITER";
    UpdateGroupRolesEnum["Admin"] = "ADMIN";
})(UpdateGroupRolesEnum = exports.UpdateGroupRolesEnum || (exports.UpdateGroupRolesEnum = {}));
