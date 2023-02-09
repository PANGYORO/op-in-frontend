import axios from "axios";

axios.defaults.withCredentials = true;
// axios 객체 생성
export default axios.create({
  // baseURL: import.meta.env.VITE_API_URL,
  // baseURL: "http://i8c211.p.ssafy.io:5001/",
  baseURL: "http://127.0.0.1:5001/",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});


