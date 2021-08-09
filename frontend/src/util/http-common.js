import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'http://13.125.119.76:8080', // 백엔드 서버
  // baseURL: 'http://localhost:8080', // 로컬 서버
  headers: {
    'Content-type': 'application/json',
  },
});

// 채팅 URL
export const chatURL = 'http://13.125.119.76:8080/ssazip'; // 백엔드 서버
// export const chatURL = 'http://localhost:8080/ssazip'; // 로컬 서버
