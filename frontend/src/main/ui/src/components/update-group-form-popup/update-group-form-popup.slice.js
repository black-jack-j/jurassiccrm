import {createSlice} from "@reduxjs/toolkit";

export const UPDATE_GROUP_POPUP_SLICE_NAME = 'update-group-popup'

export const UpdateGroupPopupSlice = createSlice({
    name: UPDATE_GROUP_POPUP_SLICE_NAME,
    initialState: {
        groupId: null,
        open: false
    },
    reducers: {
        select: (state, action) => {
            state.groupId = action.payload
            state.open = true
        },
        open: state => {
            state.open = true
        },
        close: state => {
            state.open = false
            state.groupId = null
        },
        toggle: state => {
            state.open = !state.open
        },
        reset: state => {
            state.groupId = null
        }
    }
})

export const {open, close, toggle, select, reset} = UpdateGroupPopupSlice.actions

export default UpdateGroupPopupSlice.reducer

export const selectUpdateGroupFormPopupOpened = state => state[UPDATE_GROUP_POPUP_SLICE_NAME].open
export const selectUpdateGroupFormPopupGroupId = state => state[UPDATE_GROUP_POPUP_SLICE_NAME].groupId