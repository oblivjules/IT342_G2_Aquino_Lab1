import React from 'react';
import './LogoutModal.css';

const LogoutModal = ({ isOpen, onClose, onConfirm }) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                <h2>Confirm Logout</h2>
                <p>Are you sure you want to logout?</p>
                <div className="modal-actions">
                    <button onClick={onClose} className="btn-cancel">Cancel</button>
                    <button onClick={onConfirm} className="btn-confirm">Logout</button>
                </div>
            </div>
        </div>
    );
};

export default LogoutModal;
