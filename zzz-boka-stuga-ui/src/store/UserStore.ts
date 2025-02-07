import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { Kund } from "../Models/KundModel";
import { useRoute } from "vue-router";
const router = useRoute()
export const useUser = defineStore('user', () => {
    const LoggedInUser = ref()
    const isLoggedIn = ref(false)
    async function login(email:string, password:string){
        try {
            const response = await axios.get('http://localhost:8080/Anvandare/login?email=' + email +'&password=' + password)
            sessionStorage.setItem('token',response.data)
            if(sessionStorage.getItem('token')){
                isLoggedIn.value = true
                getUser()
            }
        } catch (error) {
            console.log(error)
        }
    }
    async function logout(){
        sessionStorage.removeItem('token')
        LoggedInUser.value = undefined
        isLoggedIn.value = false
    }
    async function register(
        typ: string,
        fornamn: string,
        efternamn: string,
        postnummer: string,
        postort: string,
        adress: string,
        email: string,
        telefonnummer: string
      ) {
        try {
          const request = await axios.post<Kund>('http://localhost:8080/Anvandare/register', {
            Typ: typ,
            Fornamn: fornamn,
            Efternamn: efternamn,
            Postnummer: postnummer,
            Postort: postort,
            Adress: adress,
            Email: email,
            Telefonnummer: telefonnummer
          })
          console.log(request)
    
        } catch (error) {
          console.log(error)
        }
    }

    async function getUser(){
        try {
            const response = await axios.get('http://localhost:8080/Anvandare/user',{
                headers:{
                    Authorization: 'Bearer ' + sessionStorage.getItem('token')
                }
            })
            LoggedInUser.value = response.data
        } catch (error) {
            console.log(error)
        }
    }

    return{
        LoggedInUser,
        isLoggedIn,
        login,
        logout,
        register,
        getUser
    }
})