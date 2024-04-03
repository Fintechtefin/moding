import axios from "axios";

// const BASE_URL = "http://localhost:8084";
// const BASE_URL = "http://172.30.1.16:5174";
// const BASE_URL = import.meta.env.VITE_BASE_URL;
const BASE_URL = "https://j10c204.p.ssafy.io";

const axiosApi = () => {
  const instance = axios.create({
    baseURL: `${BASE_URL}/api`,
  });

  // const token = localStorage.getItem("jwt");
  const token =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxb19Hb280a0JpSXMzQVNMc2QwalBfdEc4S29pMVczMVZ0V2pvTUh6RWxvIiwic3ViIjoiYWNjZXNzVG9rZW4iLCJpYXQiOjE3MTIxNjM5NTEsImV4cCI6MTcxNDc1NTk1MX0.1UWs9opZWEs8tNaZsiC-jkIxn63BZkR2pxg3UuLOx4s";

  instance.defaults.headers.common["Authorization"] = token;
  instance.defaults.headers.post["Content-Type"] = "application/json";
  instance.defaults.headers.put["Content-Type"] = "application/json";
  instance.defaults.headers.delete["Content-Type"] = "application/json";

  return instance;
};

const axiosFileApi = () => {
  const instanceFile = axios.create({
    baseURL: `${BASE_URL}/api`,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });

  return instanceFile;
};

export { axiosApi, axiosFileApi };
