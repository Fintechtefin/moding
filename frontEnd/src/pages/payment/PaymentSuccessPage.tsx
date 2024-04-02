import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import Loading from "@pages/payment/Loading";
import { axiosApi } from "@util/commons";

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

    console.log(requestData);

    const confirm = async () => {
      try {
        console.log(searchParams.get("orderId"));
        const res = await axiosApi().post(
          `/fundings/orders/${searchParams.get("orderId")}/confirm`,
          requestData
        );

        navigate("/fund/payment/completed", {
          replace: true,
          state: res.data,
        });
      } catch (err) {
        console.log(err);
        // if (axios.isAxiosError(err) && err.response) {
        //   navigate(`/fund/payment/fail?message=${err.response.data.message}`);
        // }
      }
    };

    confirm();
  }, []);

  return <Loading />;
};

export default PaymentSuccessPage;
