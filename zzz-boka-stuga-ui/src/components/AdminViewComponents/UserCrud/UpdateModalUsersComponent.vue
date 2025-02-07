<template>
  <Transition>
    <div v-if="props.show" id="modal-mask">
      <div class="p-6 m-auto bg-white border-black border">
        <button @click="$emit('close')" class="float-end"><font-awesome-icon icon="fa-solid fa-xmark" /></button>
        <h2 class="text-2xl text-center m-2">Lägg till Användare</h2>
        <div class="p-3/4 flex flex-col gap-3">
          <div class="flex flex-col">
            <label for="name">Namn</label>
            <input type="text" name="name" v-model="user.namn" class="border border-black" />
          </div>
          <div class="flex flex-col">
            <label for="surname">Efternamn</label>
            <input type="text" name="surname" v-model="user.efternamn" class="border border-black" />
          </div>
          <div class="flex flex-col">
            <label for="type">Typ</label>
            <select name="type" id="type" v-model="user.grupp" class="border border-black">
              <option value="Användare">Admin</option>
              <option value="Admin">User</option>
            </select>
          </div>
          <div class="flex flex-col">
            <label for="zipcode">Postnummer</label>
            <input type="text" name="zipcode" v-model="user.postnummer" class="border border-black" />
          </div>
          <div class="flex flex-col">
            <label for="city">Postort</label>
            <input type="text" name="city" v-model="user.postort" class="border border-black" />
          </div>
          <div class="flex flex-col">
            <label for="adress">Adress</label>
            <input type="text" name="adress" v-model="user.adress" class="border border-black" />
          </div>
          <div class="flex flex-col">
            <label for="email">Email</label>
            <input type="email" name="email" v-model="user.email" class="border border-black" />
          </div>
          <div class="flex flex-col">
            <label for="number">Telefonnummer</label>
            <input type="text" name="number" v-model="user.telefonnummer" class="border border-black" />
          </div>
          <div class="w-full flex gap-5">
            <button
            class="border border-black px-4 py-2"
            @click="
              store.updateKund(
                user.id,
                user.grupp,
                user.namn,
                user.efternamn,
                user.postnummer,
                user.postort,
                user.adress,
                user.email,
                user.adress
              ),$emit('close')
            "
            >
              Spara
            </button>
            <button @click="$emit('close')" class="flex items-center gap-2 border border-black px-4 py-2">Stäng<font-awesome-icon icon="fa-solid fa-xmark" /></button>
          </div>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script setup lang="ts">
import { useKund } from '../../../store/KundStore'
import { Kund } from '../../../Models/KundModel';
const store = useKund()
const props = defineProps({
  show:Boolean,
})
const user = defineModel<Kund>('user')


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
  
</style>
../../../store/KundStore../../../Models/KundModel