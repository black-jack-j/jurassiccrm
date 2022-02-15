import {createSlice} from "@reduxjs/toolkit";

export const NEW_GROUP_POPUP_SLICE_NAME = 'new-group-popup'

export const NewGroupPopupSlice = createSlice({
    name: NEW_GROUP_POPUP_SLICE_NAME,
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

export const {open, close, toggle} = NewGroupPopupSlice.actions

export default NewGroupPopupSlice.reducer

export const selectNewGroupFormPopupOpened = state => state[NEW_GROUP_POPUP_SLICE_NAME].open