import {createSlice} from "@reduxjs/toolkit";

export const DOCUMENT_VIEWER_SLICE_NAME = 'document-viewer'

export const DocumentViewerSlice = createSlice({
    name: DOCUMENT_VIEWER_SLICE_NAME,
    initialState: {
        documentType: null,
        id: null
    },
    reducers: {
        reset: state => {
            state.documentType = null
            state.id = null
        },
        viewDocument: (state, action) => {
            state.documentType = action.payload.documentType
            state.id = action.payload.id
        }
    }
})

export const {reset, viewDocument} = DocumentViewerSlice.actions

export default DocumentViewerSlice.reducer

export const selectDocumentViewerState = state => state[DOCUMENT_VIEWER_SLICE_NAME]