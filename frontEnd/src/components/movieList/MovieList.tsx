import { useState } from "react";
import post1 from "@assets/images/영화포스터.jpg";
import MovieListItem from "./MovieListItem";
import "@/assets/styles/movieList/MovieList.scss";
import { Link } from "react-router-dom";
import type { Movie } from "@util/types/movieType";

interface Props {
  category: string;
  sort: string;
}

const MovieList = ({ category, sort }: Props) => {
  const [PosterList] = useState<Movie[]>([
    {
      movieId: 1,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 2,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 3,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 4,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 5,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 6,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 7,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 8,
      poster: post1,
      status: "무딩예정",
    },
    {
      movieId: 9,
      poster: post1,
      status: "무딩예정",
    },
  ]);

  return (
    <div className="movielist-container">
      <div className="flex flex-col items-end pt-4 pr-3 text-gray-400">
        <div className="text-[3.5vh]">000님을 위해</div>
        <div className="text-[3.5vh]">
          무딩이 준비한 {PosterList.length}편의
        </div>
        <div className="text-[3.5vh]">{category}</div>
      </div>
      <div className="movie-list none-scroller translate-y-[-20px] px-[1.5vh] grid grid-cols-3 gap-[1.2vh] overflow-auto pb-56">
        {PosterList.map((poster) => {
          return (
            <Link to={`/fund/list/${poster.movieId}`} key={poster.movieId}>
              <MovieListItem
                state={poster.status}
                url={poster.poster}
                heigth="22vh"
              ></MovieListItem>
            </Link>
          );
        })}
      </div>
    </div>
  );
};

export default MovieList;
