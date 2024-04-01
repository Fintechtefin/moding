import { axiosApi } from "@/util/commons";

const url = "/users";

async function getCode(social: string, code: string) {
  try {
    const { data } = await axiosApi().get(
      `${url}/auth/login/${social}?code=${code}`
    );
    console.log(data);
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function getUser() {
  try {
    const { data } = await axiosApi().get(`${url}/user`);
    return data;
  } catch (error) {
    console.log(error);
  }
}

export { getCode, getUser };
