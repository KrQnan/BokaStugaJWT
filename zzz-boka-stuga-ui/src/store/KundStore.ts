import axios from 'axios'
import { defineStore } from 'pinia'
import { computed, ref } from 'vue'
import {Kund} from '../Models/KundModel'
import { useStuga } from './StugStore'
export const useKund = defineStore('kunder', () => {
  const kunder = ref<Kund[]>()
  const loading = ref(false)
  axios.defaults.headers.put['Authorization'] = 'Bearer ' + sessionStorage.getItem('token')
  async function getKunder() {
    try {
      const response = await axios.get('http://localhost:8080/Kund')
      kunder.value = response.data
    } catch (error) {
      console.error(error)
    }
  }
  getKunder()

  async function postKund(
    grupp: string,
    namn: string,
    efternamn: string,
    postnummer: string,
    postort: string,
    adress: string,
    email: string,
    telefonnummer: string,
    password:string
  ) {
    try {
      const request = await axios.post<Kund>('http://localhost:8080/Kund', {
        grupp: grupp,
        namn: namn,
        efternamn: efternamn,
        postnummer: postnummer,
        postort: postort,
        adress: adress,
        email: email,
        telefonnummer: telefonnummer,
        password: password
      })
      getKunder()

    } catch (error) {
      console.log(error)
    }
  }
  async function updateKund(
    kundId:string,
    typ: string,
    fornamn: string,
    efternamn: string,
    postnummer: string,
    postort: string,
    adress: string,
    email: string,
    telefonnummer: string
  ){
    try {
      const request = await axios.put('http://localhost:8080/Kund/' + kundId, {
        grupp: typ,
        namn: fornamn,
        efternamn: efternamn,
        postnummer: postnummer,
        postort: postort,
        adress: adress,
        email: email,
        telefonnummer: telefonnummer
      })
      const storeStuga = useStuga()
      storeStuga.getStugor()
      console.log(request)
    } catch (error) {
      console.log(error)
    }
  }

  async function deleteKund(kundId:string){
    try {
      const request = await axios.delete('http://localhost:8080/Kund/' + kundId)
      getKunder()
      console.log(request.statusText, request.status)
    } catch (error) {
      console.log(error)
    }
  }
  return {
    kunder,
    loading,
    getKunder,
    postKund,
    updateKund,
    deleteKund
  }
})
