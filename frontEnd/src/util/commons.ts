import axios from "axios";

// const BASE_URL = "http://localhost:8084";
// const BASE_URL = "http://172.30.1.16:5174";
const BASE_URL = import.meta.env.VITE_BASE_URL;

const axiosApi = () => {
  const instance = axios.create({
    baseURL: `${BASE_URL}/api`,
  });

  //const token = localStorage.getItem("jwt");

  instance.defaults.headers.common["Authorization"] =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxb19Hb280a0JpSXMzQVNMc2QwalBfdEc4S29pMVczMVZ0V2pvTUh6RWxvIiwic3ViIjoiYWNjZXNzVG9rZW4iLCJpYXQiOjE3MTE4ODk4MDgsImV4cCI6MTcxNDQ4MTgwOH0.t3o1nGRSfKsjmfwXLjueaRqW9B4fF2Z-XhWzXUNqZMA";
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
