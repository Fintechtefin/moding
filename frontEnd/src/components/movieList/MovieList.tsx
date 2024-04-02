import { useState, useEffect } from "react";
import MovieListItem from "./MovieListItem";
import "@/assets/styles/movieList/MovieList.scss";
import { Link } from "react-router-dom";
import type { MovieCategory } from "@util/types/movieType";
import { useQuery } from "@tanstack/react-query";
import { getGenreList } from "@api/movie";
import { useInView } from "react-intersection-observer";

interface Props {
  category: number;
  cateTitle: string;
  sort: string;
}

const MovieList = ({ category, cateTitle, sort }: Props) => {
  const [PosterList, setPosterList] = useState<MovieCategory | null>(null);
  const [page, setPage] = useState(1);
  const nickname = localStorage.getItem("nickname");

  const { data } = useQuery<MovieCategory>({
    queryKey: ["GenreList", category, page, sort], // 쿼리 키를 지정합니다.
    queryFn: () => getGenreList(category, page, sort),
    // getNowRanking 함수를 호출합니다.
  });

  useEffect(() => {
    if (data) {
      setPosterList((prevPosterList) => {
        if (!prevPosterList) {
          return data;
        }
        return {
          ...prevPosterList,
          movieList: [...prevPosterList.movieList, ...data.movieList],
          totalCnt: data.totalCnt,
        };
      });
      console.log(PosterList);
    }
  }, [data]);

  useEffect(() => {
    setPage(1);
    setPosterList(null);
  }, [category, sort]);

  const [ref, inView] = useInView({
    threshold: 0.5,
  });

  useEffect(() => {
    if (inView) {
      setPage((prev) => prev + 1);
    }
  }, [inView]);

  return (
    <div className="movielist-container">
      <div className="flex flex-col items-end pt-4 pr-3 text-gray-400">
        <div className="text-[3vh]">{nickname}님을 위해</div>
        <div className="text-[3vh]">무딩이 준비한 {PosterList && PosterList.totalCnt}편의</div>
        <div className="text-[3vh]">{cateTitle}</div>
      </div>
      <div className="movie-list none-scroller translate-y-[-40px] px-[1.5vh] grid grid-cols-3 gap-[1.2vh] overflow-auto pb-56">
        {PosterList && (
          <>
            {PosterList.movieList.map((poster) => {
              return (
                <Link to={`/fund/list/${poster.movieId}`} key={poster.movieId} state={{ type: "list" }}>
                  <MovieListItem state={poster.status} url={poster.poster} heigth="22vh"></MovieListItem>
                </Link>
              );
            })}
          </>
        )}
        <div ref={ref}></div>
      </div>
    </div>
  );
};

export default MovieList;
