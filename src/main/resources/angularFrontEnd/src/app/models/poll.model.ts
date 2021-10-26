//import { ExecSyncOptionsWithStringEncoding } from "child_process";
import { FormGroup, FormControl, FormArray, FormBuilder } from '@angular/forms';


export class Poll {
  pollCode?: number;
  pollQuestion?: string;
  askedBy?: string;
  optionList?: Array<string>;
  multipleAnswer?: boolean;
  //idk how to put lists in here ugh
  //i think its actually an array of 'votes' but i dont have that data yet so until then itll be of strings
  createdAt?: string;
  require_name?: boolean;
  published?: boolean;
  voteList?: Vote[];
}

export class Vote {
  voteCode?: number;
  voterName?: string;
  //will add info for vote once that is completed in backend
}