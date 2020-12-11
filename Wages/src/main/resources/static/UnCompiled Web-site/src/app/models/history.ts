import { Employee } from './employee';

export class History {

    constructor(
        public id: number,
        public employee: Employee,
        public date: Date,
        public payCheck: number
    ) { }
}
