import {configureStore} from '@reduxjs/toolkit';
import authReducer from '../features/auth/authenticationSlice';
import sensorReducer from '../features/sensor/sensorSlice';
import aiReducer from '../features/ai/aiSlice';

export const store = configureStore({
    reducer: {
        auth: authReducer,
        sensor: sensorReducer,
        ai: aiReducer,
    }
});