package wartimeline;

public class WorldLeaders {
	
	private String LeaderName;
	private String Country;
	private String Description;
	private String ImageName;
	private int BirthYear;
	private int DeathYear;
	
	public WorldLeaders() {}
	
	/**
	 * @param LeaderName
	 * @param Country
	 * @param Description
	 * @param BirthYear
	 * @param DeathYear
	 */
	public WorldLeaders(String LeaderName, String Country, String Description, int BirthYear, int DeathYear) {
	this.LeaderName = LeaderName;
	this.Country = Country;
	this.Description = Description;
	this.BirthYear = BirthYear;
	this.DeathYear = DeathYear;
	}
	
	/**
	 * @param LeaderName
	 * @param Country
	 * @param Description
	 * @param BirthYear
	 * @param DeathYear
	 * @param ImageName
	 */
	public WorldLeaders(String LeaderName, String Country, String Description, int BirthYear, int DeathYear, String ImageName){
		this.LeaderName = LeaderName;
		this.Country = Country;
		this.Description = Description;
		this.BirthYear = BirthYear;
		this.DeathYear = DeathYear;
		this.ImageName = ImageName;
	}
	
	/**
	 * @param LeaderName
	 */
	public void setLeaderName(String LeaderName) {
		this.LeaderName = LeaderName;
	}
	
	/**
	 * @param Country
	 */
	public void setCountry(String Country) {
		this.Country = Country;
	}
	
	/**
	 * @param Description
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	/**
	 * @param BirthYear
	 */
	public void setBirth(int BirthYear) {
		this.BirthYear = BirthYear;
	}
	
	/**
	 * @param DeathYear
	 */
	public void setDeath(int DeathYear) {
		this.DeathYear = DeathYear;
	}
	
	public void setIMGName(String ImageName) {
		this.ImageName = ImageName;
	}
	
	/**
	 * @return LeaderName
	 */
	public String getLeaderName() {
		return LeaderName;
	}
	
	/**
	 * @return Country
	 */
	public String getCountry() {
		return Country;
	}
	
	/**
	 * @return Description
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * @return Birth
	 */
	public int getBirth() {
		return BirthYear;
	}
	
	/**
	 * @return Death
	 */
	public int getDeath() {
		return DeathYear;
	}
	
	/**
	 * @return ImageName
	 */
	public String getImageName() {
		return ImageName;
	}


}
