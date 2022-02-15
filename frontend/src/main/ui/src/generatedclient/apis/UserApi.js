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
var UserApi = /** @class */ (function (_super) {
    __extends(UserApi, _super);
    function UserApi() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * createUser
     */
    UserApi.prototype.createUserRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        headerParameters['Content-Type'] = 'application/json';
                        return [4 /*yield*/, this.request({
                                path: "/api/user",
                                method: 'POST',
                                headers: headerParameters,
                                query: queryParameters,
                                body: models_1.FullUserInputTOToJSON(requestParameters.body),
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.FullUserOutputTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * createUser
     */
    UserApi.prototype.createUser = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.createUserRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * findAllByRolesAll
     */
    UserApi.prototype.findAllByRolesAllRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.roles === null || requestParameters.roles === undefined) {
                            throw new runtime.RequiredError('roles', 'Required parameter requestParameters.roles was null or undefined when calling findAllByRolesAll.');
                        }
                        queryParameters = {};
                        if (requestParameters.roles) {
                            queryParameters['roles'] = requestParameters.roles;
                        }
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/user/role-all",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.FullUserOutputTOFromJSON); })];
                }
            });
        });
    };
    /**
     * findAllByRolesAll
     */
    UserApi.prototype.findAllByRolesAll = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.findAllByRolesAllRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * findAllByRolesAny
     */
    UserApi.prototype.findAllByRolesAnyRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.roles === null || requestParameters.roles === undefined) {
                            throw new runtime.RequiredError('roles', 'Required parameter requestParameters.roles was null or undefined when calling findAllByRolesAny.');
                        }
                        queryParameters = {};
                        if (requestParameters.roles) {
                            queryParameters['roles'] = requestParameters.roles;
                        }
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/user/role-any",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.FullUserOutputTOFromJSON); })];
                }
            });
        });
    };
    /**
     * findAllByRolesAny
     */
    UserApi.prototype.findAllByRolesAny = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.findAllByRolesAnyRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getCurrentUserRoles
     */
    UserApi.prototype.getCurrentUserRolesRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/user/active/role",
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
     * getCurrentUserRoles
     */
    UserApi.prototype.getCurrentUserRoles = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getCurrentUserRolesRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getUserById
     */
    UserApi.prototype.getUserByIdRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.userId === null || requestParameters.userId === undefined) {
                            throw new runtime.RequiredError('userId', 'Required parameter requestParameters.userId was null or undefined when calling getUserById.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/user/{userId}".replace("{" + "userId" + "}", encodeURIComponent(String(requestParameters.userId))),
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.FullUserOutputTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * getUserById
     */
    UserApi.prototype.getUserById = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getUserByIdRaw(requestParameters, initOverrides)];
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
    UserApi.prototype.getUsersFullRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/user",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.FullUserOutputTOFromJSON); })];
                }
            });
        });
    };
    /**
     * getUsers
     */
    UserApi.prototype.getUsersFull = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getUsersFullRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * get users simple
     */
    UserApi.prototype.getUsersSimpleRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/user/simple",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.SimpleUserInfoTOFromJSON); })];
                }
            });
        });
    };
    /**
     * get users simple
     */
    UserApi.prototype.getUsersSimple = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getUsersSimpleRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * updateUser
     */
    UserApi.prototype.updateUserRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.userId === null || requestParameters.userId === undefined) {
                            throw new runtime.RequiredError('userId', 'Required parameter requestParameters.userId was null or undefined when calling updateUser.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        headerParameters['Content-Type'] = 'application/json';
                        return [4 /*yield*/, this.request({
                                path: "/api/user/{userId}".replace("{" + "userId" + "}", encodeURIComponent(String(requestParameters.userId))),
                                method: 'PUT',
                                headers: headerParameters,
                                query: queryParameters,
                                body: models_1.FullUserInputTOToJSON(requestParameters.body),
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.FullUserOutputTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * updateUser
     */
    UserApi.prototype.updateUser = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.updateUserRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    return UserApi;
}(runtime.BaseAPI));
exports.UserApi = UserApi;
/**
    * @export
    * @enum {string}
    */
var FindAllByRolesAllRolesEnum;
(function (FindAllByRolesAllRolesEnum) {
    FindAllByRolesAllRolesEnum["DocumentReader"] = "DOCUMENT_READER";
    FindAllByRolesAllRolesEnum["DinosaurPassportReader"] = "DINOSAUR_PASSPORT_READER";
    FindAllByRolesAllRolesEnum["AviaryPassportReader"] = "AVIARY_PASSPORT_READER";
    FindAllByRolesAllRolesEnum["ThemeZoneProjectReader"] = "THEME_ZONE_PROJECT_READER";
    FindAllByRolesAllRolesEnum["TechnologicalMapReader"] = "TECHNOLOGICAL_MAP_READER";
    FindAllByRolesAllRolesEnum["ResearchDataReader"] = "RESEARCH_DATA_READER";
    FindAllByRolesAllRolesEnum["DocumentWriter"] = "DOCUMENT_WRITER";
    FindAllByRolesAllRolesEnum["DinosaurPassportWriter"] = "DINOSAUR_PASSPORT_WRITER";
    FindAllByRolesAllRolesEnum["AviaryPassportWriter"] = "AVIARY_PASSPORT_WRITER";
    FindAllByRolesAllRolesEnum["ThemeZoneProjectWriter"] = "THEME_ZONE_PROJECT_WRITER";
    FindAllByRolesAllRolesEnum["TechnologicalMapWriter"] = "TECHNOLOGICAL_MAP_WRITER";
    FindAllByRolesAllRolesEnum["ResearchDataWriter"] = "RESEARCH_DATA_WRITER";
    FindAllByRolesAllRolesEnum["TaskReader"] = "TASK_READER";
    FindAllByRolesAllRolesEnum["IncubationTaskReader"] = "INCUBATION_TASK_READER";
    FindAllByRolesAllRolesEnum["AviaryBuildingTaskReader"] = "AVIARY_BUILDING_TASK_READER";
    FindAllByRolesAllRolesEnum["ResearchTaskReader"] = "RESEARCH_TASK_READER";
    FindAllByRolesAllRolesEnum["TaskWriter"] = "TASK_WRITER";
    FindAllByRolesAllRolesEnum["IncubationTaskWriter"] = "INCUBATION_TASK_WRITER";
    FindAllByRolesAllRolesEnum["AviaryBuildingTaskWriter"] = "AVIARY_BUILDING_TASK_WRITER";
    FindAllByRolesAllRolesEnum["ResearchTaskWriter"] = "RESEARCH_TASK_WRITER";
    FindAllByRolesAllRolesEnum["SecurityReader"] = "SECURITY_READER";
    FindAllByRolesAllRolesEnum["SecurityWriter"] = "SECURITY_WRITER";
    FindAllByRolesAllRolesEnum["Admin"] = "ADMIN";
})(FindAllByRolesAllRolesEnum = exports.FindAllByRolesAllRolesEnum || (exports.FindAllByRolesAllRolesEnum = {}));
/**
    * @export
    * @enum {string}
    */
var FindAllByRolesAnyRolesEnum;
(function (FindAllByRolesAnyRolesEnum) {
    FindAllByRolesAnyRolesEnum["DocumentReader"] = "DOCUMENT_READER";
    FindAllByRolesAnyRolesEnum["DinosaurPassportReader"] = "DINOSAUR_PASSPORT_READER";
    FindAllByRolesAnyRolesEnum["AviaryPassportReader"] = "AVIARY_PASSPORT_READER";
    FindAllByRolesAnyRolesEnum["ThemeZoneProjectReader"] = "THEME_ZONE_PROJECT_READER";
    FindAllByRolesAnyRolesEnum["TechnologicalMapReader"] = "TECHNOLOGICAL_MAP_READER";
    FindAllByRolesAnyRolesEnum["ResearchDataReader"] = "RESEARCH_DATA_READER";
    FindAllByRolesAnyRolesEnum["DocumentWriter"] = "DOCUMENT_WRITER";
    FindAllByRolesAnyRolesEnum["DinosaurPassportWriter"] = "DINOSAUR_PASSPORT_WRITER";
    FindAllByRolesAnyRolesEnum["AviaryPassportWriter"] = "AVIARY_PASSPORT_WRITER";
    FindAllByRolesAnyRolesEnum["ThemeZoneProjectWriter"] = "THEME_ZONE_PROJECT_WRITER";
    FindAllByRolesAnyRolesEnum["TechnologicalMapWriter"] = "TECHNOLOGICAL_MAP_WRITER";
    FindAllByRolesAnyRolesEnum["ResearchDataWriter"] = "RESEARCH_DATA_WRITER";
    FindAllByRolesAnyRolesEnum["TaskReader"] = "TASK_READER";
    FindAllByRolesAnyRolesEnum["IncubationTaskReader"] = "INCUBATION_TASK_READER";
    FindAllByRolesAnyRolesEnum["AviaryBuildingTaskReader"] = "AVIARY_BUILDING_TASK_READER";
    FindAllByRolesAnyRolesEnum["ResearchTaskReader"] = "RESEARCH_TASK_READER";
    FindAllByRolesAnyRolesEnum["TaskWriter"] = "TASK_WRITER";
    FindAllByRolesAnyRolesEnum["IncubationTaskWriter"] = "INCUBATION_TASK_WRITER";
    FindAllByRolesAnyRolesEnum["AviaryBuildingTaskWriter"] = "AVIARY_BUILDING_TASK_WRITER";
    FindAllByRolesAnyRolesEnum["ResearchTaskWriter"] = "RESEARCH_TASK_WRITER";
    FindAllByRolesAnyRolesEnum["SecurityReader"] = "SECURITY_READER";
    FindAllByRolesAnyRolesEnum["SecurityWriter"] = "SECURITY_WRITER";
    FindAllByRolesAnyRolesEnum["Admin"] = "ADMIN";
})(FindAllByRolesAnyRolesEnum = exports.FindAllByRolesAnyRolesEnum || (exports.FindAllByRolesAnyRolesEnum = {}));
