import { GoCheckCircleFill } from "react-icons/go";
import { FaRegCircle } from "react-icons/fa";
import Receipt from "@components/payment/Receipt";
import { useLocation } from "react-router-dom";

const PaymentCompletedPage = () => {
  const { state } = useLocation();

  console.log(state);

  return (
    <div className="px-[3vh] py-[5vh] flex flex-col justify-between gap-[2vh] h-[100vh]">
      <div className="flex flex-col items-center gap-[2vh]">
        <div className="flex flex-col items-center gap-[1vh]">
          <GoCheckCircleFill className="text-red-600 text-[8vh]" />
          <div className="text-[2.5vh] font-bold">
            펀딩 신청이 완료되었습니다
          </div>
        </div>
        <div className="flex justify-center relative gap-[10vh]">
          <div className="flex flex-col items-center gap-[1vh]">
            <GoCheckCircleFill className="text-red-600 text-[4vh]" />
            <div className="text-[1.5vh]">펀딩신청</div>
          </div>
          <div className="w-[11vh] h-[0vh] border-[0.2vh] border-red-600 border-dashed absolute top-[25%]"></div>
          <div className="flex flex-col items-center gap-[1vh]">
            <FaRegCircle className="text-red-600 text-[4vh]" />
            <div className="text-[1.5vh]">펀딩완료</div>
          </div>
        </div>
      </div>
      <Receipt {...state} />
    </div>
  );
};

export default PaymentCompletedPage;
