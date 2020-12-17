public class player {
	
	public int ID, score;
	public String user, pass;
	
	public player(int intID, String strUSer, String strPass, int intScore) {
		this.ID = intID;
		this.user = strUSer;
		this.pass = strPass;
		this.score = intScore;
	}
	
	public String getUsername() {
		return this.user;
	}
	
	public int setScore(int intScore) {
		return this.score += intScore;
	}
	
	public String toString() {
		return ID + " " + user + " " + pass + " " + score;
	}

}
