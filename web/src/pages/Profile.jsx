import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import './Profile.css';

const Profile = () => {
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();

    return (
        <div className="profile-container">
            <div className="profile-header">
                <button onClick={() => navigate('/dashboard')} className="btn-back">
                    ← Back to Dashboard
                </button>
            </div>

            {user && (
                <div className="profile-card">
                    <h2>Profile Information</h2>
                    <div className="profile-section">
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

export default Profile;
