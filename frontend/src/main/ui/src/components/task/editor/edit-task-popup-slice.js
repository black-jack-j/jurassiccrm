import {createSlice} from "@reduxjs/toolkit";

export const EDIT_TASK_POPUP_SLICE_NAME = 'edit-task-popup'

export const EditTaskPopupSlice = createSlice({
    name: EDIT_TASK_POPUP_SLICE_NAME,
    initialState: {
        open: false,
        taskId: null,
        taskType: null
    },
    reducers: {
        close: state => {
            state.open = false
            state.taskId = null
            state.taskType = null
        },
        open: state => {
            state.open = true
        },
        editTask: (state, action) => {
            state.open = true
            state.taskId = action.payload.id
            state.taskType = action.payload.type
        }
    }
})

export const {editTask, close, open} = EditTaskPopupSlice.actions

export default EditTaskPopupSlice.reducer

export const selectTaskEditorPopupState = state => state[EDIT_TASK_POPUP_SLICE_NAME]