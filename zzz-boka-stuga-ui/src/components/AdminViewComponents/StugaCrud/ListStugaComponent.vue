<template>
    <div>
        <div class="border m-4 p-4 border-black flex flex-col gap-4">
            <div class="flex gap-4 items-center">
                <h4 class="text-2xl">Stugor</h4>
                <button class="border border-black p-2 flex items-center gap-2" @click="showModalCreate = true"><font-awesome-icon icon="fa-solid fa-user-plus" />Skapa stuga</button>  
            </div>
            <table class="mt-4">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Namn</th>
                        <th>Bild</th>
                        <th>Beskrivning</th>
                        <th>Ordrar</th>
                        <th>Åtgärder</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="Stuga in storeStuga.stugor">
                        <td>{{ Stuga.id }}</td>
                        <td>{{ Stuga.namn }}</td>
                        <td class="w-40"><img :src="Stuga.bild" class="w-fit"/></td>
                        <td>{{ Stuga.beskrivning }}</td>
                        <td>
                            <div class="flex flex-col w-50 m-2 border-b border-black"  v-for="order in Stuga.ordrar">
                                <button @click.capture="toggle(order)" v-if="order" class="text-start font-semibold">Order # {{ order.id }} <font-awesome-icon icon="fa-solid fa-chevron-down" :class="{'rotate-180 transition': order.isActive,'transition': !order.isActive }" /></button>
                                <Transition>
                                    <div v-if="order.isActive">
                                        <ul>
                                            <li>{{ order.kundId?.namn }}, {{ order.kundId?.efternamn }}</li>
                                            <li>{{ order.bokningFrom }} - {{ order.bokningTom }}</li>
                                        </ul>
                                    </div>
                                </Transition>
                            </div>
                        </td>
                        <td>
                            <div class="flex justify-evenly">
                                <button @click="updateStuga(Stuga)">
                                    <font-awesome-icon icon="fa-solid fa-pen"/>
                                </button>
                                <button @click="deleteConfirmation(Stuga.id)">
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
        <CreateModalStugaComponent v-model:show="showModalCreate" @cancel="showModalCreate = false"/>
        <DeleteConfirmationComponent :show="showModalDelete" @delete="deleteStuga(activeUser)" @close="showModalDelete = false">
            <template #title>Vill du ta bort stugan?</template>
        </DeleteConfirmationComponent>
        <UpdateModalStugorComponent :show="showModalUpdate" @close="showModalUpdate = false" v-model:stuga="activeUser"/>
    </Teleport>
</template>

<script setup lang="ts">
import DeleteConfirmationComponent from './DeleteConfirmationComponent.vue';
import CreateModalStugaComponent from './CreateModalStugaComponent.vue';
import UpdateModalStugorComponent from './UpdateModalStugorComponent.vue';
import { ref } from 'vue';
import { useStuga } from '../../../store/StugStore';


const storeStuga = useStuga()

const showModalUpdate = ref(false)
const showModalDelete = ref(false)
const showModalCreate = ref(false)
const activeUser = ref()
const showText = ref()
function toggle(item: any){
    item.isActive = !item.isActive
}
function updateStuga(stuga:Object){
    activeUser.value = stuga
    showModalUpdate.value = true
}
function deleteConfirmation(userId:string){
    activeUser.value = userId
    showModalDelete.value = true
}

function deleteStuga(stugaId:string){
    console.log(stugaId)
    storeStuga.deleteStuga(stugaId)
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
.v-enter-active,
.v-leave-active {
  transition: opacity 0.5s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>