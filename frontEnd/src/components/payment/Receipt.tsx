import RECEIPT_IMAGE from "@assets/images/receipt_image.webp";
import ReceiptDetail from "@components/payment/ReceiptDetail";
import { useNavigate, useSearchParams } from "react-router-dom";

const Receipt = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  return (
    <div className="relative flex justify-center text-black">
      <img className="w-full h-[65vh]" src={RECEIPT_IMAGE} alt="" />
      <div className="absolute w-[100%] h-[100%] flex flex-col justify-between">
        <div className="flex flex-col gap-[8vh] px-[3vh] py-[5vh]">
          <div className="text-[5vh] text-center">Moding</div>
          <div className="flex flex-col gap-[2vh]">
            <ReceiptDetail label="펀딩내역" value="엘리멘탈" />
            <ReceiptDetail label="결제일자" value="2024.03.09 15:00" />
            <ReceiptDetail
              label="결제금액"
              value={`${Number(searchParams.get("amount")).toLocaleString()}원`}
            />
          </div>
        </div>
        <button
          className="py-[3vh] text-[2.5vh] font-bold border-dashed border-t border-t-black"
          onClick={() => navigate("/")}
        >
          확인
        </button>
      </div>
    </div>
  );
};

export default Receipt;
