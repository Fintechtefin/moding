import { axiosApi } from "@/util/commons";
import { Movie, MovieRanking } from "@util/types/movieType";

// const url = "/fundings";
const url2 = "/fundings/movies";

const api = axiosApi();

async function getGenreList(genre: string, sort: string) {
  const { data } = await api.get(`${url2}?genre=${genre}&sort=${sort}&page=`);
  return data;
}

async function getNowRanking(time: number): Promise<MovieRanking[]> {
  console.log("dddd");
  const { data } = await api.get(`${url2}/popular?time=${time}`);
  console.log(data);
  return data;
}

async function getSearchMovie(word: string): Promise<Movie[]> {
  if (word.length == 0) return;
  const { data } = await api.get(`${url2}/search?word=${word}`);
  console.log("search");
  return data;
}

async function getSendLog(id: number) {
  const { data } = await api.get(`${url2}/search?${id}`);
  return data;
}

async function getMovieDetail(id: number) {
  const { data } = await api.get(`${url2}/${id}`);
  return data;
}

export { getGenreList, getNowRanking, getSearchMovie, getSendLog, getMovieDetail };
