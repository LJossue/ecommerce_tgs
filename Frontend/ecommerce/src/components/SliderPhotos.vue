<template>
  <swiper :options="swiperOption">
    <swiper-slide v-for="(image, index) in images" :key="index">
      <!-- Aplicamos estilos al contenedor de la imagen para establecer un tamaño fijo -->
      <div class="slide-container">
        <img :src="image" alt="Slide" />
      </div>
    </swiper-slide>
    <!-- Si quieres agregar paginación, puedes usar lo siguiente -->
    <div class="swiper-pagination"></div>
  </swiper>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/swiper-bundle.css"; // Importar los estilos CSS de Swiper

export default {
  name: "SliderPhotos",
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      images: [
        "https://hype.com.co/cdn/shop/articles/banner-blog-bad_bunny_pf-sku-hype.jpg?v=1698091982",
        "https://www.gameinformer.com/sites/default/files/2020/11/12/5f3f896a/msmmm_adidas_lowtop_leap_legal.jpg",
      ],
      swiperOption: {
        // Configuración de Swiper, puedes personalizar según tus necesidades
        loop: true,
        autoplay: {
          delay: 3000,
          disableOnInteraction: false,
        },
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
      },
    };
  },
};
</script>

<style scoped>
/* Estilos personalizados para Swiper */
.swiper-pagination-bullet {
  background-color: white;
}

/* Establecer un tamaño fijo para las imágenes */
.slide-container img {
  width: 100vw; /* O cualquier otro valor que desees */
  height: 35rem; /* Para mantener la proporción */
  object-fit: cover;
}
</style>
