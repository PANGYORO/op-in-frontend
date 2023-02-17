/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./index.html", "./src/**/*.{html,js,jsx,css}"],
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
  plugins: [require("@tailwindcss/forms"), require("@tailwindcss/colors")],
};
