import StatusBadge from "@components/movieDetail/StatusBadge";

type PosterProps = {
  state: string;
  url: string;
  heigth: string;
};

const MovieListItem = ({ state, url, heigth }: PosterProps) => {
  return (
    <div className="w-[100%] relative">
      <StatusBadge status={state} textSize="xl" />
      <div className="w-[100%]"></div>
      <img className={`w-[100%] h-[${heigth}] object-cover brightness-[90%]"`} src={url} alt="" />
    </div>
  );
};

export default MovieListItem;
