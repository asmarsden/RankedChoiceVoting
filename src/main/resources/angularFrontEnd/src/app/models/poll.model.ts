//import { ExecSyncOptionsWithStringEncoding } from "child_process";
import { FormGroup, FormControl, FormArray, FormBuilder } from '@angular/forms';


export class Poll {
  //i dont think ill need much of this except the name, codes, and option lists for the angular to work but we'll see 
  pollCode?: number;
  pollQuestion?: string;
  askedBy?: string;
  optionList?: Array<string>;
  multipleAnswer?: boolean;
  createdAt?: string;
  require_name?: boolean;
  published?: boolean;
  ballotList?: Array<string>;
}

export class Ballot {
  ballotId: string;
  name: string;
  ranking: Array<number>;
  parentPollCode: string;
  ballotCode: string;
}

