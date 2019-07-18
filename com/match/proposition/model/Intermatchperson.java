package com.match.proposition.model;

import java.util.Date;

public class Intermatchperson {

	 private Long id;

	    private Long creator;

	    private Long modifier;

	    private Date ctime;

	    private Date mtime;

	    private long intermatchid;

	    private long  personid;
	    
	    private long competitionid;
	    
	    

		public long getCompetitionid() {
			return competitionid;
		}

		public void setCompetitionid(long competitionid) {
			this.competitionid = competitionid;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getCreator() {
			return creator;
		}

		public void setCreator(Long creator) {
			this.creator = creator;
		}

		public Long getModifier() {
			return modifier;
		}

		public void setModifier(Long modifier) {
			this.modifier = modifier;
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

		public long getIntermatchid() {
			return intermatchid;
		}

		public void setIntermatchid(long intermatchid) {
			this.intermatchid = intermatchid;
		}

		public long getPersonid() {
			return personid;
		}

		public void setPersonid(long personid) {
			this.personid = personid;
		}
	    
	    
}
