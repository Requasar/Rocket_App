// Modal/Modal.js
import React from 'react';
import './Modal.css';

const Modal = ({ isOpen, toggleModal, children }) => {
  if (!isOpen) return null;

  return (
    <div className="modal">
      <div className="modal-content">
        <span className="close" onClick={toggleModal}>×</span>
        {children}
      </div>
    </div>
  );
};

export default Modal;
