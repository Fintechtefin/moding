import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import Loading from "@pages/payment/Loading";
import { ToasterMsg } from "@components/Common";
import { toastMsg } from "@util/commonFunction";

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

    const confirm = async () => {
      try {
        console.log(searchParams.get("orderId"));
        const res = await axios.post(
          `${
            import.meta.env.VITE_BASE_URL
          }/api/fundings/orders/${searchParams.get("orderId")}/confirm`,
          JSON.stringify(requestData),
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: localStorage.getItem("jwt"),
            },
          }
        );

        navigate("/fund/payment/completed", {
          replace: true,
          state: res.data,
        });
      } catch (err) {
        console.log(err);
        if (axios.isAxiosError(err) && err.response) {
          navigate(`/fund/payment/fail?message=${err.response.data.message}`);
        }
      }
    };

    confirm();
  }, []);

  return (
    <>
      <ToasterMsg />
      <Loading />
    </>
  );
};

export default PaymentSuccessPage;
