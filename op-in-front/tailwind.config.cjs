/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        priwhite: "#f9f7f7",
        prisky: "#dbe2ef",
        priblue: "#3f72af",
        prinavy: "#112d4e",
      },
    },
  },
  corePlugins: {
    // due to https://github.com/tailwindlabs/tailwindcss/issues/6602 - buttons disappear
    // preflight: false,
  },
  plugins: [require("@tailwindcss/forms")],
};
