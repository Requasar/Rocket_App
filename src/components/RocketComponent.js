import React, { useEffect, useState } from 'react'
import { createRocket, getRocket, updateRocket } from '../services/RocketService'
import { useNavigate, useParams } from 'react-router-dom'
import Modal from './Modal/Modal.js';
import { Validator } from './Validator'; 

const RocketComponent = () => {

   const [name, setName] = useState('')
   const [manufacturer, setManufacturer] = useState('')
   const [height, setHeight] = useState('')
   const [weight, setWeight] = useState('')
   const [missionType, setMissionType] = useState('')
   const [partsCount, setPartsCount] = useState('')

   const {id} = useParams(); 
   
   const [errors, setErrors] =useState({
      name: '',
      manufacturer: '',
      height: '',
      weight: '',
      missionType: '',
      partsCount: ''
   })

   const navigator = useNavigate();

   useEffect(() => {
      if(id){       
          getRocket(id).then((response) => {
            setName(response.data.name);
            setManufacturer(response.data.manufacturer);
            setHeight(response.data.height);
            setWeight(response.data.weight);
            setMissionType(response.data.missionType);
            setPartsCount(response.data.partsCount);
          }).catch(error => {
          console.error(error);
          })
      }
    },[id])

    const handleName = (event) => setName(event.target.value); 
    
    const handleManufacturer= (event) => setManufacturer(event.target.value); 
    
    const handleHeight = (event) => setHeight(event.target.value); 

    const handleWeight = (event) => setWeight(event.target.value);

    const handleMissionType = (event) => setMissionType(event.target.value);

    const handlePartsCount = (event) => setPartsCount(event.target.value);

    const [modalOpen, setModalOpen] = useState(false);

    const toggleModal = () => {
      setModalOpen(!modalOpen);
  };

    function saveOrUpdateRocket(e){
      e.preventDefault();

      if(validateForm()){
        const rocket = {name, manufacturer, height, weight, missionType, partsCount}
        console.log(rocket)

        if(id){
          updateRocket(id, rocket).then((response) => {
            console.log(response.data);
            toggleModal();
            setTimeout(() => navigator('/rocket'), 1500);
          })
        }else{
          createRocket(rocket).then((response) => {
            console.log(response.data);
            toggleModal(); 
            setTimeout(() => navigator('/rocket'), 1500);
          }).catch(error => {
            console.error(error);
          })
        }
      }
    }
    

    function validateForm(){
      let valid = true;

      const errorsCopy = {...errors}
      
      if(name.trim()){
        errorsCopy.name = '';
      } else {
        errorsCopy.name = 'Name is required';
        valid = false;
      }

      if(manufacturer.trim()){
        errorsCopy.manufacturer = '';
      } else {
        errorsCopy.manufacturer = 'Manufacturer is required';
        valid = false;
      }

      if (height.trim()) {
        
        if (!Validator.isValidNumber(height.trim())) {
          errorsCopy.height = 'Height must be a valid number';
          valid = false;
        } else {
          errorsCopy.height = ''; 
        }
      } else {
        errorsCopy.height = 'Height is required';
        valid = false;
      }
      
      if (weight.trim()) {
        
        if (!Validator.isValidNumber(weight.trim())) {
          errorsCopy.weight = 'Weight must be a valid number';
          valid = false;
        } else {
          errorsCopy.weight = ''; 
        }
      } else {
        errorsCopy.weight = 'Weight is required';
        valid = false;
      }

      if(missionType.trim()){
        errorsCopy.missionType = '';
      } else {
        errorsCopy.missionType = 'Type of mission is required';
        valid = false;
      }

      if (partsCount.trim()) {
        
        if (!Validator.isValidInt(partsCount.trim())) {
          errorsCopy.partsCount = 'Number of part must be a valid number';
          valid = false;
        } else {
          errorsCopy.partsCount = ''; 
        }
      } else {
        errorsCopy.partsCount = 'Number of part is required';
        valid = false;
      }

      setErrors(errorsCopy);
      return valid;
    }

    function pageTitle(){
      const titleStyle = {
        marginTop: '40px', 
      };
      if(id){
        return <h2 className='text-center'>Update Rocket</h2>
      } else {
        return <h2 className='text-center'>Add Rocket</h2>
      }
    }

    return (
      <div className='container'>
        <br />
        <div className='row'>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
            {pageTitle()}
            <div className='card-body'>
              <form>
                <div className='form-group mb-2'>
                  <label className='form-label'> Name: </label>
                  <input
                    type='text'
                    placeholder='Enter Name'
                    name='name'
                    value={name}
                    className={`form-control ${errors.name ? 'is-invalid' : ''}`}
                    onChange={handleName}
                  />
                  {errors.name && <div className='invalid-feedback'>{errors.name}</div>}
                </div>
    
                <div className='form-group mb-2'>
                  <label className='form-label'> Manufacturer: </label>
                  <input
                    type='text'
                    placeholder='Enter Manufacturer'
                    name='manufacturer'
                    value={manufacturer}
                    className={`form-control ${errors.manufacturer ? 'is-invalid' : ''}`}
                    onChange={handleManufacturer}
                  />
                  {errors.manufacturer && <div className='invalid-feedback'>{errors.manufacturer}</div>}
                </div>
    
                <div className='form-group mb-2'>
                  <label className='form-label'> Height: </label>
                  <input
                    type='text'
                    placeholder='Enter Height'
                    name='height'
                    value={height}
                    className={`form-control ${errors.height ? 'is-invalid' : ''}`}
                    onChange={handleHeight}
                  />
                  {errors.height && <div className='invalid-feedback'>{errors.height}</div>}
                </div>
    
                <div className='form-group mb-2'>
                  <label className='form-label'> Weight: </label>
                  <input
                    type='text'
                    placeholder='Enter Weight'
                    name='weight'
                    value={weight}
                    className={`form-control ${errors.weight ? 'is-invalid' : ''}`}
                    onChange={handleWeight}
                  />
                  {errors.weight && <div className='invalid-feedback'>{errors.weight}</div>}
                </div>
    
                <div className='form-group mb-2'>
                  <label className='form-label'> Mission type: </label>
                  <select
                    name='missionType'
                    value={missionType}
                    className={`form-control ${errors.missionType ? 'is-invalid' : ''}`}
                    onChange={handleMissionType}
                  >
                    <option value='' disabled>Select Mission Type</option>
                    <option value='Discovery Mission'>Discovery Mission</option>
                    <option value='Scientific Mission'>Scientific Mission</option>
                    <option value='Cargo and Resupply Mission'>Cargo and Resupply Mission</option>
                    <option value='Defense and Security Missions'>Defense and Security Missions</option>
                    <option value='Communication Mission'>Communication Mission</option>
                    <option value='Technology Demonstration Mission'>Technology Demonstration Mission</option>
                  </select>
                  {errors.missionType && <div className='invalid-feedback'>{errors.missionType}</div>}
                </div>
    
                <div className='form-group mb-2'>
                  <label className='form-label'> Number Of Parts: </label>
                  <input
                    type='text'
                    placeholder='Enter Number of Parts'
                    name='partsCount'
                    value={partsCount}
                    className={`form-control ${errors.partsCount ? 'is-invalid' : ''}`}
                    onChange={handlePartsCount}
                  />
                  {errors.partsCount && <div className='invalid-feedback'>{errors.partsCount}</div>}
                </div>
    
                <button className='btn btn-success' onClick={saveOrUpdateRocket}>Save</button>
              </form>
            </div>
          </div>
        </div>  
        {/* Modal component */}
        {modalOpen && (
          <Modal isOpen={modalOpen} toggleModal={toggleModal}>
            Operation Successful!
          </Modal>
        )}
      </div>
    );
};

export default RocketComponent