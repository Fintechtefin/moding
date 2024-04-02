import "@components/Test.scss";
import RECEIPT_IMAGE from "@assets/images/receipt_image.webp";

const Test = () => {
  return (
    <main className="ticket-system">
      <div className="flex justify-center ">
        <div className="printer w-full h-[3vh] border-white border-[5px] rounded-xl" />
      </div>
      <div className="-mt-3 overflow-hidden ">
        <div className="receipts">
          {/* <img src={RECEIPT_IMAGE} alt="" className="receipt" /> */}
          <div className="receipt">
            <div className="route">
              <h2>NYC</h2>
              <svg
                className="plane-icon"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 510 510"
              >
                <path
                  fill="#3f32e5"
                  d="M497.25 357v-51l-204-127.5V38.25C293.25 17.85 275.4 0 255 0s-38.25 17.85-38.25 38.25V178.5L12.75 306v51l204-63.75V433.5l-51 38.25V510L255 484.5l89.25 25.5v-38.25l-51-38.25V293.25l204 63.75z"
                />
              </svg>
              <h2>ATL</h2>
            </div>
            <div className="details">
              <div className="item">
                <span>Passanger</span>
                <h3>69Pixels</h3>
              </div>
              <div className="item">
                <span>Flight No.</span>
                <h3>US6969</h3>
              </div>
              <div className="item">
                <span>Departure</span>
                <h3>08/26/2018 15:33</h3>
              </div>
              <div className="item">
                <span>Gate Closes</span>
                <h3>15:03</h3>
              </div>
              <div className="item">
                <span>Luggage</span>
                <h3>Hand Luggage</h3>
              </div>
              <div className="item">
                <span>Seat</span>
                <h3>69P</h3>
              </div>
            </div>
          </div>
          <div className="receipt qr-code">
            <svg
              className="qr"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 29.938 29.938"
            >
              <path d="M7.129 15.683h1.427v1.427h1.426v1.426H2.853V17.11h1.426v-2.853h2.853v1.426h-.003zm18.535 12.83h1.424v-1.426h-1.424v1.426zM8.555 15.683h1.426v-1.426H8.555v1.426zm19.957 12.83h1.427v-1.426h-1.427v1.426zm-17.104 1.425h2.85v-1.426h-2.85v1.426zm12.829 0v-1.426H22.81v1.426h1.427zm-5.702 0h1.426v-2.852h-1.426v2.852zM7.129 11.406v1.426h4.277v-1.426H7.129zm-1.424 1.425v-1.426H2.852v2.852h1.426v-1.426h1.427zm4.276-2.852H.002V.001h9.979v9.978zM8.555 1.427H1.426v7.127h7.129V1.427zm-5.703 25.66h4.276V22.81H2.852v4.277zm14.256-1.427v1.427h1.428V25.66h-1.428zM7.129 2.853H2.853v4.275h4.276V2.853zM29.938.001V9.98h-9.979V.001h9.979zm-1.426 1.426h-7.127v7.127h7.127V1.427zM0 19.957h9.98v9.979H0v-9.979zm1.427 8.556h7.129v-7.129H1.427v7.129zm0-17.107H0v7.129h1.427v-7.129zm18.532 7.127v1.424h1.426v-1.424h-1.426zm-4.277 5.703V22.81h-1.425v1.427h-2.85v2.853h2.85v1.426h1.425v-2.853h1.427v-1.426h-1.427v-.001zM11.408 5.704h2.85V4.276h-2.85v1.428zm11.403 11.405h2.854v1.426h1.425v-4.276h-1.425v-2.853h-1.428v4.277h-4.274v1.427h1.426v1.426h1.426V17.11h-.004zm1.426 4.275H22.81v-1.427h-1.426v2.853h-4.276v1.427h2.854v2.853h1.426v1.426h1.426v-2.853h5.701v-1.426h-4.276v-2.853h-.002zm0 0h1.428v-2.851h-1.428v2.851zm-11.405 0v-1.427h1.424v-1.424h1.425v-1.426h1.427v-2.853h4.276v-2.853h-1.426v1.426h-1.426V7.125h-1.426V4.272h1.426V0h-1.426v2.852H15.68V0h-4.276v2.852h1.426V1.426h1.424v2.85h1.426v4.277h1.426v1.426H15.68v2.852h-1.426V9.979H12.83V8.554h-1.426v2.852h1.426v1.426h-1.426v4.278h1.426v-2.853h1.424v2.853H12.83v1.426h-1.426v4.274h2.85v-1.426h-1.422zm15.68 1.426v-1.426h-2.85v1.426h2.85zM27.086 2.853h-4.275v4.275h4.275V2.853zM15.682 21.384h2.854v-1.427h-1.428v-1.424h-1.427v2.851zm2.853-2.851v-1.426h-1.428v1.426h1.428zm8.551-5.702h2.853v-1.426h-2.853v1.426zm1.426 11.405h1.427V22.81h-1.427v1.426zm0-8.553h1.427v-1.426h-1.427v1.426zm-12.83-7.129h-1.425V9.98h1.425V8.554z" />
            </svg>
            <div className="description">
              <h2>69Pixels</h2>
              <p>Show QR-code when requested</p>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default Test;
