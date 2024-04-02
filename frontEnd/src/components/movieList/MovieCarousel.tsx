import { useState } from "react";
import "@/assets/styles/movieList/MovieCarousel.scss";
import { Link } from "react-router-dom";

const images = [
  {
    id: 0,
    category: "로맨스",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
  },
  {
    id: 1,
    category: "드라마",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
  },
  {
    id: 2,
    category: "액션",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
  },
  {
    id: 3,
    category: "공포",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
  },
  {
    id: 4,
    category: "SF",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
  },
  {
    id: 5,
    category: "코미디",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
  },
  {
    id: 6,
    category: "기타",
    url: "http://www.honcho-sfx.com/blog/wp-content/uploads/2015/08/Deadpool-trailer-300x300.jpg",
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
      setSelectedIndex(
        (prevIndex) => (prevIndex - 1 + images.length) % images.length
      );
      setStartTouchX(0);
    } else if (deltaX < -50) {
      setSelectedIndex((prevIndex) => (prevIndex + 1) % images.length);
      setStartTouchX(0);
    }
  };

  return (
    <div
      className="wrapper"
      onTouchStart={handleTouchStart}
      onTouchMove={handleTouchMove}
    >
      <div className="object">
        <div className="carousel" style={rotateCarousel()}>
          {images.map((image, index) => (
            <Link
              to={"/movie/list"}
              state={{ category: image.id }}
              key={index}
              className="cell"
            >
              <div>
                <h3 className="text-white">{image.category}</h3>
              </div>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
};

export default MovieCarousel;
