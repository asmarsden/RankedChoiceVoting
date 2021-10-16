package com.CS495.RankChoiceVoting.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;


import com.CS495.RankChoiceVoting.Model.Vote;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollDTO
{
        private List<Vote> voteList;
        private String pollQuestion;
        
        private boolean require_name;
        
        //private SimpleDateFormat createdAt;

        private String pollCode;

		public List<Vote> getVoteList() {
			return voteList;
		}

		public void setVoteList(List<Vote> voteList) {
			this.voteList = voteList;
		}

		public String getPollQuestion() {
			return pollQuestion;
		}

		public void setPollQuestion(String pollQuestion) {
			this.pollQuestion = pollQuestion;
		}

		public boolean isRequire_name() {
			return require_name;
		}

		public void setRequire_name(boolean require_name) {
			this.require_name = require_name;
		}

		//public SimpleDateFormat getCreatedAt() {
		//	return createdAt;
		//}

		//public void setCreatedAt(SimpleDateFormat createdAt) {
		//	this.createdAt = createdAt;
		//}

		public String getPollCode() {
			return pollCode;
		}

		public void setPollCode(String pollCode) {
			this.pollCode = pollCode;
		}
}