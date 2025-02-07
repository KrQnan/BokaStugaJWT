<template>
    <div class="m-2 flex flex-col gap-2">
        <h2 class="text-2xl font-bold">Summering av din order</h2>
        <div v-for="items in storeCart.ordrar" class="border border-black py-12">
            <div class="flex justify-between mx-6">
                <div class="flex flex-col gap-1">
                    <span class="text-xl">{{ items.stugId.namn }}</span>
                    <span><strong>Boknings period: </strong>{{ items.bokningFrom }} - {{ items.bokningTom }}</span>
                    <span>{{ items.stugId?.pris }} kr / natt</span>
                </div>
                <div>
                    <img :src="items.stugId?.bild" alt="" class="w-64">
                </div>
            </div>
        </div>
        <div class="w-full">
            <button class="bg-forest-green w-full py-2 my-2 shadow-md text-white border-black border" @click="storeCart.createOrder()">Lägg order</button>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useCart } from '../store/VagnStore';

const storeCart = useCart()
</script>

<style scoped>

</style>