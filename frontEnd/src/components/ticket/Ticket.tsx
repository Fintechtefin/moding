import { useState } from "react";
import tt from "@assets/images/moviePost.png";

import "@assets/styles/Ticket.scss";
import TicketBack from "./TicketBack";
import { useLocation } from "react-router-dom";

const Ticket = () => {
  const [isFlipped, setIsFlipped] = useState(false);

  const { state } = useLocation();

  const handleClick = () => {
    setIsFlipped((prev) => !prev);
  };

  return (
    <div className="card-container w-[42vh] h-[77vh] " onClick={handleClick}>
      <div className={`card ${isFlipped ? "is-flipped" : ""}`}>
        <img
          className="card-face card-front ticket-mask w-[45vh] h-[77vh]"
          src={state.poster}
          onError={(e) => {
            e.currentTarget.src = tt;
          }}
        />
        <TicketBack state={state} />
      </div>
      <div className="test">111111111</div>
    </div>
  );
};

export default Ticket;
