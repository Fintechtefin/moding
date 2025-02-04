import { axiosApi } from "@/util/commons";
import { Movie, MovieRanking } from "@util/types/movieType";

// const url = "/fundings";
const url2 = "/fundings/movies";

const api = axiosApi();

async function getGenreList(genre: number, page: number, sort: string) {
  console.log("page");
  console.log(page);
  const { data } = await api.get(`${url2}?genre=${genre}&page=${page}&sort=${sort}`);
  return data;
}

async function getNowRanking(): Promise<MovieRanking[]> {
  console.log("dddd");
  const { data } = await api.get(`${url2}/popular`);
  console.log(data);
  return data;
}

async function getSearchMovie(word: string): Promise<Movie[]> {
  const { data } = await api.get(`${url2}/search?word=${word}`);
  console.log("search");
  console.log(data);
  return data;
}

async function getSearchDetail(id: string) {
  console.log("영화검색상세정보");
  const { data } = await api.get(`${url2}/search/${id}`);

  return data;
}

async function getMovieDetail(id: string) {
  console.log("영화상세정보");
  const { data } = await api.get(`${url2}/${id}`);
  return data;
}

export { getGenreList, getNowRanking, getSearchMovie, getSearchDetail, getMovieDetail };
