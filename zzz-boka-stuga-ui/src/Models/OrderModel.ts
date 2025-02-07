import { StugaHamta } from "./StugaModel";

export interface Order{
    bokningTom: string,
    bokningFrom: string,
    kundId: string,
    stugId: StugaHamta
}