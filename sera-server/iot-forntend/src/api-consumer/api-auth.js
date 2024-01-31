import axios from './axiosInterceptor'; // Import the interceptor instance
import { LOGIN_URL } from './constants';

const login = async (username, password) => {
  try {
    const response = await axios.post(LOGIN_URL, {
      username: username,
      password: password
    });

    // Handle successful login response
    handleLogin(response);

    return response.data;
  } catch (error) {
    console.error('Error during login:', error);
    if (error.response.status === 400) {
      throw new Error('Invalid credentials');
    }
    throw error; // Propagate the error for handling in the calling component
  }
};

const handleLogin = (response) => {
  console.log('Saving JWT token...');
  const jwtToken = response.data.jwt;
  // Store the JWT token in local storage
  localStorage.setItem('jwt', jwtToken);
};

export { login };