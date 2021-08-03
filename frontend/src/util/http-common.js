import axios from 'axios';
// axios 객체 생성
export default axios.create({
    baseURL: 'http://13.125.119.76:8080/',
    headers: {
        'Content-type': 'application/json',
    },
});