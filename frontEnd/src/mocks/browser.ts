import { setupWorker } from "msw/browser";
import { movieHandlers } from "./movieHandlers";
import { rankHandlers } from "./rankHandlers";

const worker = setupWorker(...movieHandlers, ...rankHandlers);

export default worker;
