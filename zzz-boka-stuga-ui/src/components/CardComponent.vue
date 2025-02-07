<template>
  <div>
    <div class="grid w-80 h-96 shadow-lg hover:scale-105 hover:bg-beige hover:transition p-4">
      <header class="text-center font-bold self-start">
        <slot name="header"></slot>
      </header>
      <main class="self-center">
        <slot name="picture"></slot>
      </main>
      <div class="text-center">
        <slot name="description"></slot>
      </div>
      <footer class="text-center self-end">
        <slot name="footer">
          <div class="flex justify-around">
            <button class=" bg-white text-black hover:bg-forest-green hover:text-white shadow-md border-black border px-4 py-1 " @click="router.push('/hyra/' + props.stuga.id)">Detaljer</button>
            <button  v-if="storeUser.isLoggedIn == true" class="bg-forest-green text-white border-black border shadow-md px-4 py-1 " @click="showModal = true">Boka nu</button>
            <button v-else class="bg-white text-black hover:bg-forest-green hover:text-white border-black border shadow-md px-4 py-1 " @click="showLogin = true">Logga in <font-awesome-icon icon="fa-solid fa-right-to-bracket" /></button>
          </div>
        </slot>
      </footer>
    </div>
  </div>
  <Teleport to="body">
    <!-- use the modal component, pass in the prop -->
    <HyrStugaModalComponent :show="showModal" @close="showModal = false">
      <template #header>
        <h3>{{ props.stuga.namn }}</h3>
      </template>
      <template #body>
        <div class="flex justify-center mb-2">
          <img :src="props.stuga.bild"/>
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
          @click="CheckAvailability(stuga.id,orderObject.bokningFrom, orderObject.bokningTom)"
          >
            Lägg i kundvagn
          </button>
        </div>
      </template>
    </HyrStugaModalComponent>
  </Teleport>
  <!-- use the modal component, pass in the prop -->
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
</template>

<script setup lang="ts">
import HyrStugaModalComponent from './HyrStugaModalComponent.vue';
import LogInModalComponent from './LogInModalComponent.vue';
import { StugaHamta } from '../Models/StugaModel';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useCart } from '../store/VagnStore';
import { useUser } from '../store/UserStore';
import { Order } from '../Models/OrderModel';
import axios from 'axios';

const storeUser = useUser()
const storeCart = useCart()
const router = useRouter()
const props = defineProps<{
  stuga: StugaHamta
}>()
const showModal = ref(false)
const showLogin = ref(false)
const email = ref()
const password = ref()
const errorMessage = ref("")
const orderObject = ref<Order>({
  bokningTom: '',
  bokningFrom: '',
  kundId: storeUser.LoggedInUser?.id,
  stugId: props.stuga
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
<style scoped></style>
