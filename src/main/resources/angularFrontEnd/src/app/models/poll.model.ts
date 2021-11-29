export class Poll {
  adminCode?: number;
  urlCode?: number;
  question?: string;
  askedBy?: string;
  candidates?: Array<string>;
  multipleAnswer?: boolean;
  createdAt?: string;
  requireName?: boolean;
  password?: string;
  published?: boolean;
  ballots?: Array<string>;
  winner?: string; //idk if this is defined in the data at the level of springboot but it should be, makes it easier to display, well, the winner
}
