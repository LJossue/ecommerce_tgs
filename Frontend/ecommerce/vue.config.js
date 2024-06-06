const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
});
module.exports = {
  devServer: {
    port: 3000, // Puedes especificar el puerto que desees aquí
  },
};
