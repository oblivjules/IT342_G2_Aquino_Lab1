import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const axiosInstance = axios.create({
    baseURL: API_BASE_URL,
});

axiosInstance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export const authAPI = {
    register: (data) => axiosInstance.post('/auth/register', data),
    login: (data) => axiosInstance.post('/auth/login', data),
    getCurrentUser: () => axiosInstance.get('/user/me'),
};

export default axiosInstance;
