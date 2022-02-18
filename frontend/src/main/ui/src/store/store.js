import {combineReducers, configureStore} from "@reduxjs/toolkit";
import CreateTaskPopupReducer, {CREATE_TASK_POPUP_SLICE_NAME} from "../components/task/form/popup/create-task-popup-slice";
import EditTaskPopupReducer, {EDIT_TASK_POPUP_SLICE_NAME} from "../components/task/editor/edit-task-popup-slice";
import ViewTaskReducer, {TASK_VIEWER_SLICE_NAME} from "../components/task/viewer/viewer-slice";
import CreateDocumentPopupReducer, {CREATE_DOCUMENT_POPUP_SLICE_NAME} from "../components/create-document-form-popup/create-document-form-popup-slice";
import DocumentFormSelectorPopupReducer, {DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME} from "../components/document-form-selector-popup/documentform-selector-popup-slice";
import NewGroupPopupReducer, {NEW_GROUP_POPUP_SLICE_NAME} from "../components/create-group-form-popup/create-group-form-popup.slice";
import UpdateGroupPopupReducer, {UPDATE_GROUP_POPUP_SLICE_NAME} from "../components/update-group-form-popup/update-group-form-popup.slice";
import UpdateDocumentPopupReducer, {UPDATE_DOCUMENT_POPUP_SLICE_NAME} from "../components/update-document-form-popup/update-document-form-popup-slice";

export default configureStore({
    reducer: combineReducers({
        [CREATE_TASK_POPUP_SLICE_NAME]: CreateTaskPopupReducer,
        [EDIT_TASK_POPUP_SLICE_NAME]: EditTaskPopupReducer,
        [TASK_VIEWER_SLICE_NAME]: ViewTaskReducer,
        [CREATE_DOCUMENT_POPUP_SLICE_NAME]: CreateDocumentPopupReducer,
        [DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME]: DocumentFormSelectorPopupReducer,
        [NEW_GROUP_POPUP_SLICE_NAME]: NewGroupPopupReducer,
        [UPDATE_GROUP_POPUP_SLICE_NAME]: UpdateGroupPopupReducer,
        [UPDATE_DOCUMENT_POPUP_SLICE_NAME]: UpdateDocumentPopupReducer,
    })
})