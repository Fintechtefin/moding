export interface Movie {
  movieId: number;
  poster: string;
  status: string;
}

export interface MovieList extends Movie {
  title: string;
  likeCnt: number;
}

export interface MovieCategory {
  movieList: Movie[];
  totalCnt: number;
}

export interface MovieRanking extends Movie {
  searchCnt: number;
  refreshTime: number;
}

export interface ModingList extends Movie {
  crowdCnt: number;
  peopleCnt: number;
}

export interface MovieInfo extends Movie {
  title: string;
  releaseAt: string;
  runningTime: number;
  age: string;
  actors: string;
  plot: string;
  likeCnt: number;
  hopeCnt: number;
  total: number;
  genere: Array<string>;
  success: number;
}

export interface FundingInfo {
  cinemaName: string;
  crowdCnt: number;
  movieDate: string;
  peopleCnt: number;
  price: number;
}
