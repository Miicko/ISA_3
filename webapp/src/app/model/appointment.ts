import { Company } from "./company";

export class Appointment {
    id : any;
    start : Date;
    end : Date;
    reserved : boolean;
    userId : number;
    cid: number;
    done : boolean;
}
