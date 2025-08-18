import { atom } from "recoil";

export const movieCategoryState = atom<number>({
  key: "movieCategoryState",
  default: 1,
});

export const movieSortState = atom<string>({
  key: "movieSortState",
  default: "titleAsc",
});
