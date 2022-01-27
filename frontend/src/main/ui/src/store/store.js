import {combineReducers, configureStore} from "@reduxjs/toolkit";
import {CREATE_TASK_POPUP_SLICE_NAME} from "../components/task/form/create-task-popup-slice";
import CreateTaskPopupReducer from "../components/task/form/create-task-popup-slice"
import {EDIT_TASK_POPUP_SLICE_NAME} from "../components/task/editor/edit-task-popup-slice";
import EditTaskPopupReducer from "../components/task/editor/edit-task-popup-slice"
import {TASK_VIEWER_SLICE_NAME} from "../components/task/viewer/viewer-slice";
import ViewTaskReducer from "../components/task/viewer/viewer-slice"
import {CREATE_DOCUMENT_POPUP_SLICE_NAME} from "../components/document/createdocument/create-document-popup-slice";
import CreateDocumentPopupReducer from "../components/document/createdocument/create-document-popup-slice"
import {DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME} from "../components/document/documentformselector/popup/documentform-selector-popup-slice";
import DocumentFormSelectorPopupReducer from "../components/document/documentformselector/popup/documentform-selector-popup-slice"

export default configureStore({
    reducer: combineReducers({
        [CREATE_TASK_POPUP_SLICE_NAME]: CreateTaskPopupReducer,
        [EDIT_TASK_POPUP_SLICE_NAME]: EditTaskPopupReducer,
        [TASK_VIEWER_SLICE_NAME]: ViewTaskReducer,
        [CREATE_DOCUMENT_POPUP_SLICE_NAME]: CreateDocumentPopupReducer,
        [DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME]: DocumentFormSelectorPopupReducer
    })
})