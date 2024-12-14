import React, { useEffect, useState } from 'react'
import { createRocket, getRocket, updateRocket } from '../services/RocketService'
import { useNavigate, useParams } from 'react-router-dom'

const RocketComponent = () => {

   const [name, setName] = useState('')
   const [manufacturer, setManufacturer] = useState('')
   const [height, setHeight] = useState('')
   const [weight, setWeight] = useState('')
   const [missionType, setMissionType] = useState('')
   const [partsCount, setPartsCount] = useState('')

   const {id} = useParams(); 
   
   const [errors, setErrors] =useState({ //I set statehook for empty conditions of any input for adding new rocket but we need fonctions for checking them
      name: '',
      manufacturer: '',
      height: '',
      weight: '',
      missionType: '',
      partsCount: ''
   })

   const navigator = useNavigate();

   useEffect(() => { //we set data to our state variables with this method
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

    const handleName = (event) => setName(event.target.value); //we set name to our const name state variable at upper side 
    
    const handleManufacturer= (event) => setManufacturer(event.target.value); //we set manufacturer to our const manufacturer state variable at upper side
 
    const handleHeight = (event) => setHeight(event.target.value); //we set height to our const height state variable at upper side

    const handleWeight = (event) => setWeight(event.target.value);

    const handleMissionType = (event) => setMissionType(event.target.value);

    const handlePartsCount = (event) => setPartsCount(event.target.value);


    function saveOrUpdateRocket(e){
      e.preventDefault();

      if(validateForm()){ //if none of the parts empty this function will work
        const rocket = {name, manufacturer, height, weight, missionType, partsCount}
        console.log(rocket)

        if(id){
          updateRocket(id, rocket).then((response) => {
            console.log(response.data);
            navigator('/rocket');
          })
        }else{
          createRocket(rocket).then((response) => {
            console.log(response.data);
            navigator('/rocket');
          }).catch(error => {
            console.error(error);
          })
        }
      }
    }
    

    function validateForm(){
      let valid = true;

      const errorsCopy = {... errors} //... means spread operator, it copies the object and it is state variable it coppies errors object into here
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
      if(height.trim()){
        errorsCopy.height = '';
      } else {
        errorsCopy.height = 'Height is required';
        valid = false;
      }
      if(weight.trim()){
        errorsCopy.weight = '';
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
      if(partsCount.trim()){
        errorsCopy.partsCount = '';
      } else {
        errorsCopy.partsCount = 'Number of parts is required';
        valid = false;
      }
      setErrors(errorsCopy);
      return valid;
    }

    function pageTitle(){
      if(id){
        return <h2 className='text-center'>Update Rocket</h2>
      } else {
        return <h2 className='text-center'>Add Rocket</h2>
      }
    }

  return (
    <div className='container'>
      <br></br>
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
                  className={`form-control ${errors.name ? 'is-invalid' : ''}`} //with bootstrap we can see the empty parts with red color
                  onChange={handleName}
                >
                </input>
                {errors.name && <div className='invalid-feedback'>{errors.name}</div>} {/*if there is an error it will show the error message*/}
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
                >
                </input>
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
                >
                </input>
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
                >
                </input>
                {errors.weight && <div className='invalid-feedback'>{errors.weight}</div>} 
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'> missionType: </label>
                <input
                  type='text'
                  placeholder='Enter Mission Type'
                  name='missionType'
                  value={missionType}
                  className={`form-control ${errors.missionType ? 'is-invalid' : ''}`}
                  onChange={handleMissionType}
                >
                </input>
                {errors.missionType && <div className='invalid-feedback'>{errors.missionType}</div>} 
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'> partsCount: </label>
                <input
                  type='text'
                  placeholder='Enter Number of Parts'
                  name='partsCount'
                  value={partsCount}
                  className={`form-control ${errors.partsCount ? 'is-invalid' : ''}`}
                  onChange={handlePartsCount}
                >
                </input>
                {errors.partsCount && <div className='invalid-feedback'>{errors.partsCount}</div>} 
              </div>

              <button className='btn btn-success' onClick={saveOrUpdateRocket}> Save</button>
            </form>
          </div>
        </div>
      </div>  
    </div>
  );
};

export default RocketComponent