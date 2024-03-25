/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  // corePlugins: {
  //   preflight: false,
  // },
  theme: {
    extend: {
      colors: { bgGray: "#191919", textGray: "#898989" },
      boxShadow: {
        bgRed: "inset 0px 3px 10px 5px rgb(168, 6, 6)",
        bgShadow: "0 0.1vh 1vh rgba(0, 0, 0, 0.2);",
        bgTT: "0 0 1vh rgba(0, 0, 0, 0.1)",
      },
    },
  },
  plugins: [],
};
