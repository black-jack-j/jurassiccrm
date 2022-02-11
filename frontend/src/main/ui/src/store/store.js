import {combineReducers, configureStore} from "@reduxjs/toolkit";
import CreateTaskPopupReducer, {CREATE_TASK_POPUP_SLICE_NAME} from "../components/task/form/popup/create-task-popup-slice";
import EditTaskPopupReducer, {EDIT_TASK_POPUP_SLICE_NAME} from "../components/task/editor/edit-task-popup-slice";
import ViewTaskReducer, {TASK_VIEWER_SLICE_NAME} from "../components/task/viewer/viewer-slice";
import CreateDocumentPopupReducer, {CREATE_DOCUMENT_POPUP_SLICE_NAME} from "../components/document/form/create/popup/create-document-popup-slice";
import DocumentFormSelectorPopupReducer, {DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME} from "../components/document/documentformselector/popup/documentform-selector-popup-slice";

export default configureStore({
    reducer: combineReducers({
        [CREATE_TASK_POPUP_SLICE_NAME]: CreateTaskPopupReducer,
        [EDIT_TASK_POPUP_SLICE_NAME]: EditTaskPopupReducer,
        [TASK_VIEWER_SLICE_NAME]: ViewTaskReducer,
        [CREATE_DOCUMENT_POPUP_SLICE_NAME]: CreateDocumentPopupReducer,
        [DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME]: DocumentFormSelectorPopupReducer
    })
})