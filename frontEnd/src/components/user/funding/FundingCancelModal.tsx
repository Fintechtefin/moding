import { ChangeEvent, useState } from "react";
import toast, { Toaster } from "react-hot-toast";

interface Props {
  id: number;
  handleClickFalse: () => void;
}

const FundingCancelModal = ({ id, handleClickFalse }: Props) => {
  const [reasonType, setReasonType] = useState("");
  const [reason, setReason] = useState("");

  console.log(id);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.value !== "기타") setReason("");

    setReasonType(e.target.value);
  };

  const handleReasonChange = (e: ChangeEvent<HTMLInputElement>) =>
    setReason(e.target.value);

  const handleSubmit = () => {
    const cancelReason = reasonType === "기타" ? reason : reasonType;

    if (!cancelReason) {
      toast("사유를 알려주세요", { duration: 1500 });
      return;
    }

    console.log(cancelReason);
  };

  return (
    <div className="absolute inset-0 z-10 flex items-center justify-center bg-black bg-opacity-80 p-[3vh]">
      <div className="bg-bgGray p-[3vh] flex flex-col  gap-[3vh] rounded-[2vh] shadow-test">
        <div>
          <div className="a text-[2.5vh] font-bold ">
            펀딩 취소 사유를 선택해 주세요.
          </div>
          <div className="text-[1.5vh] text-textGray">
            펀딩 취소 시, 결제 금액이 환불 됩니다.
          </div>
        </div>
        <div className="text-[2vh] flex flex-col gap-[0.5vh]">
          <label className="flex gap-[1vh]">
            <input
              type="radio"
              name="type"
              value="단순 변심"
              onChange={handleChange}
            />
            단순 변심
          </label>
          <label className="flex gap-[1vh]">
            <input
              type="radio"
              name="type"
              value="펀딩 변경"
              onChange={handleChange}
            />
            펀딩 변경
          </label>
          <label className="flex gap-[1vh]">
            <input
              type="radio"
              name="type"
              value="기타"
              onChange={handleChange}
            />
            기타
          </label>
          <input
            disabled={reasonType !== "기타"}
            placeholder="사유를 알려주세요"
            type="text"
            value={reason}
            onChange={handleReasonChange}
            className="rounded-[0.5vh] text-black p-[1vh] w-[100%]"
          />
        </div>
        <div className="w-[100%] flex gap-[2vh] text-[2vh] font-bold">
          <button
            className="flex-1 bg-[#EFEFEF] text-bgGray p-[1.5vh] rounded-[1vh] shadow-bgwhite"
            onClick={handleClickFalse}
          >
            닫기
          </button>
          <button
            className="flex-1 text-white bg-red-600 p-[1.5vh] rounded-[1vh] shadow-bgRed"
            onClick={handleSubmit}
          >
            취소
          </button>
        </div>
        <Toaster
          containerStyle={{
            margin: "0 auto",
          }}
          toastOptions={{
            // Define default options
            style: {
              background: "#262626",
              color: "#fff",
              fontSize: "2vh",
            },
          }}
        />
      </div>
    </div>
  );
};

export default FundingCancelModal;
