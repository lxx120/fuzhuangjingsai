package com.match.proposition.model;

import java.util.Date;

public class Intermatch {

	 	private Long id;

	    private Date ctime;

	    private Date mtime;

	    private int vote;

	    private long  themeworkid;
	    
	    private long  competitionid;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getCtime() {
			return ctime;
		}

		public void setCtime(Date ctime) {
			this.ctime = ctime;
		}

		public Date getMtime() {
			return mtime;
		}

		public void setMtime(Date mtime) {
			this.mtime = mtime;
		}

		public int getVote() {
			return vote;
		}

		public void setVote(int vote) {
			this.vote = vote;
		}

		public long getThemeworkid() {
			return themeworkid;
		}

		public void setThemeworkid(long themeworkid) {
			this.themeworkid = themeworkid;
		}

		public long getCompetitionid() {
			return competitionid;
		}

		public void setCompetitionid(long competitionid) {
			this.competitionid = competitionid;
		}
	    
	    

}
