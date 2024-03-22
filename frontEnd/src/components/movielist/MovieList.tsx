import { useState } from "react";
import post1 from "@assets/images/영화포스터.jpg";
import MovieListItem from "./MovieListItem";
import "@/assets/styles/movieList/MovieList.scss";
import { NavLink } from "react-router-dom";

export type Poster = {
  id: number;
  name: string;
  state: string;
  count: number;
  url: string;
};

const MovieList = () => {
  const [PosterList, setPosterList] = useState<Poster[]>([
    {
      id: 1,
      name: "엘리멘탈",
      state: "무딩중",
      count: 3663,
      url: post1,
    },
    {
      id: 2,
      name: "불한당",
      state: "무딩예정",
      count: 1763,
      url: post1,
    },
    {
      id: 3,
      name: "엘리멘탈",
      state: "무딩중",
      count: 3663,
      url: post1,
    },
    {
      id: 4,
      name: "불한당",
      state: "무딩예정",
      count: 1763,
      url: post1,
    },
    {
      id: 5,
      name: "엘리멘탈",
      state: "무딩중",
      count: 3663,
      url: post1,
    },
    {
      id: 6,
      name: "불한당",
      state: "무딩예정",
      count: 1763,
      url: post1,
    },
    {
      id: 7,
      name: "불한당",
      state: "무딩예정",
      count: 1763,
      url: post1,
    },
    {
      id: 8,
      name: "불한당",
      state: "무딩예정",
      count: 1763,
      url: post1,
    },
    {
      id: 9,
      name: "불한당",
      state: "무딩예정",
      count: 1763,
      url: post1,
    },
  ]);

  return (
    <div className="movielist-container">
      <div className="">아이템</div>
      <div className="movie-list none-scroller px-[1.5vh] mt-20 grid grid-cols-3 gap-[1.2vh] overflow-auto pb-40">
        {PosterList.map((poster) => {
          return (
            <NavLink to={`/fund/list/${poster.id}`} key={poster.id}>
              <MovieListItem poster={poster}></MovieListItem>
            </NavLink>
          );
        })}
      </div>
    </div>
  );
};

export default MovieList;
