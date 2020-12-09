package wartimeline;

/**
 * @author Matthew Holland ST20064644
 *
 */
public class WarTemplate {

	private String BattleName;
	private String Location;
	private String Figure;
	private String Description;
	private int Death;
	private int Date;
	private int Duration;
	
	
	public WarTemplate() {}
	
	/**
	 * @param BattleName
	 * @param Location
	 * @param Death
	 * @param Date
	 * @param Duration
	 */
	public WarTemplate(String BattleName, String Location, String Figure, String Description, int Death, int Date, int Duration) {
		this.BattleName = BattleName;
		this.Location = Location;
		this.Figure = Figure;
		this.Description = Description;
		this.Death = Death;
		this.Date = Date;
		this.Duration = Duration;
	}
	
	/**
	 * @param BattleName
	 */
	public void setBattleName(String BattleName) {
		this.BattleName = BattleName;
	}
	
	/**
	 * @param Location
	 */
	public void setLocation(String Location) {
		this.Location = Location;
	}
	
	/**
	 * @param Figure
	 */
	public void setFigure(String Figure) {
		this.Figure = Figure;
	}
	
	/**
	 * @param Description
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}
	/**
	 * @param Death
	 */
	public void setDeath(int Death) {
		this.Death = Death;
	}
	
	/**
	 * @param Date
	 */
	public void setDate(int Date) {
		this.Date = Date;
	}
	
	/**
	 * @param Duration
	 */
	public void setDuration(int Duration) {
		this.Duration = Duration;
	}
	
	/**
	 * @return BattleName
	 */
	public String getBattleName() {
		return BattleName;
	}
	
	/**
	 * @return Location
	 */
	public String getLocation() {
		return Location;
	}
	
	public String getFigure() {
		return Figure;
	}
	
	/**
	 * @return Description
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * @return Death
	 */
	public int getDeath() {
		return Death;
	}
	
	/**
	 * @return Date
	 */
	public int getDate() {
		return Date;
	}
	
	/**
	 * @return Duration
	 */
	public int getDuration() {
		return Duration;
	}
}
