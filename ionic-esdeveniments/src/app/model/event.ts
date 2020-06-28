import { User } from './user';

export class Event{
    eventId:string;
    creatorId:string;
    activity:string = "";
    description:string = "";
    location:string = "";
    startTime:string = "";
    endTime:string = "";
    numParticipants: number;
    numEnrolledParticipants: number;
    participants:Array<User>;
}