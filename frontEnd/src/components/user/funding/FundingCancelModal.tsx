import { ChangeEvent, useState } from "react";
import axios from "axios";
import { ToasterMsg } from "@components/Common";
import { toastMsg } from "@util/commonFunction";

interface Props {
  id: number;
  handleClickFalse: () => void;
}

const RADIO_OPTIONS = ["단순 변심", "펀딩 변경", "기타"];

const FundingCancelModal = ({ id, handleClickFalse }: Props) => {
  const [reasonType, setReasonType] = useState("");
  const [reason, setReason] = useState("");
  const jwt = localStorage.getItem("jwt");

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;

    if (value !== "기타") setReason("");

    setReasonType(value);
  };

  const handleReasonChange = (e: ChangeEvent<HTMLInputElement>) =>
    setReason(e.target.value);

  const handleSubmit = async () => {
    const cancelReason = reasonType === "기타" ? reason : reasonType;

    if (!cancelReason) {
      toastMsg("사유를 알려주세요");
      return;
    }

    const data = {
      cancelReason,
      orderId: id,
    };

    try {
      const res = await axios.post(
        `${import.meta.env.VITE_BASE_URL}:8084/fundings/orders/${id}/refund`,
        data,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: jwt,
          },
        }
      );

      console.log(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="absolute inset-0 z-10 flex items-center justify-center bg-black bg-opacity-80 p-[3vh]">
      <div className="bg-bgGray p-[3vh] flex flex-col gap-[3vh] rounded-[2vh] shadow-test">
        <div>
          <div className="a text-[2.5vh] font-bold ">
            펀딩 취소 사유를 선택해 주세요.
          </div>
          <div className="text-[1.5vh] text-textGray">
            펀딩 취소 시, 결제 금액이 환불 됩니다.
          </div>
        </div>
        <div className="text-[2vh] flex flex-col gap-[0.5vh]">
          {RADIO_OPTIONS.map((option) => (
            <label className="flex gap-[1vh]" key={option}>
              <input
                type="radio"
                name="type"
                value={option}
                onChange={handleChange}
                checked={reasonType === option}
              />
              {option}
            </label>
          ))}
          <input
            disabled={reasonType !== "기타"}
            placeholder="사유를 알려주세요 (필수)"
            type="text"
            value={reason}
            onChange={handleReasonChange}
            className="rounded-[0.5vh] text-black p-[1vh] w-full"
          />
        </div>
        <div className="w-full flex gap-[2vh] text-[2vh] font-bold">
          <button
            className="flex-1 bg-[#EFEFEF] text-bgGray p-[1.5vh] rounded-[1vh]"
            onClick={handleClickFalse}
          >
            닫기
          </button>
          <button
            className="flex-1 text-white bg-red-600 p-[1.5vh] rounded-[1vh]"
            onClick={handleSubmit}
          >
            취소
          </button>
        </div>
        <ToasterMsg />
      </div>
    </div>
  );
};

export default FundingCancelModal;
