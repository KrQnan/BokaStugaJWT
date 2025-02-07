import { defineConfig } from "cypress";

export default defineConfig({
  chromeWebSecurity: false,
  component: {
    devServer: {
      framework: "vue",
      bundler: "vite",
    },
    specPattern: "**/*.cy.ts",
  },
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
<<<<<<< HEAD
    baseUrl: 'http://localhost:8080/',
  },
=======
    specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
    baseUrl: "http://localhost:5173/",
  }
>>>>>>> d4f803e (openshift)
});
