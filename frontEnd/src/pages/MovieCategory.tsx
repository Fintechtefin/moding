import { useEffect, useState, useRef } from "react";
import { useLocation, Link } from "react-router-dom";
import MovieList from "@components/movieList/MovieList";
import { FaSearch } from "react-icons/fa";
import { VscTriangleUp } from "react-icons/vsc";
import { VscTriangleDown } from "react-icons/vsc";
import { Swiper, SwiperSlide } from "swiper/react";
import { EffectCoverflow } from "swiper/modules";
import "swiper/swiper-bundle.css";
import { MdKeyboardArrowUp } from "react-icons/md";
import "@/assets/styles/MovieCategory.scss";
import { movieCategoryState, movieSortState } from "@recoil/movieState";
import { useRecoilState } from "recoil";

const MovieCategory = () => {
  const location = useLocation();
  const initialIdx = location.state.category;

  // console.log(idx);

  const categoryNav = [
    { id: 1, category: "로맨스" },
    { id: 3, category: "드라마" },
    { id: 7, category: "액션" },
    { id: 10, category: "공포" },
    { id: 13, category: "SF" },
    { id: 15, category: "코미디" },
    { id: 32, category: "기타" },
  ];

  const [category, setCategory] = useRecoilState(movieCategoryState);
  const [sort, setSort] = useRecoilState(movieSortState);
  const [activeIndex, setActiveIndex] = useState<number>(initialIdx);
  const [swiperInstance, setSwiperInstance] = useState<Swiper | null>(null);

  useEffect(() => {
    if (swiperInstance) {
      swiperInstance.slideTo(activeIndex, 0);
    }
  }, [activeIndex, swiperInstance]);

  useEffect(() => {
    if (!category || category == 0) {
      setCategory(categoryNav[initialIdx].id);
      setActiveIndex(initialIdx);
    } else {
      const idx = categoryNav.findIndex((c) => c.id === category);
      if (idx >= 0) setActiveIndex(idx);
    }
  }, [category, initialIdx]);

  const changeSort = () => {
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

  const goTop = () => {
    if (!window.scrollY) return;
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  const cateTitle = categoryNav.find((c) => c.id === category)?.category ?? "";

  return (
    <div className="category-container relative">
      <div className="category-header">
        <div className="category-header-item">
          <div className="text-start text-3xl py-3 pl-3">MODING</div>
          <Link to={"/movie/search"}>
            <div className="absolute top-0 right-0 m-5 scale-150">
              <FaSearch />
            </div>
          </Link>
          <div className="slider-container sticky">
            <Swiper
              modules={[EffectCoverflow]}
              effect="coverflow"
              slidesPerView={3}
              centeredSlides={true}
              onSwiper={(swiper) => setSwiperInstance(swiper)}
              onSlideChange={(swiper) => {
                const activeIdx = swiper.activeIndex;
                setActiveIndex(activeIdx);
                setCategory(categoryNav[activeIdx].id);
              }}
              slideToClickedSlide={true}
              coverflowEffect={{
                rotate: 0,
                stretch: 0,
                depth: 100,
                modifier: 1,
                slideShadows: true,
              }}
              initialSlide={activeIndex}
              slideActiveClass="active-slider"
            >
              {categoryNav.map((show, idx) => {
                return (
                  <SwiperSlide
                    key={idx}
                    className={`pb-1 slider-nav ${
                      activeIndex === idx ? "active-slide" : ""
                    }`}
                  >
                    <div className="slider-sub text-center text-xl">
                      {show.category}
                    </div>
                    {/* <img className="slide-img" src={show.imgUrl} alt="" /> */}
                  </SwiperSlide>
                );
              })}
            </Swiper>
          </div>
          <div
            className="w-[90%] bg-stone-800 m-auto p-[1.2vh] text-center rounded-lg text-zinc-500 cursor-pointer"
            onClick={() => changeSort()}
          >
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
                  <div>좋아요순</div>
                </>
              )}
              {sort == "likeDesc" && (
                <>
                  <div>최신순</div>
                </>
              )}
            </div>
          </div>
        </div>
      </div>
      <div className="categoryList-area">
        <div>
          <MovieList category={category} cateTitle={cateTitle} sort={sort} />
        </div>
        <div
          className="sticky bottom-[65px] float-right mr-3 bg-stone-600 rounded-full opacity-80"
          onClick={goTop}
        >
          <MdKeyboardArrowUp className="text-[6vh] text-white cursor-pointer" />
        </div>
      </div>
    </div>
  );
};

export default MovieCategory;
