import {createSlice} from "@reduxjs/toolkit";

export const CREATE_TASK_POPUP_SLICE_NAME = 'create-task-popup'

export const CreateTaskPopupSlice = createSlice({
    name: CREATE_TASK_POPUP_SLICE_NAME,
    initialState: {
        open: false
    },
    reducers: {
        open: state => {
            state.open = true
        },
        close: state => {
            state.open = false
        },
        toggle: state => {
            state.open = !state.open
        }
    }
})

export const {open, close, toggle} = CreateTaskPopupSlice.actions

export default CreateTaskPopupSlice.reducer

export const selectTaskCreatorPopupOpened = state => state[CREATE_TASK_POPUP_SLICE_NAME].open