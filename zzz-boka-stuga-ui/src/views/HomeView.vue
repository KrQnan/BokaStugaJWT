<template>
  <div class="container flex m-auto mt-6 justify-center flex-col">
    <h2 class="text-4xl text-center">Fjällstugor</h2>
    <GalleryComponent></GalleryComponent>
  </div>
</template>

<script setup lang="ts">
import { useKund } from '../store/KundStore'
import { useStuga } from '../store/StugStore'
import GalleryComponent from '../components/GalleryComponent.vue'
const kundStore = useKund()
const stugaStore = useStuga()
</script>

<style scoped></style>
