import axios from "axios";

const RESTE_API_BASE_URL ='http://localhost:8080/api/rocket';


const getToken = () => localStorage.getItem('token');

export const listRocket = () => {const token = getToken(); return axios.get(RESTE_API_BASE_URL, {headers: {
    'Authorization': `Bearer ${token}`
  }
});};

export const createRocket = (rocket) => {const token = getToken(); return axios.post(RESTE_API_BASE_URL, rocket, {headers: {
    'Authorization': `Bearer ${token}`
  }
});};

export const getRocket = (rocketId) => {const token = getToken(); return axios.get(RESTE_API_BASE_URL + '/' + rocketId, {headers: {
    'Authorization': `Bearer ${token}`
  }
});};

export const updateRocket = (rocketId, rocket) => {const token = getToken(); return axios.put(RESTE_API_BASE_URL + '/' + rocketId, rocket, {headers: {
    'Authorization': `Bearer ${token}`
  }
});};

export const deleteRocket = (rocketId) => {const token = getToken(); return axios.delete(RESTE_API_BASE_URL + '/' + rocketId, {headers: {
    'Authorization': `Bearer ${token}`
  }
});};