import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import tsconfigPaths from "vite-tsconfig-paths";
import { VitePWA } from "vite-plugin-pwa";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    tsconfigPaths(),
    VitePWA({
      registerType: "autoUpdate",
      devOptions: {
        enabled: true,
      },
      manifest: {
        name: "오늘은 낚시왕",
        short_name: "오늘은 낚시왕",
        theme_color: "#ffffff",
        start_url: "/",
        icons: [
          {
            src: "./icons/logo192x192.webp",
            sizes: "192x192",
            type: "image/webp",
          },
          {
            src: "./icons/logo192x192.webp",
            sizes: "192x192",
            type: "image/webp",
            purpose: "any maskable",
          },
          {
            src: "./icons/logo512x512.webp",
            sizes: "512x512",
            type: "image/webp",
          },
          {
            src: "./icons/logo512x512.webp",
            sizes: "512x512",
            type: "image/webp",
            purpose: "any maskable",
          },
        ],
      },
    }),
  ],
  server: {
    port: 5174,
    host: "0.0.0.0",
    open: true,
  },
});
