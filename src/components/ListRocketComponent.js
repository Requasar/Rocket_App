import React,{useEffect, useState} from 'react'
import { deleteRocket, listRocket } from '../services/RocketService'
import { useNavigate } from 'react-router-dom'

const ListRocketComponent = () => {

    const [rocket, setRocket] = useState([]) // state to store the rockets we can pass the initial values with useState hook

    const navigator = useNavigate();

    useEffect(() => {
        getAllRockets(); //we call the function to get all rockets
    },[])

    function getAllRockets(){
        listRocket().then((response) => {
            setRocket(response.data); //we get data from rocket's state variable
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewRocket(){
        navigator('/rocket/add-rocket'); //Navigate hook to navigate to the add-rocket page, new page So when button clicked function calls this part and 
                                  //We went into new page which is /add-rocket
    }

    function updateRocket(id){
        navigator(`/rocket/update-rocket/${id}`); //Navigate hook to navigate to the update-rocket page
    }

    function removeRocket(id){
        console.log(id); //to print id of the rocket
        deleteRocket(id).then((response) => {
            getAllRockets(); //we call the function to get all rockets
        }).catch(error => {
            console.error(error);
        })
    }

  return (
    <div className='container'>
        <h2 className='text-center'>
            List Rockets
        </h2>
        <button className='btn btn-primary mb-2'onClick={addNewRocket}>
            Add Rocket
        </button>

        <table className='table table-striped table-bordered'>
            <thead> {/* table columns*/}
                <tr> 
                    <th>ID</th>
                    <th>Name</th>
                    <th>Manufacturer</th>
                    <th>Height</th>
                    <th>Weight</th>
                    <th>Mission Type</th>
                    <th>Parts Count</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    rocket.map((rocket) => 
                            <tr key={rocket.id}>
                                <td>{rocket.id}</td>
                                <td>{rocket.name}</td>
                                <td>{rocket.manufacturer}</td>
                                <td>{rocket.height}</td>
                                <td>{rocket.weight}</td>
                                <td>{rocket.missionType}</td>
                                <td>{rocket.partsCount}</td>
                                <td>
                                    <button className='btn btn-info' onClick={()=> updateRocket(rocket.id)}>Update</button>
                                    <button className='btn btn-danger' onClick={() => removeRocket(rocket.id)}
                                        style={{marginLeft: '10px'}} //style to give margin to the button
                                    >Delete</button>
                                </td>
                            </tr>
                        )
                }
                <tr>

                </tr>
            </tbody>

        </table>
    </div>
  )
}

export default ListRocketComponent