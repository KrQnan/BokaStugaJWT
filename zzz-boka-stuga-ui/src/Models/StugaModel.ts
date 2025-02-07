import { hamtaOrderStuga } from "./OrdrarModels"
export interface StugaSkapa{
    namn:string,
    bokningFrom:string,
    bokningTom:string,
    bild:string,
    beskrivning: string,
    kundId:string
}
export interface StugaHamta{
    id:string,
    pris:string
    namn:string,
    bild:string,
    beskrivning: string,
    ordrar: Array<hamtaOrderStuga>
}