import React,{useEffect, useState} from 'react';
import "./Modal.css";

export default function Modal({isOpen, toggleModal}) {
    const[modal, setModal] = useState(false);

    return (
        <>
            {isOpen && (
                <div className="modal">
                    <div className="overlay" onClick={toggleModal}></div>
                    <div className='modal-content'>
                        <h2 className='center'>Successfull</h2>
                    </div>
                </div>
            )}
        </>
    );
}