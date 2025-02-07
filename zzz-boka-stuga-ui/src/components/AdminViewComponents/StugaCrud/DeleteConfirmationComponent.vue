<template>
   <Transition>
        <div v-if="props.show" id="modal-mask">
            <div class="border border-black p-6 m-auto bg-white flex flex-col">
                <button @click="$emit('close')" class="flex justify-end"><font-awesome-icon icon="fa-solid fa-xmark" /></button>
                <h2 class="text-xl"><slot name="title"></slot></h2>
                <div class="flex gap-4 mt-4">
                    <button @click="$emit('close')" class="border border-black bg-red-300 py-2 px-6 ">Nej</button>
                    <button @click="$emit('delete')" class="border bg border-black bg-green-300 py-2 px-6 ">Ja</button>
                </div> 
            </div>
            
        </div> 
    </Transition>
</template>

<script setup lang="ts">
const props = defineProps({
    show:Boolean,
})
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