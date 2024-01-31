import { createSlice, createAsyncThunk } from "@reduxjs/toolkit"
import { getPagedSensorData } from "../../api-consumer/api-calls";


export const fetchSensorData = createAsyncThunk(
    "sensor/fetchSensorData",
    async () => {
        const response = await getPagedSensorData();
        return response;
    }
);

const initialState = {
    sensorData: [],
    status: 'idle',
    error: null,
};

const sensorSlice = createSlice({
    name: 'sensor',
    initialState,
    reducers: {
        clearError(state) {
            state.error = null;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchSensorData.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(fetchSensorData.fulfilled, (state, action) => {
                state.status = 'idle';
                state.sensorData = action.payload;
            })
            .addCase(fetchSensorData.rejected, (state, action) => {
                state.status = 'idle';
                state.error = action.error.message;
            })
    }
});

export const { clearError } = sensorSlice.actions;
export default sensorSlice.reducer;