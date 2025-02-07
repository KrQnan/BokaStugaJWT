<template>
  <div>
    <NavbarComponent />
    <RouterView />
    <FooterComponent />
  </div>
</template>

<script setup lang="ts">
import FooterComponent from './components/FooterComponent.vue'
import NavbarComponent from './components/NavbarComponent.vue'
</script>

<style scoped></style>
