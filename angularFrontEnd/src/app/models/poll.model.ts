//import { ExecSyncOptionsWithStringEncoding } from "child_process";

export class Poll {
  pollCode?: any;
  pollQuestion?: string;
  askedBy?: string;
  voteList?: Array<string>;
  //idk how to put lists in here ugh
  //i think its actually an array of 'votes' but i dont have that data yet so until then itll be of strings
  createdAt?: string;
  require_name?: boolean;
  published?: boolean;
}
