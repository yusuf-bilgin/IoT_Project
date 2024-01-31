// axiosInterceptor.js
import axios from 'axios';
import { LOGIN_URL } from './constants';

const instance = axios.create();

// Add a request interceptor
instance.interceptors.request.use(
  (config) => {
    // Check if the request is not a login request
    if (config.url !== LOGIN_URL) {
      // Add the JWT Bearer token from local storage to the Authorization header
      const token = localStorage.getItem('jwt');
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default instance;
