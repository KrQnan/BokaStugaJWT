<template>
  <div class="container m-auto mt-4 flex flex-col gap-4">
    <h1 class="text-center text-3xl font-bold">Alla stugor</h1>
    <div class="flex flex-wrap gap-4 justify-center">
      <div v-for="stuga in store.stugor">
        <CardComponent :stuga="stuga">
        <template #header>{{ stuga.namn }}</template>
        <template #picture><img :src="stuga.bild"/></template>
        <template #description>
          <div class="flex flex-col">
            <span>{{ stuga.pris }} kr / natt</span>
          </div>
        </template>
        <template #footer>
        </template>
        </CardComponent>
      </div>
    </div>
  </div>

</template>

<script setup lang="ts">
import CardComponent from '../components/CardComponent.vue';
import { useStuga } from '../store/StugStore';
const store = useStuga()
</script>

<style scoped>
img{
  height: 200px;
  width: 275px
}
</style>
