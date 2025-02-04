import { axiosApi } from "@/util/commons";

const url = "/fundings";

const api = axiosApi();

async function getTopTen(status: string) {
  console.log("top10");
  const { data } = await api.get(`${url}?status=${status}`);
  console.log(data);
  return data;
}

async function postSurvey(movieId: string, surveyData: Object) {
  await api.post(`${url}/${movieId}/attendance`, surveyData);
  console.log("보냄");
}

async function getFundingInfo(movieId: string) {
  const { data } = await api.get(`${url}/open/${movieId}`);
  console.log(data);
  return data;
}

async function getFundingResult(fundingId: number) {
  console.log(fundingId);
  const result = await api.get(`${url}/${fundingId}/participation`);
  return result.data;
}

export { getTopTen, postSurvey, getFundingInfo, getFundingResult };
