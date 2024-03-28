import { useEffect, useState } from "react";
import {
  loadPaymentWidget,
  PaymentWidgetInstance,
} from "@tosspayments/payment-widget-sdk";
import { nanoid } from "nanoid";
import NoneNavHeader from "@components/NoneNavHeader";
import Loading from "@pages/payment/Loading";
import { ToasterMsg } from "@components/Common";
import { toastMsg } from "@util/commonFunction";
import post from "@assets/images/영화포스터.jpg";
import "@assets/styles/payment/Payment.scss";

const KEY = nanoid();

const PaymentPage = () => {
  const widgetClientKey = import.meta.env.VITE_TOSS_API_KEY;

  const [paymentWidget, setPaymentWidget] =
    useState<PaymentWidgetInstance | null>(null);
  const [price] = useState(50);
  const [loading, setLoading] = useState(true);
  const [showPaymentButton, setShowPaymentButton] = useState(false);

  useEffect(() => {
    const customerKey = KEY;

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
      setLoading(false);
    });

    paymentWidget.renderAgreement("#agreement", { variantKey: "AGREEMENT" });
  }, [paymentWidget, price]);

  const handlePaymentRequest = async () => {
    if (paymentWidget == null) return;
    const paymentMethodsWidget = paymentWidget.renderPaymentMethods(
      "#payment-widget",
      { value: price }
      // { variantKey: "DEFAULT" }
    );
    const selectedPaymentMethod =
      paymentMethodsWidget.getSelectedPaymentMethod().method;
    // console.log(selectedPaymentMethod);

    // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
    // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
    try {
      await paymentWidget?.requestPayment({
        orderId: KEY,
        orderName: "엘리멘탈",
        successUrl: `${window.location.origin}/fund/payment/success?fundingCount=2&fundingId=1&method=${selectedPaymentMethod}`,
        failUrl: `${window.location.origin}/fund/payment/fail`,
      });
    } catch (error) {
      console.error("Error requesting payment:", error);
      const errorMessage = (error as Error).message;
      toastMsg(errorMessage);
    }
  };

  return (
    <div className="bg-white h-[100vh] flex flex-col">
      <div className="text-black bg-[#F5F5F5] rounded-b-[3vh] shadow-bgShadow">
        <NoneNavHeader centerText="펀딩신청" />
        <div className="flex justify-between p-[3vh]">
          <div className="text-[20px] font-bold">총 결제 금액</div>
          <div className="text-[20px] font-bold">10,000원</div>
        </div>
      </div>
      <div className="flex-1 none-scroll flex flex-col gap-[5vh] overflow-auto w-[100%] pt-[3vh]">
        <div className="text-[#333D4B] flex flex-col gap-[2vh] px-[3vh] z-10">
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
          <div className="h-[1vh]">
            <div id="payment-widget" />
            <div id="agreement" />
          </div>
        </div>
      </div>
      {/* 결제하기 버튼 */}
      {showPaymentButton && (
        <div className="flex p-[3vh]">
          <button
            id="payment-button"
            disabled={!showPaymentButton}
            onClick={handlePaymentRequest}
            className="flex-1 text-[2.5vh] font-bold rounded-[1vh] p-[2vh] shadow-bgRed bg-red-600"
          >
            결제하기
          </button>
        </div>
      )}
      {loading && <Loading />}
      <ToasterMsg />
    </div>
  );
};

export default PaymentPage;
