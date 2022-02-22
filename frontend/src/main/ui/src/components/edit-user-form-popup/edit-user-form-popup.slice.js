import {createSlice} from "@reduxjs/toolkit";

export const EDIT_USER_POPUP_SLICE_NAME = 'edit-user-popup'

export const EditUserPopupSlice = createSlice({
    name: EDIT_USER_POPUP_SLICE_NAME,
    initialState: {
        userId: null,
        open: false
    },
    reducers: {
        select: (state, action) => {
            state.userId = action.payload
            state.open = true
        },
        open: state => {
            state.open = true
        },
        close: state => {
            state.open = false
            state.userId = null
        },
        toggle: state => {
            state.open = !state.open
        },
        reset: state => {
            state.userId = null
        }
    }
})

export const {open, close, toggle, select, reset} = EditUserPopupSlice.actions

export default EditUserPopupSlice.reducer

export const selectEditUserFormPopupOpened = state => state[EDIT_USER_POPUP_SLICE_NAME].open
export const selectEditUserFormPopupUserId = state => state[EDIT_USER_POPUP_SLICE_NAME].userId