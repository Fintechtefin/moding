import { useState, useEffect, useRef } from "react";
import MovieListItem from "./MovieListItem";
import "@/assets/styles/movieList/MovieList.scss";
import { Link } from "react-router-dom";
import type { MovieCategory } from "@util/types/movieType";
import { useInfiniteQuery } from "@tanstack/react-query";
import { getGenreList } from "@api/movie";
import { useInView } from "react-intersection-observer";

interface Props {
  category: number;
  cateTitle: string;
  sort: string;
}

const MovieList = ({ category, cateTitle, sort }: Props) => {
  const nickname = localStorage.getItem("nickname");

  const [ref, inView] = useInView({
    threshold: 0.5,
  });

  const [hasFetchedOnce, setHasFetchedOnce] = useState(false);

  const { data, fetchNextPage, hasNextPage, isFetchingNextPage } =
    useInfiniteQuery<MovieCategory>({
      queryKey: ["GenreList", category, sort], // 쿼리 키를 지정합니다.
      queryFn: ({ pageParam }) =>
        getGenreList(category, pageParam as number, sort),
      initialPageParam: 1,
      getNextPageParam: (lastPage, allPages) => {
        const totalLoaded = allPages.reduce(
          (acc, page) => acc + page.movieList?.length,
          0
        );

        if (!lastPage.movieList || lastPage.movieList.length === 0)
          return undefined;

        return totalLoaded < lastPage.totalCnt
          ? allPages.length + 1
          : undefined;
      },
      // getNowRanking 함수를 호출합니다.
      staleTime: 1000 * 60 * 5,
      gcTime: 1000 * 60 * 10,
    });

  useEffect(() => {
    if (!inView) {
      setHasFetchedOnce(false);
    }
    if (inView && hasNextPage && !isFetchingNextPage && !hasFetchedOnce) {
      fetchNextPage();
      setHasFetchedOnce(true);
    }
  }, [inView, hasNextPage, isFetchingNextPage]);

  const PosterList = data?.pages.flatMap((page) => page.movieList) || [];
  const totalCnt = data?.pages?.[0]?.totalCnt || 0;

  return (
    <div className="movielist-container">
      <div className="flex flex-col items-end pt-4 pr-3 text-gray-400">
        <div className="text-[3vh]">웨이드님을 위해</div>
        <div className="text-[3vh]">무딩이 준비한 {totalCnt}편의</div>
        <div className="text-[3vh]">{cateTitle}</div>
      </div>
      <div className="movie-list none-scroller translate-y-[-40px] px-[1.5vh] grid grid-cols-3 gap-[1.2vh] overflow-auto pb-56">
        {PosterList && (
          <>
            {PosterList.map((poster) => {
              return (
                <Link
                  to={`/fund/list/${poster.movieId}`}
                  key={poster.movieId}
                  state={{ type: "list" }}
                >
                  <MovieListItem
                    state={poster.status}
                    url={poster.poster}
                    heigth="22vh"
                  ></MovieListItem>
                </Link>
              );
            })}
          </>
        )}
        <div ref={ref} className="w-full h-[100px]"></div>
      </div>
    </div>
  );
};

export default MovieList;
