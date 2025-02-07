import axios from 'axios'
import { defineStore } from 'pinia'
import { ref,computed } from 'vue'
import { StugaHamta, StugaSkapa } from '../Models/StugaModel'

export const useStuga = defineStore('stugor', () => {
  const stugor = ref<StugaHamta[]>()
  const loading = ref(false)

  async function getStugor() {
    try {
      const response = await axios.get('http://localhost:8080/Stugor')
      stugor.value = response.data
    } catch (error) {
      console.log(error)
    }
  }
  getStugor()
  
  async function postStuga(
    namn:string,
    bild:string,
    beskrivning:string,
    pris:string
  ){
    try {
      const request = await axios.post<StugaSkapa>('http://localhost:8080/Stugor',{
        namn: namn,
        bild: bild,
        beskrivning: beskrivning,
        pris:pris
      })
      getStugor()
      console.log(request)
    } catch (error) {
      console.log(error)
    }
  }

  async function updateStuga(
    id:string,
    namn:string,
    bokningFrom:string,
    bokningTom:string,
    bild:string,
    beskrivning:string,
    kundId: number
  ) {
    console.log(kundId)
    try {
      
      const request = await axios.put('http://localhost:8080/Stugor/' + id, {
        namn: namn,
        bokningFrom: bokningFrom,
        bokningTom: bokningTom,
        bild: bild,
        beskrivning: beskrivning,
        kundId: kundId
      })
      getStugor()
      console.log(request)
    } catch (error) {
      console.log(error)
    }
  }

  async function deleteStuga(id:string){
    try {
      const request = await axios.delete('http://localhost:8080/Stugor/' + id)
      getStugor()
      console.log(request.statusText, request.status)
    } catch (error) {
      console.log(error)
    }
  }
  const getStugaById = computed(() => (id: string) => {
    return stugor.value?.find((stuga:StugaHamta ) => stuga.id == id)
  })
  return {
    stugor,
    loading,
    getStugor,
    postStuga,
    updateStuga,
    deleteStuga,
    getStugaById
  }
})
