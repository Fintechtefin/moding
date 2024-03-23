import { atom, selector, selectorFamily } from "recoil";

export const selectSeatsAtom = atom<string[]>({
  key: "selectSeatsAtom",
  default: [],
});

export const selectSeatsLengthSelector = selector<number>({
  key: "selectSeatsLengthSelector",
  get: ({ get }) => {
    const seats = get(selectSeatsAtom);
    return seats.length;
  },
});

export const isSelectedSelector = selectorFamily<boolean, string>({
  key: "isSelectedSelector",
  get:
    (seatId) =>
    ({ get }) => {
      const data = get(selectSeatsAtom);
      const result = data.includes(seatId);
      return result;
    },
});

export const isSelectableSelector = selectorFamily<
  boolean,
  { seatId: string; max: number }
>({
  key: "isSelectableSelector",
  get:
    ({ seatId, max }) =>
    ({ get }) => {
      const dataLen = get(selectSeatsLengthSelector);
      const isSelect = get(isSelectedSelector(seatId));
      const result = dataLen < max || isSelect;
      return result;
    },
});
