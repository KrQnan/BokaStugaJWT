import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { Order } from "../Models/OrderModel";
export const useCart = defineStore('cart', () => {
    const ordrar = ref<Array<Order>>([])
    function createOrder(){
        try {
            ordrar.value.forEach((item) => {
                const request = axios.post('http://localhost:8080/Ordrar', {
                    kundId: item.kundId,
                    stugId: item.stugId.id,
                    bokningFrom: item.bokningFrom,
                    bokningTom: item.bokningTom
            })
            console.log("Här körs det", request)
            ordrar.value.pop()
            }) 
        } catch (error) {
            console.log(error)
        }
    }

    return{
       ordrar,
       createOrder
    }
})