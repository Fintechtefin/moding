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
  requestCnt: number;
}

export interface MovieInfo extends Movie {
  title: string;
  releaseAt: string;
  runningTime: number;
  age: string;
  actors: string;
  plot: string;
  like: boolean;
  likeCnt: number;
  request: boolean;
  hopeCnt: number;
  total: number;
  genere: Array<string>;
  success: number;
}

export interface FundingInfo {
  time: string;
  date: Date;
  fundingId: number;
  price: number;
  cinemaName: string;
  movieDate: string;
  crowdCnt: number;
  peopleCnt: number;
}
