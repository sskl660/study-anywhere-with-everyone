import axios from 'axios';

// axios 객체 생성
export default axios.create({
    //baseURL: "http://localhost:9999/vuews/book",
    // baseURL: 'http://localhost:8097/hrmboot/api/employee',
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-type': 'application/json',
    },
});
