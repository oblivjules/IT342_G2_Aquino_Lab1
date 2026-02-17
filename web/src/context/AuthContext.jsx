import React, { createContext, useState, useEffect } from 'react';
import { authAPI } from '../api/authAPI';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            fetchCurrentUser();
        } else {
            setLoading(false);
        }
    }, []);

    const fetchCurrentUser = async () => {
        try {
            const response = await authAPI.getCurrentUser();
            setUser(response.data.data);
        } catch (error) {
            localStorage.removeItem('token');
        } finally {
            setLoading(false);
        }
    };

    const login = async (identifier, password) => {
        const response = await authAPI.login({ identifier, password });
        localStorage.setItem('token', response.data.data.token);
        await fetchCurrentUser();
        return response.data.data;
    };

    const register = async (username, email, password, firstName, lastName) => {
        await authAPI.register({ username, email, password, firstName, lastName });
    };

    const logout = async () => {
        try {
            await authAPI.logout();
        } catch (error) {
            // Ignore logout API errors and clear local state.
        }
        localStorage.removeItem('token');
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, loading, login, register, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
