import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { formatDateWithDay1 } from "@util/commonFunction";
import type { FundingCompleted } from "@util/types";
import UserFundProgressBar from "@components/common/UserFundProgressBar";
import Prompt from "@components/reverse/Prompt";
import { axiosApi } from "@util/commons";

interface Props {
  item: FundingCompleted;
}

const FundingSuccessItem = ({ item }: Props) => {
  const [isOpen, setIsOpen] = useState(false);
  const [hasTicket, setHasTicket] = useState(false);

  const navigate = useNavigate();

  const moveMovieDetail = () =>
    navigate(`/fund/list/${item.movieId}`, { state: { type: "list" } });

  const moveCheckTicket = async () => {
    try {
      const res = await axiosApi().get(
        `/reservations/get/${item.reservationId}`
      );

      console.log(res);

      navigate(`/user/ticket/${res.data.number}`, {
        state: res.data,
      });
    } catch (err) {
      console.log(err);
    }
  };

  const cancelReservation = async () => {
    try {
      const res = await axiosApi().put(
        `/reservations/cancel/${item.reservationId}`,
        null
      );

      console.log(res);

      setHasTicket(false);
      setIsOpen(false);
    } catch (error) {
      console.log(error);
    }
  };

  const calculationDate = (targetDate: string) => {
    const today = new Date();
    const specificDate = new Date(targetDate);
    today.setHours(0, 0, 0, 0);
    specificDate.setHours(0, 0, 0, 0);

    const diffInHours =
      (specificDate.getTime() - today.getTime()) / (1000 * 60 * 60);
    const diffInDays = diffInHours / 24;

    return diffInDays;
  };

  useEffect(() => {
    const ck = async () => {
      try {
        const res = await axiosApi().get(
          `/reservations/get/${item.reservationId}`
        );
        console.log(res);
        setHasTicket(true);
      } catch (err) {
        console.log(err);
      }
    };

    ck();
  }, []);

  return (
    <div className="flex flex-col bg-bgGray p-[2vh] rounded-lg gap-[2vh] shadow-test">
      <div className="w-full flex gap-[2vh]">
        <img
          className="w-[9vh] h-[13vh] object-cover rounded-md cursor-pointer"
          src={item.poster}
          alt=""
          loading="lazy"
          onClick={moveMovieDetail}
        />
        <div className="relative flex flex-col justify-between w-full">
          <div className="flex items-center w-full gap-[2vh] ">
            <div
              className="flex-1 text-[2.5vh] font-bold w-0 overflow-hidden text-ellipsis whitespace-nowrap cursor-pointer"
              onClick={moveMovieDetail}
            >
              {item.title}
            </div>
            <div className="text-[1.5vh] text-textGray">{`${formatDateWithDay1(
              item.date
            )}`}</div>
          </div>
          <UserFundProgressBar
            type="success-bar"
            attendCnt={item.attendCnt}
            goalCnt={item.goalCnt}
          />
        </div>
      </div>
      {hasTicket && (
        <div className="flex gap-[1vh]">
          {calculationDate(item.date) > 1 && (
            <button
              className="flex-1 p-[1vh] border border-textGray rounded-lg text-[2vh]"
              onClick={() => setIsOpen(true)}
            >
              좌석취소
            </button>
          )}
          <button
            className="flex-1 p-[1vh] border border-textGray rounded-lg text-[2vh]"
            onClick={moveCheckTicket}
          >
            티켓확인
          </button>
        </div>
      )}
      {isOpen && (
        <Prompt
          onClose={() => setIsOpen(false)}
          onConfirm={cancelReservation}
        />
      )}
    </div>
  );
};

export default FundingSuccessItem;
