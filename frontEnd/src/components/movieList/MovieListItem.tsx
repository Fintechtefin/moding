import StatusBadge from "@components/movieDetail/StatusBadge";

type PosterProps = {
  state: string;
  url: string;
  height: string;
};

const MovieListItem = ({ state, url, height }: PosterProps) => {
  return (
    <div className={`w-[100%] h-[${height}] relative `}>
      <div className="absolute top-1 right-1 w-[100%]">
        <StatusBadge status={state} textSize="sm" />
      </div>
      <img
        className={`w-[100%] h-[${height}] object-cover brightness-[90%]"`}
        src={url}
        alt=""
      />
    </div>
  );
};

export default MovieListItem;
