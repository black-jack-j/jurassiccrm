import {createSlice} from "@reduxjs/toolkit";

export const NEW_USER_POPUP_SLICE_NAME = 'new-user-popup'

export const NewUserPopupSlice = createSlice({
    name: NEW_USER_POPUP_SLICE_NAME,
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

export const {open, close, toggle} = NewUserPopupSlice.actions

export default NewUserPopupSlice.reducer

export const selectNewUserFormPopupOpened = state => state[NEW_USER_POPUP_SLICE_NAME].open