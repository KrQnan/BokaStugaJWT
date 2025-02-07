<template>
    <Transition name="modal">
    <div v-if="show" id="modal-mask" >
      <div class="container p-4 m-auto border bg-white border-black flex flex-col gap-2">
        <h2 class="text-2xl text-center m-2">Lägg till stugor</h2>
        <div class="p-3/4 flex flex-col gap-4">
          <div class="flex flex-col">
            <label>Namn</label>
            <input type="text" name="name" v-model="stuga.namn" placeholder="Namn på stuga" class="border-black border" />
          </div>
          <div class="flex flex-col">
            <label>Bild</label>
            <input type="text" v-model="stuga.bild" name="picture" class="border-black border" />
          </div>
          <div class="flex flex-col">
            <label>Pris</label>
            <input type="number" v-model="stuga.pris" name="price" class="border-black border" />
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
          <button class="border border-black py-2" @click="createStuga()">Skapa</button>
          <button class="border border-black py-2" @click="show = false">Avbryt</button>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useStuga } from '../../../store/StugStore';
const store = useStuga()
const show = defineModel<Boolean>('show')

const stuga = ref({
    namn: '',
    bild: '',
    beskrivning: '',
    pris:''
})

function createStuga(){
    store.postStuga(
        stuga.value.namn,
        stuga.value.bild,
        stuga.value.beskrivning,
        stuga.value.pris
    )
    stuga.value.namn = ''
    stuga.value.bild = ''
    stuga.value.beskrivning = ''
    stuga.value.pris = ''
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
</style>