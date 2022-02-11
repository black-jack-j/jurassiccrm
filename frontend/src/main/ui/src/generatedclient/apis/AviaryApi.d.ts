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
import { AviaryRevisionEntryTO, SimpleEntityInputTO, SimpleEntityOutputTO } from '../models';
export interface CreateAviaryTypeRequest {
    body?: SimpleEntityInputTO;
}
export interface DeleteAviaryTypeRequest {
    id: number;
}
export interface UpdateAviaryTypeRequest {
    id: number;
    body?: SimpleEntityInputTO;
}
/**
 *
 */
export declare class AviaryApi extends runtime.BaseAPI {
    /**
     * createAviary
     */
    createAviaryTypeRaw(requestParameters: CreateAviaryTypeRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<SimpleEntityOutputTO>>;
    /**
     * createAviary
     */
    createAviaryType(requestParameters: CreateAviaryTypeRequest, initOverrides?: RequestInit): Promise<SimpleEntityOutputTO>;
    /**
     * deleteAviary
     */
    deleteAviaryTypeRaw(requestParameters: DeleteAviaryTypeRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<SimpleEntityOutputTO>>;
    /**
     * deleteAviary
     */
    deleteAviaryType(requestParameters: DeleteAviaryTypeRequest, initOverrides?: RequestInit): Promise<SimpleEntityOutputTO>;
    /**
     * getAllAviaries
     */
    getAllAviaryTypesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<SimpleEntityOutputTO>>>;
    /**
     * getAllAviaries
     */
    getAllAviaryTypes(initOverrides?: RequestInit): Promise<Array<SimpleEntityOutputTO>>;
    /**
     */
    getNextAviaryRevisionsRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<AviaryRevisionEntryTO>>>;
    /**
     */
    getNextAviaryRevisions(initOverrides?: RequestInit): Promise<Array<AviaryRevisionEntryTO>>;
    /**
     * updateAviary
     */
    updateAviaryTypeRaw(requestParameters: UpdateAviaryTypeRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<SimpleEntityOutputTO>>;
    /**
     * updateAviary
     */
    updateAviaryType(requestParameters: UpdateAviaryTypeRequest, initOverrides?: RequestInit): Promise<SimpleEntityOutputTO>;
}