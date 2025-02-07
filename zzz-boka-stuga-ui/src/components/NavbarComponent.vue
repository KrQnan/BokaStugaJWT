<template>
  <div>
    <nav
      class="bg-forest-green text-white font-tiltWrap font-semibold flex justify-between transition-all"
    >
      <RouterLink
        to="/"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
      >
      <font-awesome-icon icon="fa-solid fa-house" />
      </RouterLink>
      <RouterLink
        to="/faq"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
      >
        FAQ
      </RouterLink>
      <RouterLink
        to="/alla"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
      >
        Alla stugor
      </RouterLink>
      <RouterLink
        v-if="storeUser.isLoggedIn"
        to="/user"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
      >
        Profil
      </RouterLink>
      <button
      v-if="storeUser.isLoggedIn"
        id="dropdown"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
      >
        <font-awesome-icon icon="fa-solid fa-cart-shopping" />
        <div class="dropdown-content">
          <CartItemComponent/>
          <div>
            <button v-if="storeCart.ordrar.length > 0" class="bg-green-300 text-black border-black border shadow-md px-6 py-1 " @click="router.push('/hyra')">Boka nu</button>
            <span v-else>Här var det tomt <font-awesome-icon icon="fa-solid fa-store-slash" /></span>
          </div>
        </div>
        
      </button>
      <button
        v-if="!storeUser.isLoggedIn"
        id="show-modal"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
        @click="showModal = true"
      >
        Logga in <font-awesome-icon icon="fa-solid fa-right-to-bracket" />
      </button>
      <button
        v-else
        id="show-modal"
        class="hover:bg-white hover:text-forest-green text-center w-full p-2"
        @click="storeUser.logout(), router.push('/')"
      >
        Logga ut
      </button>
    
    </nav>
    <Teleport to="body">
    <!-- use the modal component, pass in the prop -->
      <LogInModalComponent :show="showModal" @close="showModal = false">
        <template #header>
          <div class="flex justify-between">
            <h3 class="font-bold">Logga in</h3>
            <button @click="showModal = false">
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
              @click="storeUser.login(email,password), router.push('/'), showModal = false"
              class="w-full py-2 font-semibold bg-white  border border-slate-700 hover:bg-slate-700 hover:text-white hover:border-white"
          >
            Logga in
          </button>
        </template>
      </LogInModalComponent>
    </Teleport>
  </div>
</template>

<script setup lang="ts">
import LogInModalComponent from './LogInModalComponent.vue';
import CartItemComponent from './CartItemComponent.vue';
import { useUser } from '../store/UserStore';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { useCart } from '../store/VagnStore';

const storeUser = useUser()
const storeCart = useCart()
const router = useRouter()
const showModal = ref(false)
const email = ref()
const password = ref()
</script>

<style scoped lang="scss">
a.router-link-exact-active {
  background-color: white;
  color: #4a5859;
}

#dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  width: 360px;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
}

#dropdown:hover .dropdown-content {
  display: block;
}
</style>
