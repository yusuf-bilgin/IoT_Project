
import axios from './axiosInterceptor'; // Import the interceptor instance
import { PAGED_SENSOR_DATA_URL, PREDICT_URL } from './constants';



const getPagedSensorData = async (page = 0, size = 10) => {
  try {
    const response = await axios.get(`${PAGED_SENSOR_DATA_URL}?page=${page}&size=${size}&sort=createdDate,desc`);
    
    console.log('SENSOR response', response);
    // Extract and return only the content from the response
    return response.data.content;
  } catch (error) {
    console.error('Error fetching sensor data:', error);
    throw error; // Propagate the error for handling in the calling component
  }
};

const predictImage = async (formData) => {

  console.log('sending formData - PREDICT -', formData);

  return axios.post(PREDICT_URL, formData)
        .then(response => {
          // Handle success
          console.log('Response:', response.data);
          return response.data
        })
        .catch(error => {
          // Handle error
          console.error('Error:', error);
          throw error;
        });
};

export { getPagedSensorData, predictImage };