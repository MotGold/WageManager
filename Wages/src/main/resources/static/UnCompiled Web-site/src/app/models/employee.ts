export class Employee {

    constructor(
        public id: number,
        public hiringDate: Date,
        public firstName: string,
        public lastName: string,
        public bankAccount: number,
        public salary: number,
        public latestDateOfTransfer: Date,
        public department: string,
        public position: string
    ) { }
}