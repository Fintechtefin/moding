import { axiosApi } from "@/util/commons";
import { Movie, MovieRanking } from "@util/types/movieType";

const url = "/fundings";

const api = axiosApi();

async function getTopTen(status: string) {
  console.log("top10");
  const { data } = await api.get(`${url}/?status=${status}`);
  console.log(data);
  return data;
}

async function postSurvey(movieId: string, surveyData: Object) {
  await api.post(`${url}/${movieId}/attendance`, surveyData);
  console.log("보냄");
}

export { getTopTen, postSurvey };
