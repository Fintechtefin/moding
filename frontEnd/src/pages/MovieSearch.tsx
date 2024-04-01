import React from "react";
import NoneNavHeader from "@components/NoneNavHeader";
import MovieListItem from "@components/movieList/MovieListItem";
import "@/assets/styles/movieList/MovieSearch.scss";
import { useState, useEffect } from "react";
import { Movie, MovieRanking } from "@util/types/movieType";
import { Link } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { getNowRanking, getSearchMovie } from "@api/movie";

const MovieSearch = () => {
  const today = new Date();
  const formatDate = `${today.getFullYear()}년 ${
    today.getMonth() + 1
  }월 ${today.getDate()}일`;
  const time = today.getHours() - 1;

  const [rankingData, setRankingData] = useState<MovieRanking[]>([]);

  const { data: rankingDataResult } = useQuery<MovieRanking[], Error>({
    queryKey: ["nowRanking"], // 쿼리 키를 지정합니다.
    queryFn: () => getNowRanking(), // getNowRanking 함수를 호출합니다.
  });

  const [keyword, setKeyword] = useState("");
  const [result, setResult] = useState("");
  const [searchResult, setSearchResult] = useState(true);

  const [searchData, setSearchData] = useState<Movie[]>([]);

  const { data: searchDataResult } = useQuery<Movie[], Error>({
    queryKey: ["searchResult", result], // 쿼리 키를 지정합니다.
    queryFn: () => {
      if (result.length === 0) {
        return Promise.resolve([]);
      } else {
        return getSearchMovie(result); // getNowRanking 함수를 호출합니다.
      }
    },
  });

  const searchKeyword = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      console.log(keyword);
      setResult(keyword);

      setSearchResult(false);
    }
  };

  // 데이터가 로드되면 상태에 저장합니다.
  useEffect(() => {
    if (rankingDataResult) {
      setRankingData(rankingDataResult);
      console.log(rankingDataResult);
    }
    if (searchDataResult) {
      setSearchData(searchDataResult);
    }
  }, [rankingDataResult, searchDataResult]);

  return (
    <div className="movie-search relative">
      <NoneNavHeader />
      <div className="absolute inline-block left-[50%] translate-x-[-50%] top-[1.4vh] w-[80%]">
        <input
          className="search-input w-[100%] h-[5vh] ml-3 p-3 bg-bgGray text-white "
          type="text"
          placeholder="영화 제목, 배우로 검색해보세요"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          onKeyDown={searchKeyword}
        />
      </div>
      <div className="p-5">
        {searchResult && (
          <div>
            <div className="flex items-end gap-4 mb-10">
              <div>실시간 인기 영화</div>
              <div className="text-[1.5vh] text-gray-500">
                {formatDate} {time}:00 기준
              </div>
            </div>
            <div className="grid grid-cols-2 gap-8 p-2">
              {rankingData.map((poster) => {
                return (
                  <Link
                    to={`/fund/list/${poster.movieId}`}
                    key={poster.movieId}
                    className="relative"
                    state={{ type: "list" }}
                  >
                    <div className="absolute top-[-20px] z-[1] text-white">
                      {poster.movieId}
                    </div>
                    <MovieListItem
                      state={poster.status}
                      url={poster.poster}
                      heigth="25vh"
                    ></MovieListItem>
                  </Link>
                );
              })}
            </div>
          </div>
        )}
        {!searchResult && (
          <div>
            <div className="flex items-end gap-4 mb-5">
              <div className=" text-gray-500">
                "{result}"으로 검색한 결과입니다.
              </div>
            </div>
            <div className="grid grid-cols-2 gap-8 p-2">
              {searchData.map((poster) => {
                return (
                  <Link
                    to={`/fund/list/${poster.movieId}`}
                    key={poster.movieId}
                    className="relative"
                    state={{ type: "search" }}
                  >
                    <MovieListItem
                      state={poster.status}
                      url={poster.poster}
                      heigth="25vh"
                    ></MovieListItem>
                  </Link>
                );
              })}
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default MovieSearch;
