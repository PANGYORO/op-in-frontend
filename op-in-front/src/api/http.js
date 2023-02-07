import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://i8c211.p.ssafy.io:5001/", //import.meta.env.VITE_API_URL, 
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
