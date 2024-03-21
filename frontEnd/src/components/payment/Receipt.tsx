import RECEIPT_IMAGE from "@assets/images/receipt_image.webp";
import ReceiptDetail from "@components/payment/ReceiptDetail";
import { useNavigate, useSearchParams } from "react-router-dom";

const Receipt = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  return (
    <div className="relative flex justify-center">
      <img className="w-full h-[65vh]" src={RECEIPT_IMAGE} alt="" />
      <div className="absolute top-[8vh] text-black w-[70%] flex flex-col gap-[8vh]">
        <div className="d text-[5vh] text-center">Moding</div>
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
        className="absolute bottom-[4vh] pt-[2.5vh] pb-[1vh] text-[2.5vh] font-bold border-dashed border-t-[1px] border-x-0 border-b-0 border-black w-[88%] bg-transparent"
        onClick={() => navigate("/")}
      >
        확인
      </button>
    </div>
  );
};

export default Receipt;
