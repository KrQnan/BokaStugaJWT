<template>
    <div v-for="(item, index) in storeCart.ordrar" class="flex text-black border-b-2 mb-1">
        <div class="flex flex-col justify-between text-left text-wrap w-2/3">
           <span>{{ item.stugId.namn }}</span>
           <span>{{ item.stugId.pris }} kr / natt</span>
        </div>
        <div>
            <img :src="item.stugId.bild" alt="" class="w-32"/>
        </div>
        <div  id="item" class="flex items-center justify-center hover:bg-red-700 hover:white px-4"  @click="deleteItem(index)">
            <button>
               <font-awesome-icon id="icon" icon="fa-solid fa-trash" /> 
            </button>
            
        </div>
    </div>
</template>

<script setup lang="ts">
import { useCart } from '../store/VagnStore';
const storeCart = useCart()
function deleteItem(id:number){
    storeCart.ordrar.splice(id,1)
}
</script>

<style scoped>
#item:hover{
    color:white
}
</style>