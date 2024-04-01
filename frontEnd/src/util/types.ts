export interface MovieInfo {
  title: string;
  poster: string;
  movieId: number;
}

export interface ProgressMovie {
  id: number;
  movieId: number;
  endAt: string;
  moviePoster: string;
  movieTitle: string;
  orderUuid: string;
  participantCount: number;
  recruitedCount: number;
}

export interface FundingCompleted extends MovieInfo {
  fundingFinalResult: number;
  goalCnt: number;
  attendCnt: number;
  reservationId: number;
  date: string;
}

export interface MovieFund extends MovieInfo {
  status?: string;
  likeCnt?: number;
  requestCnt?: number;
}

export interface Subscribe {
  id: number;
  name: string;
  state: string;
  count: number;
  url: string;
}

export interface ISeat {
  line: string;
  col: number;
}

export interface ISeats {
  seat: ISeat[];
}

export interface ITicket {
  seats: ISeats;
  poster: string;
  age: string;
  title: string;
  date: string;
  startTime: string;
  runningTime: number;
  count: number;
  name: string;
  number: number;
}

// 마이 페이지
export interface MenuOptionItem {
  name: string;
  url: string;
}
