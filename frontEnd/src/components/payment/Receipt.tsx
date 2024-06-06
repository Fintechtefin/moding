import RECEIPT_IMAGE from "@assets/images/receipt_image.webp";
import ReceiptDetail from "@components/payment/ReceiptDetail";
import { useNavigate } from "react-router-dom";

interface Props {
  amount: number;
  approvedAt: string;
  fundingTitle: string;
}

const Receipt = ({ amount, approvedAt, fundingTitle }: Props) => {
  const navigate = useNavigate();

  const relativeTime = new Date(approvedAt).toLocaleDateString("ko-KR", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    hour12: false,
  });

  return (
    <div className="relative flex justify-center text-black">
      <img className="w-full h-[65vh]" src={RECEIPT_IMAGE} alt="" />
      <div className="absolute w-[100%] h-[100%] flex flex-col justify-between">
        <div className="flex flex-col gap-[8vh] px-[3vh] py-[5vh]">
          <div className="text-[5vh] text-center">Moding</div>
          <div className="flex flex-col gap-[2vh]">
            <ReceiptDetail label="펀딩내역" value={fundingTitle} />
            <ReceiptDetail label="결제일자" value={relativeTime} />
            <ReceiptDetail label="결제금액" value={`${amount}원`} />
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
