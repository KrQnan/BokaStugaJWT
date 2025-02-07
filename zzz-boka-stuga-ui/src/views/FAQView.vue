<template>
  <div class="flex flex-col items-center">
    <div class="m-4 flex flex-col w-1/2 gap-2">
      <h2 class="text-4xl font-bold text-center">FAQ</h2>
      <img src="https://mb.cision.com/wpyfs/00/00/00/00/00/18/7C/49/fjallstuga-1.JPG" alt="" class="h-auto max-w-fit">
    </div>
    <div class=" bg-sand w-full p-40 flex flex-col items-center gap-4">
      <h2 class="text-4xl">Välkommen!</h2>
      <p class="text-center m-4 text-xl">Välkommen till vår fjällstugeuthyrning, där äventyr möter avkoppling! Utforska de
        majestätiska fjällen och njut av den friska luften i våra mysiga och fullt utrustade stugor, som erbjuder en
        perfekt bas för både sommar- och vinteraktiviteter. Oavsett om du vill vandra, åka skidor eller bara koppla av
        med en vacker utsikt, är vår stuguthyrning den idealiska destinationen för familj och vänner. Boka din drömstuga
        idag och skapa minnen för livet i fjällens sköna famn!
      </p>
      <div class="w-full flex flex-col gap-2">
        <h2 class="text-4xl text-center">Vanliga frågor <font-awesome-icon icon="fa-solid fa-question" /></h2>
        <div v-for="item in FAQContent" class="border border-black">
          <button type="button" class="collapsible flex justify-between" @click="item.isActive = !item.isActive">
            {{ item.question }}
            <font-awesome-icon icon="fa-solid fa-caret-down" :class="{'rotate-180 transition-all': item.isActive,'transition-all': !item.isActive }" />
          </button>
          <Transition>
            <div class="content" v-if="item.isActive">
              <p>{{ item.answer }}</p>
            </div>
          </Transition>
        </div>
      </div>
    </div>
    <div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const FAQContent = ref([
  {question: "Vilka faciliteter ingår i stugan?", answer: "Stugan är utrustad med fullt kök, värme, badrum, Wi-Fi, och en mysig öppen spis. Vi tillhandahåller även sängkläder och handdukar."},
  {question: "Hur bokar jag stugan?", answer:"Du kan boka stugan direkt via vår webbplats eller kontakta oss via telefon eller e-post. Vi kräver en deposition vid bokning."},
  {question:"Finns det parkering på plats?", answer:"Ja, det finns gratis parkering i nära anslutning till stugan."},
  {question:"Vilka aktiviteter finns det i närheten?", answer:"Området erbjuder skidor, vandring, cykling, fiske och mycket mer - perfekt för friluftsentusiaster!"},
  {question:"Vad är in- och utcheckningstiderna?", answer:"Incheckning är kl. 15:00 och utcheckning senast kl. 11:00. Speciella arrangemang kan göras vid behov."},
  {question:"Är husdjur tillåtna?", answer:"Ja, vi välkomnar husdjur i vissa stugor. Vänligen ange detta vid bokningstillfället."},
  {question:"Finns det avbokningsregler?", answer:"Ja, avbokning kan göras fram till 14 dagar före ankomst för full återbetalning av depositionen. Efter det kan avgifter tillkomma."},
])
</script>

<style scoped>
.collapsible {
  background-color: #f1f1f1;
  color: black;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  text-align: left;
  outline: none;
  font-size: 15px;
}
.content {
  padding: 2rem 18px;
  background-color: #f1f1f1 ;
}
.v-enter-active,
.v-leave-active {
  transition: opacity 0.5s ease-in;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}

</style>
