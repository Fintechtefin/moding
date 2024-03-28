import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import Loading from "@pages/payment/Loading";

const PaymentSuccessPage = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  useEffect(() => {
    const requestData = {
      amount: Number(searchParams.get("amount")),
      fundingCount: Number(searchParams.get("fundingCount")),
      fundingId: Number(searchParams.get("fundingId")),
      method: searchParams.get("method"),
      paymentKey: searchParams.get("paymentKey"),
    };

    async function confirm() {
      try {
        const res = await axios.post(
          `${
            import.meta.env.VITE_BASE_URL
          }:8084/fundings/orders/${searchParams.get("orderId")}/confirm`,
          JSON.stringify(requestData),
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        navigate("/fund/payment/completed", {
          replace: true,
          state: res.data,
        });
      } catch (err) {
        if (axios.isAxiosError(err) && err.response) {
          navigate(`/fund/payment/fail?message=${err.response.data.message}`);
        }
      }
    }
    confirm();
  }, []);

  return <Loading />;
};

export default PaymentSuccessPage;
