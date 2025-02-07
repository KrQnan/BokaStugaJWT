<template>
  <div class=" mt-6 flex flex-wrap gap-4 justify-center">
    <CardComponent v-for="stuga in store.stugor" :stuga="stuga">
      <template #header>{{ stuga.namn }}</template>
      <template #picture><img :src="stuga.bild" class="w-80 h-52"/></template>
      <template #description>
        <div class="flex flex-col">
          <span>{{ stuga.pris }} kr / natt</span>
        </div>
      </template>
      <template #footer>
      </template>
    </CardComponent>
  </div>

</template>

<script setup lang="ts">
import CardComponent from './CardComponent.vue'
import { useStuga } from '../store/StugStore'

const store = useStuga()

</script>

<style scoped>
img{
  height: 200px;
  width: 275px
}
</style>
