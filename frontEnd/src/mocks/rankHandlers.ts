import { http, HttpResponse } from "msw";

export const rankHandlers = [
  http.get("/api/fundings", ({ request }) => {
    return HttpResponse.json([
      {
        movieId: 1,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/27/tn_DPK011651.jpg",
        status: "무딩중",
      },
    ]);
  }),
];
