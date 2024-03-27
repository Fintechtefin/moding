import Loading from "@pages/payment/Loading";
import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";

const PaymentSuccessPage = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  useEffect(() => {
    // 쿼리 파라미터 값이 결제 요청할 때 보낸 데이터와 동일한지 반드시 확인하세요.
    // 클라이언트에서 결제 금액을 조작하는 행위를 방지할 수 있습니다.
    const requestData = {
      amount: searchParams.get("amount"),
      fundingCount: searchParams.get("fundingCount"),
      fundingId: searchParams.get("fundingId"),
      method: searchParams.get("method"),
      paymentKey: searchParams.get("paymentKey"),
      orderId: searchParams.get("orderId"),
    };

    console.log(requestData);

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

  return <Loading />;
};

export default PaymentSuccessPage;
