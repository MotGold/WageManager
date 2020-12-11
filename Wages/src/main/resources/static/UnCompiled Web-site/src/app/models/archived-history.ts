import { ArchivedEmployee } from './archived-employee';

export class ArchivedHistory {

    constructor(
        public id: number,
        public empoleeeId: ArchivedEmployee,
        public date: Date,
        public payCheck: number
    ) { }
}
