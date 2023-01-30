/** @type {import('tailwindcss').Config} */

const colors = require('tailwindcss/colors');
module.exports = {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    container: {
      center: true,
    },
    extend: {
      colors: {
        'regal-blue': '#243c5a',
      },
    }
  },
  plugins: [require("@tailwindcss/forms")],
};
