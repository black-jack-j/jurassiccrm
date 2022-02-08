import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../generatedclient/apis";
import {CreateResearchDataFormContainer} from "./researchdata/container/create-research-form-container";
import {CreateDocumentFormContainer} from "./container/create-document-form-container";

export const withType = documentType => {

    switch (documentType) {
        case DocumentTypeEnum.ResearchData: {
            return [CreateResearchDataFormContainer]
        }
        case DocumentTypeEnum.DinosaurPassport:
        case DocumentTypeEnum.AviaryPassport:
        case DocumentTypeEnum.TechnologicalMap:
        case DocumentTypeEnum.ThemeZoneProject: {
            return [CreateDocumentFormContainer]
        }
        default: {
            console.error(`Unknown document type: ${documentType}`)
        }
    }

}