import { useState } from "react";
import "@/assets/styles/movieList/MovieCarousel.scss";
import { Link } from "react-router-dom";
import romance from "@assets/images/romance.jpg";
import action from "@assets/images/action.jpg";
import horror from "@assets/images/horror.jpg";
import sf from "@assets/images/sf.jpg";
import comic from "@assets/images/comic.jpg";
import exc from "@assets/images/exc.jpg";
import drama from "@assets/images/drama.jpg";

const images = [
  {
    id: 0,
    category: "로맨스",
    url: romance,
  },
  {
    id: 1,
    category: "드라마",
    url: drama,
  },
  {
    id: 2,
    category: "액션",
    url: action,
  },
  {
    id: 3,
    category: "공포",
    url: horror,
  },
  {
    id: 4,
    category: "SF",
    url: sf,
  },
  {
    id: 5,
    category: "코미디",
    url: comic,
  },
  {
    id: 6,
    category: "기타",
    url: exc,
  },
];

const MovieCarousel = () => {
  const [selectedIndex, setSelectedIndex] = useState(0);
  const [startTouchX, setStartTouchX] = useState(0);

  const rotateCarousel = () => {
    const angle = (selectedIndex / images.length) * -360;
    return { transform: `translateZ(-275px) rotateY(${angle}deg)` };
  };

  const handleTouchStart = (event: React.TouchEvent<HTMLDivElement>) => {
    setStartTouchX(event.touches[0].clientX);
  };

  const handleTouchMove = (event: React.TouchEvent<HTMLDivElement>) => {
    if (!startTouchX) return;

    const currentTouchX = event.touches[0].clientX;
    const deltaX = currentTouchX - startTouchX;

    if (deltaX > 50) {
      setSelectedIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
      setStartTouchX(0);
    } else if (deltaX < -50) {
      setSelectedIndex((prevIndex) => (prevIndex + 1) % images.length);
      setStartTouchX(0);
    }
  };

  const handlePrevious = () => {
    setSelectedIndex((prevIndex) => prevIndex - 1);
  };

  const handleNext = () => {
    setSelectedIndex((prevIndex) => prevIndex + 1);
  };

  return (
    <div className="relative wrapper" onTouchStart={handleTouchStart} onTouchMove={handleTouchMove}>
      <div className="object">
        <div className="carousel" style={rotateCarousel()}>
          {images.map((image, index) => (
            <Link to={"/movie/list"} state={{ category: image.id }} key={index} className="cell">
              <div>
                <h3 className="text-white title">{image.category}</h3>
                <img className="list-image w-[100%] h-[50vh] rounded-sm" src={image.url} alt="" />
              </div>
            </Link>
          ))}
        </div>
      </div>
      <div className="absolute top-[5%] flex justify-between w-[100%] px-4">
        <button className="previous-button" onClick={handlePrevious}>
          &lt;
        </button>
        <button className="next-button" onClick={handleNext}>
          &gt;
        </button>
      </div>
    </div>
  );
};

export default MovieCarousel;
