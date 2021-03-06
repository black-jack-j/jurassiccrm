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
import * as runtime from '../runtime';
import { CreateUserTO, FullUserOutputTO, SimpleUserInfoTO, UpdateUserTO, UserWithRolesTO } from '../models';
export interface CreateUserRequest {
    body?: CreateUserTO;
}
export interface FindAllByRolesAllRequest {
    roles: Array<FindAllByRolesAllRolesEnum>;
}
export interface FindAllByRolesAnyRequest {
    roles: Array<FindAllByRolesAnyRolesEnum>;
}
export interface GetUserByIdRequest {
    userId: number;
}
export interface GetUserIconRequest {
    id: number;
}
export interface UpdateUserRequest {
    userId: number;
    body?: UpdateUserTO;
}
export interface UpdateUserAvatarRequest {
    userId: number;
    avatar: Blob;
}
/**
 *
 */
export declare class UserApi extends runtime.BaseAPI {
    /**
     * createUser
     */
    createUserRaw(requestParameters: CreateUserRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<FullUserOutputTO>>;
    /**
     * createUser
     */
    createUser(requestParameters: CreateUserRequest, initOverrides?: RequestInit): Promise<FullUserOutputTO>;
    /**
     * findAllByRolesAll
     */
    findAllByRolesAllRaw(requestParameters: FindAllByRolesAllRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<FullUserOutputTO>>>;
    /**
     * findAllByRolesAll
     */
    findAllByRolesAll(requestParameters: FindAllByRolesAllRequest, initOverrides?: RequestInit): Promise<Array<FullUserOutputTO>>;
    /**
     * findAllByRolesAny
     */
    findAllByRolesAnyRaw(requestParameters: FindAllByRolesAnyRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<FullUserOutputTO>>>;
    /**
     * findAllByRolesAny
     */
    findAllByRolesAny(requestParameters: FindAllByRolesAnyRequest, initOverrides?: RequestInit): Promise<Array<FullUserOutputTO>>;
    /**
     * getCurrentUser
     */
    getCurrentUserRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<UserWithRolesTO>>;
    /**
     * getCurrentUser
     */
    getCurrentUser(initOverrides?: RequestInit): Promise<UserWithRolesTO>;
    /**
     * getCurrentUserRoles
     */
    getCurrentUserRolesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Set<string>>>;
    /**
     * getCurrentUserRoles
     */
    getCurrentUserRoles(initOverrides?: RequestInit): Promise<Set<string>>;
    /**
     * getUserById
     */
    getUserByIdRaw(requestParameters: GetUserByIdRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<FullUserOutputTO>>;
    /**
     * getUserById
     */
    getUserById(requestParameters: GetUserByIdRequest, initOverrides?: RequestInit): Promise<FullUserOutputTO>;
    /**
     * get user icon
     */
    getUserIconRaw(requestParameters: GetUserIconRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<string>>>;
    /**
     * get user icon
     */
    getUserIcon(requestParameters: GetUserIconRequest, initOverrides?: RequestInit): Promise<Array<string>>;
    /**
     * getUsers
     */
    getUsersFullRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<FullUserOutputTO>>>;
    /**
     * getUsers
     */
    getUsersFull(initOverrides?: RequestInit): Promise<Array<FullUserOutputTO>>;
    /**
     * get users simple
     */
    getUsersSimpleRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<SimpleUserInfoTO>>>;
    /**
     * get users simple
     */
    getUsersSimple(initOverrides?: RequestInit): Promise<Array<SimpleUserInfoTO>>;
    /**
     * updateUser
     */
    updateUserRaw(requestParameters: UpdateUserRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<FullUserOutputTO>>;
    /**
     * updateUser
     */
    updateUser(requestParameters: UpdateUserRequest, initOverrides?: RequestInit): Promise<FullUserOutputTO>;
    /**
     * update user avatar
     */
    updateUserAvatarRaw(requestParameters: UpdateUserAvatarRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<object>>;
    /**
     * update user avatar
     */
    updateUserAvatar(requestParameters: UpdateUserAvatarRequest, initOverrides?: RequestInit): Promise<object>;
}
/**
    * @export
    * @enum {string}
    */
export declare enum FindAllByRolesAllRolesEnum {
    DocumentReader = "DOCUMENT_READER",
    DinosaurPassportReader = "DINOSAUR_PASSPORT_READER",
    AviaryPassportReader = "AVIARY_PASSPORT_READER",
    ThemeZoneProjectReader = "THEME_ZONE_PROJECT_READER",
    TechnologicalMapReader = "TECHNOLOGICAL_MAP_READER",
    ResearchDataReader = "RESEARCH_DATA_READER",
    DocumentWriter = "DOCUMENT_WRITER",
    DinosaurPassportWriter = "DINOSAUR_PASSPORT_WRITER",
    AviaryPassportWriter = "AVIARY_PASSPORT_WRITER",
    ThemeZoneProjectWriter = "THEME_ZONE_PROJECT_WRITER",
    TechnologicalMapWriter = "TECHNOLOGICAL_MAP_WRITER",
    ResearchDataWriter = "RESEARCH_DATA_WRITER",
    TaskReader = "TASK_READER",
    IncubationTaskReader = "INCUBATION_TASK_READER",
    AviaryBuildingTaskReader = "AVIARY_BUILDING_TASK_READER",
    ResearchTaskReader = "RESEARCH_TASK_READER",
    TaskWriter = "TASK_WRITER",
    IncubationTaskWriter = "INCUBATION_TASK_WRITER",
    AviaryBuildingTaskWriter = "AVIARY_BUILDING_TASK_WRITER",
    ResearchTaskWriter = "RESEARCH_TASK_WRITER",
    SecurityReader = "SECURITY_READER",
    SecurityWriter = "SECURITY_WRITER",
    Admin = "ADMIN"
}
/**
    * @export
    * @enum {string}
    */
export declare enum FindAllByRolesAnyRolesEnum {
    DocumentReader = "DOCUMENT_READER",
    DinosaurPassportReader = "DINOSAUR_PASSPORT_READER",
    AviaryPassportReader = "AVIARY_PASSPORT_READER",
    ThemeZoneProjectReader = "THEME_ZONE_PROJECT_READER",
    TechnologicalMapReader = "TECHNOLOGICAL_MAP_READER",
    ResearchDataReader = "RESEARCH_DATA_READER",
    DocumentWriter = "DOCUMENT_WRITER",
    DinosaurPassportWriter = "DINOSAUR_PASSPORT_WRITER",
    AviaryPassportWriter = "AVIARY_PASSPORT_WRITER",
    ThemeZoneProjectWriter = "THEME_ZONE_PROJECT_WRITER",
    TechnologicalMapWriter = "TECHNOLOGICAL_MAP_WRITER",
    ResearchDataWriter = "RESEARCH_DATA_WRITER",
    TaskReader = "TASK_READER",
    IncubationTaskReader = "INCUBATION_TASK_READER",
    AviaryBuildingTaskReader = "AVIARY_BUILDING_TASK_READER",
    ResearchTaskReader = "RESEARCH_TASK_READER",
    TaskWriter = "TASK_WRITER",
    IncubationTaskWriter = "INCUBATION_TASK_WRITER",
    AviaryBuildingTaskWriter = "AVIARY_BUILDING_TASK_WRITER",
    ResearchTaskWriter = "RESEARCH_TASK_WRITER",
    SecurityReader = "SECURITY_READER",
    SecurityWriter = "SECURITY_WRITER",
    Admin = "ADMIN"
}
