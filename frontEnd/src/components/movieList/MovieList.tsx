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
  const listRef = useRef<HTMLDivElement>(null);
  const [hasFetchedOnce, setHasFetchedOnce] = useState(false);

  const [ref, inView] = useInView({
    threshold: 0,
  });

  const { data, fetchNextPage, hasNextPage, isFetchingNextPage } =
    useInfiniteQuery<MovieCategory>({
      queryKey: ["GenreList", category, sort],
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

  // reactQuery로 가져온 데이터 하나의 배열로 저장
  const PosterList = data?.pages
    .flatMap((page) => page.movieList || [])
    .filter(Boolean);
  // 데이터에 있는 전체 리스트 갯수
  const totalCnt = data?.pages?.[0]?.totalCnt || 0;

  const handleScroll = (e: UIEvent<HTMLDivElement>) => {
    const target = e.currentTarget;
    sessionStorage.setItem("movieListScroll", String(target.scrollTop));
  };

  useEffect(() => {
    const savedPosition = sessionStorage.getItem("movieListScroll");
    if (listRef.current && savedPosition) {
      listRef.current.scrollTop = parseInt(savedPosition, 10);
    }
  }, []);

  return (
    <div className="movielist-container">
      <div className="flex flex-col items-end pt-4 pr-3 text-gray-400">
        <div className="text-[3vh]">웨이드님을 위해</div>
        <div className="text-[3vh]">
          무딩이 준비한 {PosterList && totalCnt}편의
        </div>
        <div className="text-[3vh]">{cateTitle}</div>
      </div>
      <div
        ref={listRef}
        onScroll={handleScroll}
        className="movie-list none-scroller translate-y-[-40px] px-[1.1vh] grid grid-cols-3 gap-[1vh] overflow-auto pb-56"
      >
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
                    height="22vh"
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
