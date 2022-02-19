import {DINOSAUR_EGG_CREATION_STEPS, DINOSAUR_INCUBATION_STEPS, DINOSAUR_TYPE_ID, DOCUMENT_NAME} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../../generatedclient/apis";

export default form => ({
    name: form[DOCUMENT_NAME],
    documentType: DocumentType.TechnologicalMap,
    dinosaurTypeId: form[DINOSAUR_TYPE_ID].value,
    incubationSteps: form[DINOSAUR_INCUBATION_STEPS],
    eggCreationSteps: form[DINOSAUR_EGG_CREATION_STEPS]
})