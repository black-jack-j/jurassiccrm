import {createSlice} from "@reduxjs/toolkit";

export const USER_SLICE_NAME = 'user'

export const UserSlice = createSlice({
    name: USER_SLICE_NAME,
    initialState: {
        current: null,
        users: []
    },
    reducers: {
        updateCurrent: (state, action) => {
            state.current = action.payload
        },
        updateUsers: (state, action) => {
            state.users = action.payload
        },
        updateUser: (state, action) => {
            const index = state.users.map(({id}) => id).indexOf(action.payload.id)
            if (index !== -1) {
                state.users = [...state.users.slice(0, index), action.payload, ...state.users.slice(index+1)]
            } else {
                state.users = [...state.users, action.payload]
            }
        },
        addUser: (state, action) => {
            state.users = [...state.users, action.payload]
        },
        refresh: (state) => {
            state.users = [...state.users]
        }
    }
})

export const {updateCurrent, updateUsers, updateUser, addUser, refresh} = UserSlice.actions

export default UserSlice.reducer

export const selectCurrentUser = state => state[USER_SLICE_NAME].current
export const selectUsers = state => state[USER_SLICE_NAME].users