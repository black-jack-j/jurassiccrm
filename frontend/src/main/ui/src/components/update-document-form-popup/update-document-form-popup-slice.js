import {createSlice} from "@reduxjs/toolkit";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";

export const UPDATE_DOCUMENT_POPUP_SLICE_NAME = 'update-document-popup'

export const UpdateDocumentFormPopupSlice = createSlice({
    name: UPDATE_DOCUMENT_POPUP_SLICE_NAME,
    initialState: {
        open: false,
        documentType: DocumentTypeEnum.DinosaurPassport,
        id: null
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
        updateDocument: (state, action) => {
            state.open = true
            state.documentType = action.payload.documentType
            state.id = action.payload.id
        }
    }
})

export const {open, close, toggle, updateDocument} = UpdateDocumentFormPopupSlice.actions

export default UpdateDocumentFormPopupSlice.reducer

export const selectDocumentUpdaterPopupState = state => state[UPDATE_DOCUMENT_POPUP_SLICE_NAME]