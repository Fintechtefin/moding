import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { GoCheckCircleFill } from "react-icons/go";
import { FaRegCircle } from "react-icons/fa";
import Receipt from "@components/payment/Receipt";

const PaymentSuccessPage = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  useEffect(() => {
    // 쿼리 파라미터 값이 결제 요청할 때 보낸 데이터와 동일한지 반드시 확인하세요.
    // 클라이언트에서 결제 금액을 조작하는 행위를 방지할 수 있습니다.
    const requestData = {
      orderId: searchParams.get("orderId"),
      amount: searchParams.get("amount"),
      paymentKey: searchParams.get("paymentKey"),
    };

    async function confirm() {
      const response = await fetch("/confirm", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      });

      const json = await response.json();

      if (!response.ok) {
        // 결제 실패 비즈니스 로직을 구현하세요.
        navigate(`/fail?message=${json.message}&code=${json.code}`);
        return;
      }

      // 결제 성공 비즈니스 로직을 구현하세요.
    }
    confirm();
  }, []);

  return (
    <div className="p-[5vh] flex flex-col gap-[2vh]">
      <div className="flex flex-col items-center gap-[2vh]">
        <GoCheckCircleFill className="text-red-600 text-[8vh]" />
        <div>
          펀딩 신청이 완료되었습니다<div className=""></div>
        </div>
      </div>
      <div className="flex justify-center relative gap-[10vh]">
        <div className="flex flex-col items-center gap-[1vh]">
          <GoCheckCircleFill className="text-red-600 text-[4vh]" />
          <div className="text-[1.5vh]">펀딩신청</div>
        </div>
        <div className="w-[11vh] h-[0vh] border-[0.2vh] border-red-600 border-dashed absolute top-[25%]"></div>
        <div className="flex flex-col items-center gap-[1vh]">
          <FaRegCircle className="text-red-600 text-[4vh] z-10"></FaRegCircle>
          <div className="text-[1.5vh]">펀딩완료</div>
        </div>
      </div>
      <Receipt />
    </div>
  );
};

export default PaymentSuccessPage;
