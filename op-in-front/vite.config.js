import path from "path";
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  build: {
    outDir: "../opInBackEnd/src/main/resources/static/",
  },
  resolve: {
    alias: {
      '@components' : path.resolve(__dirname, "./src/components"),
      '@pages': path.resolve(__dirname, "./src/pages"),
      '@assets': path.resolve(__dirname, "./src/assets"),
      '@hooks': path.resolve(__dirname, "./src/hooks"),
      '@recoil': path.resolve(__dirname, "./src/recoil")

    },
  },
});

