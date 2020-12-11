export class ArchivedEmployee {

    constructor(
        public id: number,
        public firstName: string,
        public lastName: string,
        public hiringDate: Date,
        public terminationDate: Date,
        public bankAccount: number,
        public salary: number,
        public latestDateOfTransfer: Date,
        public department: string,
        public position: string
    ) { }
}
