import { useEffect, useState } from "react";
import {
  loadPaymentWidget,
  PaymentWidgetInstance,
} from "@tosspayments/payment-widget-sdk";
import { nanoid } from "nanoid";
import toast, { Toaster } from "react-hot-toast";
import NoneNavHeader from "@components/NoneNavHeader";
import post from "@assets/images/영화포스터.jpg";
import "@assets/styles/payment/Payment.scss";

const PaymentPage = () => {
  const widgetClientKey = "test_ck_P9BRQmyarY9dWpMpp71vrJ07KzLN";
  const customerKey = "v0sCzAipMwncSa6owyId4";

  const [paymentWidget, setPaymentWidget] =
    useState<PaymentWidgetInstance | null>(null);
  const [price, setPrice] = useState(0);
  const [showPaymentButton, setShowPaymentButton] = useState(false);

  useEffect(() => {
    setPrice(50);

    const fetchPaymentWidget = async () => {
      try {
        const loadedWidget = await loadPaymentWidget(
          widgetClientKey,
          customerKey
        );
        setPaymentWidget(loadedWidget);
      } catch (error) {
        console.error("Error fetching payment widget:", error);
      }
    };

    fetchPaymentWidget();
  }, []);

  useEffect(() => {
    if (paymentWidget == null) return;

    const paymentMethodsWidget = paymentWidget.renderPaymentMethods(
      "#payment-widget",
      { value: price },
      { variantKey: "DEFAULT" }
    );

    paymentMethodsWidget.on("ready", () => {
      // 결제 버튼 활성화
      setShowPaymentButton(true);
    });

    paymentWidget.renderAgreement("#agreement", { variantKey: "AGREEMENT" });
  }, [paymentWidget, price]);

  const handlePaymentRequest = async () => {
    // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
    // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
    try {
      await paymentWidget?.requestPayment({
        orderId: nanoid(),
        orderName: "토스 티셔츠 외 2건",
        successUrl: `${window.location.origin}/fund/payment/success`,
        failUrl: `${window.location.origin}/fund/payment/fail`,
      });
    } catch (error) {
      console.error("Error requesting payment:", error);
      const errorMessage = (error as Error).message;
      toast(errorMessage, { duration: 2000 });
    }
  };

  return (
    <div className="bg-white h-[100vh]">
      <div className="text-black bg-[#F5F5F5] rounded-b-[3vh] shadow-bgShadow">
        <NoneNavHeader centerText="펀딩신청" />
        <div className="flex justify-between p-[3vh]">
          <div className="text-[20px] font-bold">총 결제 금액</div>
          <div className="text-[20px] font-bold">10,000원</div>
        </div>
      </div>
      <div className="none-scroll flex flex-col gap-[5vh] overflow-auto w-[100%] h-[83vh] pt-[3vh]">
        <div className="text-[#333D4B] flex flex-col gap-[2vh] px-[3vh] z-[2]">
          <div className="font-bold text-[20px]">펀딩 정보</div>
          <div className="flex gap-[2vh]">
            <img src={post} alt="" className="w-[13vh] rounded-[1vh]" />
            <div className="flex flex-col justify-between">
              <div>
                <div>엘리멘탈</div>
                <div>2024.03.09(토) 15:00</div>
                <div>CGV 강남</div>
              </div>
              <div>일반 1</div>
            </div>
          </div>
        </div>
        {/* 결제 UI, 이용약관 UI 영역 */}
        <div className="relative top-[-11vh]">
          <div className="w-[100%] h-[5vh] bg-white absolute top-0 z-[1]"></div>
          <div className="">
            <div id="payment-widget" />
            <div id="agreement" />
          </div>
        </div>
        {/* 결제하기 버튼 */}
        {showPaymentButton && (
          <button
            id="payment-button"
            disabled={!showPaymentButton}
            onClick={handlePaymentRequest}
            className="w-[100%] px-[3vh] fixed bottom-[2vh] border-none bg-transparent"
          >
            <div className="w-[100%] py-[2vh] shadow-bgRed bg-red-600 rounded-[1vh] text-[2.5vh] font-bold text-white">
              결제하기
            </div>
          </button>
        )}
        <Toaster
          containerStyle={{
            margin: "0 auto",
          }}
          toastOptions={{
            // Define default options
            style: {
              background: "#363636",
              color: "#fff",
              fontSize: "2vh",
            },
          }}
        />
      </div>
    </div>
  );
};

export default PaymentPage;
