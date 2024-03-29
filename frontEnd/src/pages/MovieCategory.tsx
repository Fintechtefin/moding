import { useState } from "react";
import { useLocation, Link } from "react-router-dom";
import MovieList from "@components/movieList/MovieList";
import { FaSearch } from "react-icons/fa";
import { GoTriangleDown } from "react-icons/go";
import { GoTriangleUp } from "react-icons/go";
import { TbTriangleFilled } from "react-icons/tb";
import { VscTriangleUp } from "react-icons/vsc";
import { VscTriangleDown } from "react-icons/vsc";

const MovieCategory = () => {
  const location = useLocation();
  const category = location.state.category;

  const [sort, setSort] = useState("titleAsc");

  const changeCategory = () => {
    if (sort == "titleAsc") {
      setSort("titleDesc");
    } else if (sort == "titleDesc") {
      setSort("likeAsc");
    } else if (sort == "likeAsc") {
      setSort("likeDesc");
    } else if (sort == "likeDesc") {
      setSort("titleAsc");
    }
  };

  return (
    <div className="movielist-container relative">
      <div className="text-start text-3xl py-3 pl-3">MODING</div>
      <Link to={"/movie/search"}>
        <div className="absolute top-0 right-0 m-5 scale-150">
          <FaSearch />
        </div>
      </Link>
      {/* <div className="slider-container sticky">
        <CategoryBanner />
      </div> */}
      {/* <div className="">
        <div>로맨스</div>
        <div>드라마</div>
        <div>액션</div>
        <div>공포/스릴러/미스터리</div>
        <div>SF</div>
        <div>코미디</div>
        <div>기타등등</div>
      </div> */}
      <div className="w-[90%] bg-stone-800 m-auto p-[1.2vh] text-center rounded-lg text-zinc-500 cursor-pointer" onClick={() => changeCategory()}>
        <div className="flex justify-center items-center gap-2">
          {sort == "titleAsc" && (
            <>
              <div>영화제목순</div>
              <VscTriangleUp />
            </>
          )}
          {sort == "titleDesc" && (
            <>
              <div>영화제목순</div>
              <VscTriangleDown />
            </>
          )}
          {sort == "likeAsc" && (
            <>
              <div>좋아요</div>
              <VscTriangleUp />
            </>
          )}
          {sort == "likeDesc" && (
            <>
              <div>좋아요</div>
              <VscTriangleDown />
            </>
          )}
        </div>
      </div>
      <div>
        <MovieList category={category} sort={sort} />
      </div>
    </div>
  );
};

export default MovieCategory;
