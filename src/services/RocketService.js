import axios from "axios";

const RESTE_API_BASE_URL ='http://localhost:8080/api/rocket';

export const listRocket = () => axios.get(RESTE_API_BASE_URL);

export const createRocket = (rocket) => axios.post(RESTE_API_BASE_URL, rocket);

export const getRocket = (rocketId) => axios.get(RESTE_API_BASE_URL + '/' + rocketId);

export const updateRocket = (rocketId, rocket) => axios.put(RESTE_API_BASE_URL + '/' + rocketId, rocket);

export const deleteRocket = (rocketId) => axios.delete(RESTE_API_BASE_URL + '/' + rocketId);