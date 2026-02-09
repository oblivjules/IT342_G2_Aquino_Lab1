import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <div className="dashboard-container">
            <div className="dashboard-header">
                <h1>Dashboard</h1>
                <button onClick={handleLogout} className="btn-logout">Logout</button>
            </div>

            {user && (
                <div className="profile-card">
                    <h2>Profile Information</h2>
                    <div className="profile-section">
                        <div className="profile-item">
                            <label>User ID:</label>
                            <span>{user.id}</span>
                        </div>
                        <div className="profile-item">
                            <label>Username:</label>
                            <span>{user.username}</span>
                        </div>
                        <div className="profile-item">
                            <label>Email:</label>
                            <span>{user.email}</span>
                        </div>
                        <div className="profile-item">
                            <label>First Name:</label>
                            <span>{user.firstName || 'N/A'}</span>
                        </div>
                        <div className="profile-item">
                            <label>Last Name:</label>
                            <span>{user.lastName || 'N/A'}</span>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Dashboard;
