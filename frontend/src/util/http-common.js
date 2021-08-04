import axios from 'axios';

// axios 객체 생성
export default axios.create({
     baseURL: 'http://13.125.119.76:8080', // 백엔드 서버
    //baseURL: 'http://localhost:8080', // 로컬 서버
    headers: {
        'Content-type': 'application/json',
    },
});
