import { axiosApi } from "@/util/commons";

const url = "/user";

const api = axiosApi();

async function getCode(social: string, code: string) {
  try {
    const { data } = await api.get(`${url}/auth/${social}?code=${code}`);
    console.log(data);
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function getUser() {
  try {
    const { data } = await api.get(`/users`);
    return data;
  } catch (error) {
    console.log(error);
  }
}

export { getCode, getUser };
