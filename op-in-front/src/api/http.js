import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://3.36.7.48:9999/",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
