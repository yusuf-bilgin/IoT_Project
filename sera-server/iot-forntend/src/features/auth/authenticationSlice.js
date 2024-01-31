import { createSlice, createAsyncThunk } from "@reduxjs/toolkit"
import { login } from "../../api-consumer/api-auth"

export const loginAsync = createAsyncThunk(
    'auth/login',
    async ({username, password}) => {
        const response = await login(username, password);
        console.log('Login response:', response);
        return response;
    }
);

const token = () => localStorage.getItem('jwt');

const initialState = {
    isLogin: token()? true : false,
    jwtToken: token()? token() : null,
    status: 'idle',
    error: null,
}

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        logout: (state) => {
            state.isLogin = false;
            state.jwtToken = null;
        },
        clearError(state) {
            state.error = null;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(loginAsync.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(loginAsync.fulfilled, (state, action) => {
                state.status = 'idle';
                state.isLogin = true;
                state.jwtToken = action.payload.jwt;
            })
            .addCase(loginAsync.rejected, (state, action) => {
                state.status = 'idle';
                state.error = action.error.message;
            })
    }
});

export const { logout, clearError } = authSlice.actions;
export default authSlice.reducer;