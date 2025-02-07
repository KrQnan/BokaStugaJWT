<template>
  <Transition name="modal">
    <div v-if="show" id="modal-mask">
      <div class="p-6 m-auto bg-white border-black border">
        <div class="p-3/4 flex flex-col gap-4">
            <button @click="$emit('cancel')" class="flex justify-end"><font-awesome-icon icon="fa-solid fa-xmark" /></button>
              <h2 class="text-2xl text-center m-2">Lägg till Användare</h2>
        <div class="p-3/4 flex flex-col gap-3">
        <div class="flex flex-col">
          <label for="name">Namn</label>
          <input type="text" name="name" v-model="kund.namn" class="border border-black" />
        </div>
        <div class="flex flex-col">
          <label for="surname">Efternamn</label>
          <input type="text" name="surname" v-model="kund.efternamn" class="border border-black" />
        </div>
        <div class="flex flex-col">
          <label for="password">Lösenord</label>
          <input type="text" name="password" v-model="kund.password" class="border border-black"/>
        </div>
        <div class="flex flex-col">
          <label for="type">Grupp</label>
          <select name="type" id="type" v-model="kund.grupp" class="border border-black">
            <option value="User">Användare</option>
            <option value="Admin">Admin</option>
          </select>
        </div>
        <div class="flex flex-col">
          <label for="adress">Adress</label>
          <input type="text" name="adress" v-model="kund.adress" class="border border-black" />
        </div>
        <div class="flex flex-col">
          <label for="zipcode">Postnummer</label>
          <input type="text" name="zipcode" v-model="kund.postnummer" class="border border-black" />
        </div>
        <div class="flex flex-col">
          <label for="city">Postort</label>
          <input type="text" name="city" v-model="kund.postort" class="border border-black" />
        </div>
        <div class="flex flex-col">
          <label for="email">Email</label>
          <input type="email" name="email" v-model="kund.email" class="border border-black" />
        </div>
        <div class="flex flex-col">
          <label for="number">Telefonnummer</label>
          <input type="text" name="number" v-model="kund.telefonnummer" class="border border-black" />
        </div>
              <div class="flex gap-4">
                  <button @click="createKund" class="border border-black py-2 px-4">Skapa</button>
                  <button @click="$emit('cancel')" class="border border-black py-2 px-4">Avbryt</button> 
              </div>
          </div>
      </div>
    </div>
    </div>
  </Transition>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useKund } from '../../../store/KundStore';
import { Kund } from '../../../Models/KundModel';
const store = useKund()
const show = defineModel<Boolean>('show')

const kund = ref<Kund>({
  id:'',
  namn: '',
  efternamn: '',
  email: '',
  telefonnummer:'',
  adress:'',
  postnummer:'',
  postort:'',
  grupp:'',
  password:''
})

function createKund(){
  store.postKund(kund.value.grupp,kund.value.namn,kund.value.efternamn,kund.value.postnummer,kund.value.postort,kund.value.adress,kund.value.email,kund.value.telefonnummer,kund.value.password)
  kund.value.namn =''
  kund.value.efternamn = ''
  kund.value.email = ''
  kund.value.telefonnummer = ''
  kund.value.adress = ''
  kund.value.postnummer = ''
  kund.value.postort = ''
  kund.value.grupp = ''
  kund.value.password = ''
  show.value = false
}
</script>

<style scoped>
#modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}
.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>../../../store/KundStore