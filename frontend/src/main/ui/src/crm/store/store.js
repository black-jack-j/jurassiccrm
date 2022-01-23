import {combineReducers, configureStore} from "@reduxjs/toolkit";
import {CREATE_TASK_POPUP_SLICE_NAME} from "../task/form/create-task-popup-slice";
import CreateTaskPopupReducer from "../task/form/create-task-popup-slice"
import {EDIT_TASK_POPUP_SLICE_NAME} from "../task/editor/edit-task-popup-slice";
import EditTaskPopupReducer from "../task/editor/edit-task-popup-slice"
import {TASK_VIEWER_SLICE_NAME} from "../task/viewer/viewer-slice";
import ViewTaskReducer from "../task/viewer/viewer-slice"

export default configureStore({
    reducer: combineReducers({
        [CREATE_TASK_POPUP_SLICE_NAME]: CreateTaskPopupReducer,
        [EDIT_TASK_POPUP_SLICE_NAME]: EditTaskPopupReducer,
        [TASK_VIEWER_SLICE_NAME]: ViewTaskReducer
    })
})