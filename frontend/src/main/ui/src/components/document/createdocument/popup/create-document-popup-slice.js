import {createSlice} from "@reduxjs/toolkit";
import {DINOSAUR_PASSPORT} from "../subform/createdocument-subform";

export const CREATE_DOCUMENT_POPUP_SLICE_NAME = 'create-document-popup'

export const CreateDocumentPopupSlice = createSlice({
    name: CREATE_DOCUMENT_POPUP_SLICE_NAME,
    initialState: {
        open: false,
        documentType: DINOSAUR_PASSPORT
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
        },
        createDocument: (state, action) => {
            state.open = true
            state.documentType = action.payload
        }
    }
})

export const {open, close, toggle, createDocument} = CreateDocumentPopupSlice.actions

export default CreateDocumentPopupSlice.reducer

export const selectDocumentCreatorPopupState = state => state[CREATE_DOCUMENT_POPUP_SLICE_NAME]