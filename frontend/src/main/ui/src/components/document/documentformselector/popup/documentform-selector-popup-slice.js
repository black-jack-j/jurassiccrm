import {createSlice} from "@reduxjs/toolkit";

export const DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME = 'documentform-selector-popup'

export const DocumentFormSelectorPopupSlice = createSlice({
    name: DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME,
    initialState: {
        open: false,
    },
    reducers: {
        open: (state) => {
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

export const {open, close, toggle} = DocumentFormSelectorPopupSlice.actions

export default DocumentFormSelectorPopupSlice.reducer

export const selectDocumentFormSelectorPopupOpen = state => state[DOCUMENTFORM_SELECTOR_POPUP_SLICE_NAME].open