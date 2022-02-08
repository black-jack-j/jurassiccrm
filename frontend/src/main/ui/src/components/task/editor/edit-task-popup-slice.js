import {createSlice} from "@reduxjs/toolkit";

export const EDIT_TASK_POPUP_SLICE_NAME = 'edit-task-popup'

export const EditTaskPopupSlice = createSlice({
    name: EDIT_TASK_POPUP_SLICE_NAME,
    initialState: {
        open: false,
        task: {}
    },
    reducers: {
        close: state => {
            state.open = false
        },
        open: state => {
            state.open = true
        },
        editTask: (state, action) => {
            state.open = true
            state.task = action.payload
        }
    }
})

export const {editTask, close, open} = EditTaskPopupSlice.actions

export default EditTaskPopupSlice.reducer

export const selectTaskEditorPopupState = state => state[EDIT_TASK_POPUP_SLICE_NAME]