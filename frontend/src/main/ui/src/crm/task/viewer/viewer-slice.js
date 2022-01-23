import {createSlice} from "@reduxjs/toolkit";

export const TASK_VIEWER_SLICE_NAME = 'task-viewer'

export const ViewTaskSlice = createSlice({
    name: TASK_VIEWER_SLICE_NAME,
    initialState: {
        task: undefined
    },
    reducers: {
        reset: state => {
            state.task = undefined
        },
        viewTask: (state, action) => {
            state.task = action.payload
        }
    }
})

export const {reset, viewTask} = ViewTaskSlice.actions

export default ViewTaskSlice.reducer

export const selectTaskViewerTask = state => state[TASK_VIEWER_SLICE_NAME].task