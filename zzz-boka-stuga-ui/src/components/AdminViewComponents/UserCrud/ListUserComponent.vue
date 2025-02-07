<template>
    <div>
        <div class="border m-4 p-4 border-black flex flex-col gap-4">
            <div class="flex gap-4 items-center">
                <h4 class="text-2xl">Användare</h4>
                <button class="border border-black p-2 flex items-center gap-2" @click="showModalCreate = true"><font-awesome-icon icon="fa-solid fa-user-plus" />Skapa användare</button>  
            </div>
            <table class="mt-4">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Förnamn</th>
                        <th>Efternamn</th>
                        <th>Email</th>
                        <th>Telefonnummer</th>
                        <th>Adress</th>
                        <th>Postnummer</th>
                        <th>Postort</th>
                        <th>Behörighet</th>
                        <th>Åtgärder</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="ANVANDARE in storeKund.kunder">
                        <td>{{ ANVANDARE.id }}</td>
                        <td>{{ ANVANDARE.namn }}</td>
                        <td>{{ ANVANDARE.efternamn }}</td>
                        <td>{{ ANVANDARE.email }}</td>
                        <td>{{ ANVANDARE.telefonnummer }}</td>
                        <td>{{ ANVANDARE.adress }}</td>
                        <td>{{ ANVANDARE.postnummer }}</td>
                        <td>{{ ANVANDARE.postort }}</td>
                        <td>{{ ANVANDARE.grupp }}</td>
                        <td>
                            <div class="flex justify-evenly">
                                <button @click="updateUser(ANVANDARE)">
                                    <font-awesome-icon icon="fa-solid fa-pen"/>
                                </button>
                                <button @click="deleteConfirmation(ANVANDARE.id)">
                                    <font-awesome-icon icon="fa-solid fa-trash"/>
                                </button>
                            </div>
                            
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <Teleport to="body">
        <CreateModalUserComponent v-model:show="showModalCreate" @cancel="showModalCreate = false"/>
        <DeleteConfirmationComponent :show="showModalDelete" @delete="deleteUser(activeUser)" @close="showModalDelete = false">
            <template #title>Vill du ta bort användaren?</template>
        </DeleteConfirmationComponent>
        <UpdateModalUsersComponent :show="showModalUpdate" @close="showModalUpdate = false" v-model:user="activeUser"/>
    </Teleport>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useKund } from '../../../store/KundStore';
import UpdateModalUsersComponent from './UpdateModalUsersComponent.vue';
import DeleteConfirmationComponent from './DeleteConfirmationComponent.vue';
import CreateModalUserComponent from './CreateModalUserComponent.vue';

const storeKund = useKund()

const showModalUpdate = ref(false)
const showModalDelete = ref(false)
const showModalCreate = ref(false)
const activeUser = ref()

function updateUser(user:Object){
    activeUser.value = user
    showModalUpdate.value = true
}
function deleteConfirmation(userId:string){
    activeUser.value = userId
    showModalDelete.value = true
}

function deleteUser(userId:string){
    storeKund.deleteKund(userId)
    showModalDelete.value = false
}
</script>

<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  text-wrap: wrap;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>