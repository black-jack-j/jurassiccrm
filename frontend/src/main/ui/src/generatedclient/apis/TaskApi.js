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
var TaskApi = /** @class */ (function (_super) {
    __extends(TaskApi, _super);
    function TaskApi() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * changeStatus
     */
    TaskApi.prototype.changeStatusRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.taskId === null || requestParameters.taskId === undefined) {
                            throw new runtime.RequiredError('taskId', 'Required parameter requestParameters.taskId was null or undefined when calling changeStatus.');
                        }
                        if (requestParameters.taskState === null || requestParameters.taskState === undefined) {
                            throw new runtime.RequiredError('taskState', 'Required parameter requestParameters.taskState was null or undefined when calling changeStatus.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/task/{taskId}/status/{taskState}".replace("{" + "taskId" + "}", encodeURIComponent(String(requestParameters.taskId))).replace("{" + "taskState" + "}", encodeURIComponent(String(requestParameters.taskState))),
                                method: 'PATCH',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.TaskTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * changeStatus
     */
    TaskApi.prototype.changeStatus = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.changeStatusRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * createTask
     */
    TaskApi.prototype.createTaskRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.taskType === null || requestParameters.taskType === undefined) {
                            throw new runtime.RequiredError('taskType', 'Required parameter requestParameters.taskType was null or undefined when calling createTask.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        headerParameters['Content-Type'] = 'application/json';
                        return [4 /*yield*/, this.request({
                                path: "/api/task/{taskType}".replace("{" + "taskType" + "}", encodeURIComponent(String(requestParameters.taskType))),
                                method: 'POST',
                                headers: headerParameters,
                                query: queryParameters,
                                body: models_1.TaskTOToJSON(requestParameters.body),
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.TaskTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * createTask
     */
    TaskApi.prototype.createTask = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.createTaskRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getPossibleAssignees
     */
    TaskApi.prototype.getPossibleAssigneesRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/task/assignee",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.AssigneeDTOFromJSON); })];
                }
            });
        });
    };
    /**
     * getPossibleAssignees
     */
    TaskApi.prototype.getPossibleAssignees = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getPossibleAssigneesRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * get available priorities
     */
    TaskApi.prototype.getPrioritiesRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/task/priority",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.TaskPriorityTOFromJSON); })];
                }
            });
        });
    };
    /**
     * get available priorities
     */
    TaskApi.prototype.getPriorities = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getPrioritiesRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * get task by id
     */
    TaskApi.prototype.getTaskByIdRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.taskType === null || requestParameters.taskType === undefined) {
                            throw new runtime.RequiredError('taskType', 'Required parameter requestParameters.taskType was null or undefined when calling getTaskById.');
                        }
                        if (requestParameters.id === null || requestParameters.id === undefined) {
                            throw new runtime.RequiredError('id', 'Required parameter requestParameters.id was null or undefined when calling getTaskById.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/task/{taskType}/{id}".replace("{" + "taskType" + "}", encodeURIComponent(String(requestParameters.taskType))).replace("{" + "id" + "}", encodeURIComponent(String(requestParameters.id))),
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.TaskTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * get task by id
     */
    TaskApi.prototype.getTaskById = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getTaskByIdRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getTaskTypes
     */
    TaskApi.prototype.getTaskTypesRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/task/type",
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
     * getTaskTypes
     */
    TaskApi.prototype.getTaskTypes = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getTaskTypesRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * getTasks
     */
    TaskApi.prototype.getTasksRaw = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        queryParameters = {};
                        headerParameters = {};
                        return [4 /*yield*/, this.request({
                                path: "/api/task",
                                method: 'GET',
                                headers: headerParameters,
                                query: queryParameters,
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return jsonValue.map(models_1.TaskTOFromJSON); })];
                }
            });
        });
    };
    /**
     * getTasks
     */
    TaskApi.prototype.getTasks = function (initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.getTasksRaw(initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    /**
     * updateTask
     */
    TaskApi.prototype.updateTaskRaw = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var queryParameters, headerParameters, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (requestParameters.taskId === null || requestParameters.taskId === undefined) {
                            throw new runtime.RequiredError('taskId', 'Required parameter requestParameters.taskId was null or undefined when calling updateTask.');
                        }
                        queryParameters = {};
                        headerParameters = {};
                        headerParameters['Content-Type'] = 'application/json';
                        return [4 /*yield*/, this.request({
                                path: "/api/task/{taskId}".replace("{" + "taskId" + "}", encodeURIComponent(String(requestParameters.taskId))),
                                method: 'PUT',
                                headers: headerParameters,
                                query: queryParameters,
                                body: models_1.TaskTOToJSON(requestParameters.body),
                            }, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [2 /*return*/, new runtime.JSONApiResponse(response, function (jsonValue) { return models_1.TaskTOFromJSON(jsonValue); })];
                }
            });
        });
    };
    /**
     * updateTask
     */
    TaskApi.prototype.updateTask = function (requestParameters, initOverrides) {
        return __awaiter(this, void 0, void 0, function () {
            var response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.updateTaskRaw(requestParameters, initOverrides)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.value()];
                    case 2: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    return TaskApi;
}(runtime.BaseAPI));
exports.TaskApi = TaskApi;
/**
    * @export
    * @enum {string}
    */
var ChangeStatusTaskStateEnum;
(function (ChangeStatusTaskStateEnum) {
    ChangeStatusTaskStateEnum["Closed"] = "CLOSED";
    ChangeStatusTaskStateEnum["Resolved"] = "RESOLVED";
    ChangeStatusTaskStateEnum["InProgress"] = "IN_PROGRESS";
    ChangeStatusTaskStateEnum["Open"] = "OPEN";
})(ChangeStatusTaskStateEnum = exports.ChangeStatusTaskStateEnum || (exports.ChangeStatusTaskStateEnum = {}));
/**
    * @export
    * @enum {string}
    */
var CreateTaskTaskTypeEnum;
(function (CreateTaskTaskTypeEnum) {
    CreateTaskTaskTypeEnum["Research"] = "RESEARCH";
    CreateTaskTaskTypeEnum["Incubation"] = "INCUBATION";
    CreateTaskTaskTypeEnum["AviaryCreation"] = "AVIARY_CREATION";
})(CreateTaskTaskTypeEnum = exports.CreateTaskTaskTypeEnum || (exports.CreateTaskTaskTypeEnum = {}));
/**
    * @export
    * @enum {string}
    */
var GetTaskByIdTaskTypeEnum;
(function (GetTaskByIdTaskTypeEnum) {
    GetTaskByIdTaskTypeEnum["Research"] = "RESEARCH";
    GetTaskByIdTaskTypeEnum["Incubation"] = "INCUBATION";
    GetTaskByIdTaskTypeEnum["AviaryCreation"] = "AVIARY_CREATION";
})(GetTaskByIdTaskTypeEnum = exports.GetTaskByIdTaskTypeEnum || (exports.GetTaskByIdTaskTypeEnum = {}));
