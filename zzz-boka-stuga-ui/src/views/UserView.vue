<template>
  <div class="flex justify-start border border-black m-4">
    <div class="flex flex-col m-4 w-full">
      <div class="flex justify-between">
        <span class="text-xl font-bold">{{ store.LoggedInUser?.namn }} <font-awesome-icon icon="fa-solid fa-user" /></span>
        <div class="flex gap-1">
          <button class="px-4 py-2 my-2 shadow-md border border-black hover:scale-105" @click="showModalUpdate = true">Redigera profil <font-awesome-icon icon="fa-solid fa-pen" /></button>
          <button v-if="store.LoggedInUser.grupp == 'Admin'" class="px-4 py-2 my-2 shadow-md border border-black hover:scale-105" @click="router.push('/admin')">Admin vy <font-awesome-icon icon="fa-solid fa-eye" /></button>
        </div>
      </div>
      
      <div class="flex flex-col gap-2">
        <h2 class="text-xl font-bold">Dina ordrar</h2>
        <div v-for="ordrar in store.LoggedInUser?.ordrar" class="border border-black">
          <div class="m-2">
            <div class="flex gap-4">
              <h4 class="text-lg font-semibold">Order ID # {{ ordrar.id }}</h4>
              <h4 class="text-lg font-semibold">BokningsPeriod: {{ ordrar.bokningFrom }} - {{ ordrar.bokningTom }}</h4>
            </div>
            <div class=" border-black">
              <div class="flex flex-col gap-1 w-1/3">
                <h4 class="font-semibold">{{ ordrar.stugId.name }}</h4>
                <img :src="ordrar.stugId.bild" alt="">
                <span class="flex flex-col">
                  <h4 class="font-bold">Beskrivning</h4>
                  <p>{{ ordrar.stugId.beskrivning }}</p>
                </span>
                <span>Pris: {{ ordrar.stugId.pris }} kr</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <Teleport to="body">
    <UpdateModalUsersComponent :show="showModalUpdate" @close="showModalUpdate = false" v-model:user="store.LoggedInUser"/>
  </Teleport>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import UpdateModalUsersComponent from '../components/AdminViewComponents/UserCrud/UpdateModalUsersComponent.vue';
import { useUser } from '../store/UserStore';
import { useRouter } from 'vue-router';

const store = useUser()
const router = useRouter()
const showModalUpdate = ref(false)
store.getUser()
</script>

<style scoped></style>
