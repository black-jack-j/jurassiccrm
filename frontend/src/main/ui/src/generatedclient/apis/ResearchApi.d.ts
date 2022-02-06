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
import { ResearchDataInputTO, SimpleEntityInputTO, SimpleEntityOutputTO } from '../models';
export interface CreateResearchRequest {
    body?: SimpleEntityInputTO;
}
export interface DeleteResearchRequest {
    id: number;
}
export interface UpdateResearchRequest {
    id: number;
    body?: SimpleEntityInputTO;
}
/**
 *
 */
export declare class ResearchApi extends runtime.BaseAPI {
    /**
     * createResearch
     */
    createResearchRaw(requestParameters: CreateResearchRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<SimpleEntityOutputTO>>;
    /**
     * createResearch
     */
    createResearch(requestParameters: CreateResearchRequest, initOverrides?: RequestInit): Promise<SimpleEntityOutputTO>;
    /**
     * deleteResearch
     */
    deleteResearchRaw(requestParameters: DeleteResearchRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<SimpleEntityOutputTO>>;
    /**
     * deleteResearch
     */
    deleteResearch(requestParameters: DeleteResearchRequest, initOverrides?: RequestInit): Promise<SimpleEntityOutputTO>;
    /**
     * getAllResearches
     */
    getAllResearchesRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<SimpleEntityOutputTO>>>;
    /**
     * getAllResearches
     */
    getAllResearches(initOverrides?: RequestInit): Promise<Array<SimpleEntityOutputTO>>;
    /**
     * getResearchData
     */
    getResearchDataRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<ResearchDataInputTO>>;
    /**
     * getResearchData
     */
    getResearchData(initOverrides?: RequestInit): Promise<ResearchDataInputTO>;
    /**
     * updateResearch
     */
    updateResearchRaw(requestParameters: UpdateResearchRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<SimpleEntityOutputTO>>;
    /**
     * updateResearch
     */
    updateResearch(requestParameters: UpdateResearchRequest, initOverrides?: RequestInit): Promise<SimpleEntityOutputTO>;
}
