import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { predictImage } from "../../api-consumer/api-calls";

export const predictImageHealth = createAsyncThunk(
    "ai/predictImageHealth",
    async (formData) => {
        const response = await predictImage(formData);
        return response;
    }
);


const initialState = {
    result: null,
    loading: 'idle',
    error: null,
};

const aiSlice = createSlice({
    name: "ai",
    initialState,
    reducers: {
        clearError(state) {
            state.error = null;
        },

        clearData(state) {
            state.result = null;
            state.loading = "idle";
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(predictImageHealth.pending, (state) => {
                state.loading = "loading";
            })
            .addCase(predictImageHealth.fulfilled, (state, action) => {
                state.loading = "idle";
                state.result = action.payload;
            })
            .addCase(predictImageHealth.rejected, (state, action) => {
                state.loading = "idle";
                state.error = action.error.message;
            });
    },
});

export const { clearError, clearData } = aiSlice.actions;
export default aiSlice.reducer;