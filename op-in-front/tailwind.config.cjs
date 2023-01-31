/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors');
module.exports = {
  content: ["./index.html","./src/**/*.{html,js,jsx,css}"],
  theme: {
    extend: {
      colors: {
        'primary-white': '#f9f7f7',
        'primary-sky': '#dbe2ef',
        'primary-blue': '#3f72af',
        'primary-navy': '#112d4e',
      },
    },
  },
  plugins: [],
}
