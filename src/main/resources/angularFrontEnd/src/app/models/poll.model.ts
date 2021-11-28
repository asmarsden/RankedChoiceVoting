export class Poll {
  pollCode?: number;
  pollQuestion?: string;
  askedBy?: string;
  optionList?: Array<string>;
  multipleAnswer?: boolean;
  createdAt?: string;
  require_name?: boolean;
  published?: boolean;
  ballotList?: Array<string>;
  winner?: string; //idk if this is defined in the data at the level of springboot but it should be, makes it easier to display, well, the winner
}
