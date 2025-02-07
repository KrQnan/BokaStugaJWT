<template>
  <Transition name="modal">
    <div v-if="props.show" id="modal-mask">
      <div class="container p-4 m-auto border bg-white border-black flex flex-col gap-2">
        <h2 class="text-2xl text-center m-2">Lägg till stugor</h2>
        <div class="p-3/4 flex flex-col gap-4">
          <div class="flex flex-col">
            <label>Namn</label>
            <input type="text" v-model="stuga.namn" name="name" placeholder="Namn på stuga" class="border-black border" />
          </div>
          <div class="flex flex-col">
            <label>Bild</label>
            <input type="text" v-model="stuga.bild" name="picture" class="border-black border" />
          </div>
          <div class="flex flex-col">
            <label>Beskrivning</label>
            <textarea
              id="description"
              name="description"
              v-model="stuga.beskrivning"
              placeholder="Skriv en beskrivning"
              rows="4"
              cols="50"
              class="border-black border"
            />
          </div>
          <div class="flex flex-col">
            <label for="dateFrom">Från och med</label>
            <input type="date" name="dateFrom" v-model="stuga.bokningFrom"/>
          </div>
          <div class="flex flex-col">
            <label for="dateFrom">Från och med</label>
            <input type="date" name="dateFrom" v-model="stuga.bokningTom"/>
          </div>
          <div class="flex flex-col">
            <label>Kund</label>
            <input type="text" v-model.lazy="kundId" name="name" placeholder="Namn på stuga" class="border-black border" />
          </div>
          <div class="w-full flex gap-5">
            <button
            class="border border-black px-4 py-2"
            @click="
              store.updateStuga(
                stuga.id,
                stuga.namn,
                stuga.bokningFrom,
                stuga.bokningTom,
                stuga.bild,
                stuga.beskrivning,
                kundId
              ),$emit('close'),console.log(stuga.bokningFrom)
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
import { useStuga } from '../../../store/StugStore';
import { Kund } from '../../../Models/KundModel';
import  VueDatePicker from '@vuepic/vue-datepicker';
import { ref } from 'vue';
const store = useStuga()
const props = defineProps({
  show:Boolean,
})
const stuga = defineModel<Kund>('stuga')
const kundId = ref(0)

const date = ref(new Date());

const dateFormat = ()=> {
  const day = date.value()
}

const format = () =>{
  const day = date.value.getDay();
  const month = date.value.getMonth();
  const year = date.value.getFullYear();

  return `${day}/${month}/${year}`;
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
</style>
