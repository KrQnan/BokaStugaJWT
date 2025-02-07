import { Kund } from "./KundModel";

export interface hamtaOrderStuga{
    bokningFrom: string,
    bokningTom: string,
    id: string,
    kundId: Kund,
    stugId: string

}