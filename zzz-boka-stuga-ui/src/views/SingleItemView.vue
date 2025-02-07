<template>
    <div class="flex justify-center">
        <div class=" flex flex-col w-2/3 gap-4">
            <header class="text-4xl text-center">{{ stuga.namn }}</header>
            <content class="flex flex-col items-center gap-2">
                <img :src="stuga.bild" alt="" class="w-96">
                <span>{{ stuga.beskrivning }}</span>
                <span class="font-bold text-xl">{{ stuga.pris }} kr / natt</span>
            </content>
            <footer class="flex justify-center">
                <button v-if="storeUser.isLoggedIn" class="bg-white hover:bg-forest-green text-black hover:text-white hover:scale-105 transition-all border-black border shadow-md px-6 py-2" @click="showModal = true">Hyr nu!</button>
                <button v-else class="bg-white hover:bg-forest-green text-black hover:text-white hover:scale-105 transition-all border-black border shadow-md px-6 py-2" @click="showLogin = true">Logga in <font-awesome-icon icon="fa-solid fa-right-to-bracket" /></button>
            </footer>
        </div>
    </div>
    <Teleport to="body">
    <!-- use the modal component, pass in the prop -->
        <HyrStugaModalComponent :show="showModal" @close="showModal = false">
        <template #header>
            <h3>{{ stuga.namn }}</h3>
        </template>
        <template #body>
            <div class="flex justify-center mb-2">
            <img :src="stuga.bild"/>
            </div>
            <div class="flex flex-col gap-2">
            <div class="flex flex-col">
                <label for="from">Från och med</label>
                <input type="date" v-model="orderObject.bokningFrom" name="from" class="border border-black p-2"/>
            </div>
            <div class="flex flex-col">
                <label for="tom">Till och med</label>
                <input type="date" v-model="orderObject.bokningTom" name="tom" class="border border-black p-2" />
            </div>
            </div>
            <div v-if="errorMessage" class="bg-red-400 text-white border-black border shadow-md px-4 text-center hover:scale-105" @click="showModal = false">
                <span class="font-bold">{{ errorMessage }}</span>
            </div>
        </template>
        <template #footer>
            <div class="flex gap-2">
            <button
            class="modal-default-button border-black border shadow-md px-4 py-1  hover:bg-red-300"
            @click="showModal=false"
            >
                Avbryt
            </button>
            <button
            class="bg-leaf-green text-white border-black border shadow-md px-4 py-1  hover:scale-105"
            @click="CheckAvailability(stuga?.id,orderObject.bokningFrom, orderObject.bokningTom)"
            >
                Lägg i kundvagn
            </button>
            </div>
        </template>
        </HyrStugaModalComponent>
        <LogInModalComponent :show="showLogin" @close="showLogin = false">
        <template #header>
          <div class="flex justify-between">
            <h3 class="font-bold">Logga in</h3>
            <button @click="showLogin = false">
              <font-awesome-icon icon="fa-solid fa-xmark" />
            </button>
          </div>
        </template>
        <template #body>
          <div class="flex flex-col gap-4">
            <div id="username-input" class="flex flex-col">
              <label for="username" class="font-semibold ">Email</label>
              <input name="username" v-model="email" type="email" class="border border-slate-700  p-1" />
            </div>
            <div id="password-input" class="flex flex-col">
              <label for="password" class="font-semibold ">Lösenord</label>
              <input name="password" v-model="password" type="password" class="border border-slate-700  p-1" />
            </div>
          </div>
        </template>
        <template #footer>
          <button
              @click="storeUser.login(email,password), router.push('/'), showLogin = false"
              class="w-full py-2 font-semibold bg-white  border border-slate-700 hover:bg-slate-700 hover:text-white hover:border-white"
          >
            Logga in
          </button>
        </template>
      </LogInModalComponent>
    </Teleport>
</template>

<script setup lang="ts">
import { useStuga } from '../store/StugStore';
import {ref} from 'vue'

import HyrStugaModalComponent from '../components/HyrStugaModalComponent.vue';
import LogInModalComponent from '../components/LogInModalComponent.vue';
import axios from 'axios';
import { useCart } from '../store/VagnStore';
import { useUser } from '../store/UserStore';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: String
})
const router = useRouter()
const storeStuga = useStuga()
const storeCart = useCart()
const storeUser = useUser()

const showLogin = ref(false)
const showModal = ref(false)

const stuga = storeStuga.getStugaById(props.id)

const errorMessage = ref()
const email = ref()
const password = ref()

const orderObject = ref({
    bokningFrom: '',
    bokningTom: '',
    kundId: storeUser.LoggedInUser?.id,
    stugId: stuga
})
async function CheckAvailability(id: string, from:string, tom:string){
  try {
    const response = await axios.get('http://localhost:8080/Stugor/'+ id + ',' + from + ',' + tom) 
    if(response.status == 200){
      storeCart.ordrar.push(orderObject.value)
      showModal.value = false
    }
  } catch (error) {
    errorMessage.value = error.response.data
  }
}
</script>

<style lang="scss" scoped>

</style>