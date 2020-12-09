package wartimeline;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * @author Matthew Holland ST20064644
 * 
 *
 */
public class Timeline extends JFrame{
	private static final long serialVersionUID = 1L;
	static ArrayList<WarTemplate> Battle = new ArrayList<WarTemplate>();
	static ArrayList<WorldLeaders> Figures = new ArrayList<WorldLeaders>();
	static ArrayList<WarTemplate> Current = new ArrayList<WarTemplate>();
	
	private JLabel 			lbl_BattleName, lbl_Location, lbl_Deaths, lbl_Year, 
							lbl_Duration, lbl_YearSlider, lbl_RecordSlider, lbl_RelatedFigure, 
							lbl_LeaderName, lbl_Country, lbl_BirthYear, lbl_DeathYear,
							lbl_Description, lbl_FigDesc, lbl_FigIMG;
	
	private JTextField 		txt_BattleName, txt_Location, txt_Deaths, 
							txt_Year, txt_Duration, txt_RelatedFigure,
							txt_LeaderName, txt_Country, txt_BirthYear, txt_DeathYear;
	
	private JTextArea		txtArea_Description, txtArea_FigDesc;
	
	private static JButton 	btn_NewBattle;
	
	private JButton 		btn_SaveClose, btn_DeleteBattle, btn_UpdateBattle, btn_CreateFigure;
	
	private ImageIcon		ImgIcon_FigImage;

	
	private JSlider 		sldr_YearSlider, sldr_RecordSlider;
	
	int 					startyear, endyear, index, 
							NOR, count, StartYearDay, EndYearDay, Index;
	
	static JFrame 			Error;
	
	String 					filename;
	
	/**
	 * @param title
	 * @param startyear
	 * @param endyear
	 */

	public Timeline(String title, int startyear, int endyear) 
		{
		super(title + " Timeline"); //Sets the title of the window
		StartYearDay = startyear * 365; //Converts the Start Year into Days for Validation
		EndYearDay = endyear * 365; //Converts the End Year into Days for Validation
		filename = title; //uses the title enter by the user as the file name
		index = 0; // makes the global variable = 0
		this.startyear = startyear; //puts the local variable start year into a global variable
		this.endyear = endyear; //puts the local variable end year into a global variable
		
		JPanel contentpane = new JPanel();  //Creates a JPanel makes it layout GridBagLayout and then sets it as the content pane
		contentpane.setLayout(new GridBagLayout());
		setContentPane(contentpane);
		
		//------------------------------------------------------Label declarations
		lbl_BattleName = new JLabel("Battle Name");
		lbl_BattleName.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Location = new JLabel("Location");
		lbl_Location.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Deaths = new JLabel("Fatalities");
		lbl_Deaths.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Year = new JLabel("Year");
		lbl_Year.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Duration = new JLabel("Duration (Days)");
		lbl_Duration.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_RelatedFigure = new JLabel("Related Figure");
		lbl_RelatedFigure.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_YearSlider = new JLabel("Year");
		lbl_YearSlider.setHorizontalAlignment(JLabel.CENTER);
		
		lbl_RecordSlider = new JLabel("Records This Year");
		lbl_RecordSlider.setHorizontalAlignment(JLabel.CENTER);
		
		lbl_LeaderName = new JLabel("Leader Name");
		lbl_LeaderName.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Country = new JLabel("Country");
		lbl_Country.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_BirthYear = new JLabel("Birth Year");
		lbl_BirthYear.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_DeathYear = new JLabel("Death Year");
		lbl_DeathYear.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Description = new JLabel("Description");
		lbl_Description.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_FigDesc = new JLabel("Description");
		lbl_FigDesc.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_FigIMG = new JLabel();
		//---------------------------------------------------------End of Label declarations
		
		//---------------------------------------------------------JTextField Declarations
		txt_BattleName = new JTextField(20);
		txt_Location = new JTextField(20);
		txt_Deaths = new JTextField(20);
		txt_Year = new JTextField(20);
		txt_RelatedFigure = new JTextField(20);
		
		txt_LeaderName = new JTextField(20);
		txt_Country = new JTextField(20);
		txt_BirthYear = new JTextField(20);
		txt_DeathYear = new JTextField(20);
		txt_Year.setEditable(false);
		
		txt_Duration = new JTextField(20);
		
		txtArea_Description = new JTextArea(5, 20);
		txtArea_Description.setLineWrap(true);
		
		txtArea_FigDesc = new JTextArea(5, 20);
		txtArea_FigDesc.setLineWrap(true);
		//---------------------------------------------------------End of JTextField Declarations
		
		//---------------------------------------------------------JButton Declarations
		btn_NewBattle = new JButton("New Battle");
		btn_SaveClose = new JButton("Save & Close");
		btn_SaveClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveBattlexml(); //Saves all array data to files and clears arrays
				Battle.clear();
				Current.clear();
				Figures.clear();
			}
		});
		btn_SaveClose.addActionListener(e -> this.dispose()); //Closes Timeline window
		
		btn_DeleteBattle = new JButton("Delete Battle"); 
		btn_DeleteBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Removes array item based on index and then clears fields
				Battle.remove(index);
				txt_BattleName.setText("");
				txt_Location.setText("");
				txt_Deaths.setText("");
				txt_Duration.setText("");
				txt_RelatedFigure.setText("");
				txtArea_Description.setText("");
			}
		});
		
		btn_UpdateBattle = new JButton("Update");
		btn_UpdateBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //updates array items based on index
				Battle.get(index).setBattleName(txt_BattleName.getText());
				Battle.get(index).setLocation(txt_Location.getText());
				Battle.get(index).setDeath(Integer.parseInt(txt_Deaths.getText()));
				Battle.get(index).setDuration(Integer.parseInt(txt_Duration.getText()));
				Battle.get(index).setDescription(txtArea_Description.getText());
			}
		});
		
		btn_CreateFigure = new JButton("Create Leader");
		btn_CreateFigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateFigureGUI();  //creates window where a new figure can be created
			}
		});
		//--------------------------------------------------------------------End of JButton Declarations
		
		if(Figures.isEmpty()) {
			btn_NewBattle.setEnabled(false); //if there are no figures current in the array user cannot create a battle
		}
		if(Battle.isEmpty()) {
		ArrayisEmpty(); //moves to routine that performs commands specific to a new timeline
		}
		
		else if (Battle.size() > 0) { // if this is a loaded file
		sldr_YearSlider = new JSlider(JSlider.HORIZONTAL, startyear, endyear, Battle.get(0).getDate()); //sets the start and end year to equal what was in the file
		txt_BattleName.setText(Battle.get(0).getBattleName());	//sets view pane to display first item in array list
		txt_Location.setText(Battle.get(0).getLocation());
		txt_Deaths.setText(Integer.toString(Battle.get(0).getDeath()));
		txt_Year.setText(Integer.toString(Battle.get(0).getDate()));
		txt_Duration.setText(Integer.toString(Battle.get(0).getDuration()));
		txt_RelatedFigure.setText(Battle.get(0).getFigure());
		txtArea_Description.setText(Battle.get(0).getDescription());
		sldr_RecordSlider = new JSlider(JSlider.HORIZONTAL, 0, NOR, 0); //sets record slider max to equal the amount of records on that particular year
		for(int fig = 0; fig < Figures.size(); fig++) //for loop for figure array
		{
		if(Battle.get(0).getFigure().equals(Figures.get(fig).getLeaderName())) { //checks that the figure exists in the figure array so it can be outputted
			txt_LeaderName.setText(Figures.get(fig).getLeaderName());
			txt_Country.setText(Figures.get(fig).getCountry());
			txt_BirthYear.setText(Integer.toString(Figures.get(fig).getBirth()));
			txt_DeathYear.setText(Integer.toString(Figures.get(fig).getDeath()));
			txtArea_FigDesc.setText(Figures.get(fig).getDescription());
			
			if(Figures.get(fig).getImageName() != "") { //checks that an image was uploaded with the figure
				ImgIcon_FigImage = CreateScaleIMG(Figures.get(fig).getImageName()); //creates the imageicon and scales it
				lbl_FigIMG.setIcon(ImgIcon_FigImage);//sets the image inside a JLabel
				pack();//rearrange the window to accomodate for the newly created image
			}
			
			}
		}
		}
		
		sldr_YearSlider.setMajorTickSpacing(5); //sets the major tick spacing to 5
		sldr_YearSlider.setMinorTickSpacing(1); //minor ticks to every year
		sldr_YearSlider.setSnapToTicks(true); //changes the slider to snap to points
		sldr_YearSlider.setPaintTicks(true); 
		sldr_YearSlider.setPaintLabels(true);
		sldr_YearSlider.addChangeListener(new ChangeListener() 
			{

			public void stateChanged(ChangeEvent arg0) 
				{
				count = 0; //count used to make sure what is being searched for doesn't exist
				int SliderValue = sldr_YearSlider.getValue(); // slider value to be used in statements
				NOR = -1; //NOR is Number of records, used to count how many records are on each year.
				txt_Year.setText(Integer.toString(sldr_YearSlider.getValue())); // sets the year from string to int to be compared
				if(!Current.isEmpty()){ //checks that the temp array is empty so that only the correct records appear
				Current.clear();	//empties array
				}
				
				for(int i = 0; i < Battle.size(); i++)  
					{
					if(SliderValue == Battle.get(i).getDate()) //checks that the slider value matches something in the battle array 
						{
						
						//copies the battle found into the current array so that sldr_RecordSlider only has to go through the ones that it needs to
						WarTemplate NewBattle = new WarTemplate(Battle.get(i).getBattleName(), Battle.get(i).getLocation(), Battle.get(i).getFigure(), Battle.get(i).getDescription(), Battle.get(i).getDeath(), Battle.get(i).getDate(), Battle.get(i).getDuration());
						Current.add(NewBattle);
						txt_BattleName.setText(Current.get(0).getBattleName()); //displays the first record in the current array
						txt_Location.setText(Current.get(0).getLocation());
						txt_Deaths.setText(Integer.toString(Current.get(0).getDeath()));
						txt_Year.setText(Integer.toString(Current.get(0).getDate()));
						txt_Duration.setText(Integer.toString(Current.get(0).getDuration()));	
						txt_RelatedFigure.setText(Current.get(0).getFigure());
						txtArea_Description.setText(Current.get(0).getDescription());
						for(int fig = 0; fig < Figures.size(); fig++) 
							{
							if(Current.get(0).getFigure().equals(Figures.get(fig).getLeaderName())) {
								txt_LeaderName.setText(Figures.get(fig).getLeaderName()); //sets the first records figure to be viewed
								txt_Country.setText(Figures.get(fig).getCountry());
								txt_BirthYear.setText(Integer.toString(Figures.get(fig).getBirth()));
								txt_DeathYear.setText(Integer.toString(Figures.get(fig).getDeath()));
								txtArea_FigDesc.setText(Figures.get(fig).getDescription());
								
								if(Figures.get(fig).getImageName() != "") {
									
									//creates image for figure
									ImgIcon_FigImage = CreateScaleIMG(Figures.get(fig).getImageName());
									lbl_FigIMG.setIcon(ImgIcon_FigImage);
									pack();
								}
								}
							}

						NOR++; //counts every time a record is found
						}
					else 
						{
						count++;
						}
					}
				if(count == Battle.size()) //if the count variable equals the size of the array the year was not found
					{
					
					//empties all fields in the view area
					txt_BattleName.setText("");
					txt_Location.setText("");
					txt_Deaths.setText("");
					txt_Duration.setText("");
					txt_RelatedFigure.setText("");
					txt_LeaderName.setText("");
					txt_Country.setText("");
					txt_BirthYear.setText("");
					txt_DeathYear.setText("");
					txtArea_Description.setText("");
					txtArea_FigDesc.setText("");
					lbl_FigIMG.setIcon(null);
					pack();
					}
				if(NOR > -1)
					{
					sldr_RecordSlider.setMaximum(NOR);	
					}
				
				}
			});
		//slider settings, this slider is used to run through the current array, which holds all the records for a given year
		sldr_RecordSlider.setMajorTickSpacing(1);
		sldr_RecordSlider.setPaintTicks(true);
		sldr_RecordSlider.setPaintLabels(true);
		sldr_RecordSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				//set the view based on the record slider value
				txt_BattleName.setText(Current.get(sldr_RecordSlider.getValue()).getBattleName());
				txt_Location.setText(Current.get(sldr_RecordSlider.getValue()).getLocation());
				txt_Deaths.setText(Integer.toString(Current.get(sldr_RecordSlider.getValue()).getDeath()));
				txt_Duration.setText(Integer.toString(Current.get(sldr_RecordSlider.getValue()).getDuration()));
				txt_RelatedFigure.setText(Current.get(sldr_RecordSlider.getValue()).getFigure());
				txtArea_Description.setText(Current.get(sldr_RecordSlider.getValue()).getDescription());
				for(int fig = 0; fig < Figures.size(); fig++) 
				{
				if(Current.get(sldr_RecordSlider.getValue()).getFigure().equals(Figures.get(fig).getLeaderName())) {
					//sets the figure based on what is being outputted from the current array
					txt_LeaderName.setText(Figures.get(fig).getLeaderName());
					txt_Country.setText(Figures.get(fig).getCountry());
					txt_BirthYear.setText(Integer.toString(Figures.get(fig).getBirth()));
					txt_DeathYear.setText(Integer.toString(Figures.get(fig).getDeath()));
					txtArea_FigDesc.setText(Figures.get(fig).getDescription());
					
					if(Figures.get(fig).getImageName() != "") {
						//create figure image if one exists
						ImgIcon_FigImage = CreateScaleIMG(Figures.get(fig).getImageName());
						lbl_FigIMG.setText("");
						lbl_FigIMG.setIcon(ImgIcon_FigImage);
						pack();
					}
					}
				}
				
			}
		});
		txt_Year.setText(Integer.toString(sldr_YearSlider.getValue()));
		
		btn_NewBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateBattleGUI(sldr_YearSlider.getValue()); //creates a window where a new battle can be created
			}
		});
		
		//constraints to add the components to a x/y grid using GridBagLayout
		GridBagConstraints Constraint = new GridBagConstraints();
		Constraint.fill = GridBagConstraints.HORIZONTAL;
		Constraint.insets = new Insets(10, 5, 10, 5);
		
		Constraint.gridx = 0;
		Constraint.gridy = 0;
		contentpane.add(lbl_BattleName, Constraint);
		
		
		Constraint.gridx = 0;
		Constraint.gridy = 1;
		contentpane.add(lbl_Location, Constraint);
		
		Constraint.gridx = 0;
		Constraint.gridy = 2;
		contentpane.add(lbl_Deaths, Constraint);
		
		Constraint.gridx = 0;
		Constraint.gridy = 3;
		contentpane.add(lbl_Year, Constraint);
		
		Constraint.gridx = 0;
		Constraint.gridy = 4;
		contentpane.add(lbl_Duration, Constraint);
		
		Constraint.gridx = 0;
		Constraint.gridy = 5;
		contentpane.add(lbl_RelatedFigure, Constraint);
		
		Constraint.gridx = 0;
		Constraint.gridy = 6;
		contentpane.add(lbl_Description, Constraint);
		
		Constraint.gridx = 4;
		Constraint.gridy = 0;
		contentpane.add(lbl_LeaderName, Constraint);

		Constraint.gridx = 4;
		Constraint.gridy = 1;
		contentpane.add(lbl_Country, Constraint);
		
		Constraint.gridx = 4;
		Constraint.gridy = 2;
		contentpane.add(lbl_BirthYear, Constraint);
		
		Constraint.gridx = 4;
		Constraint.gridy = 3;
		contentpane.add(lbl_DeathYear, Constraint);
		
		Constraint.gridx = 4;
		Constraint.gridy = 4;
		contentpane.add(lbl_FigDesc, Constraint);
		
		Constraint.gridheight = 5;
		Constraint.gridx = 6;
		Constraint.gridy = 0;
		contentpane.add(lbl_FigIMG, Constraint);
		
		Constraint.gridheight = 1;
		Constraint.gridx = 1;
		Constraint.gridy = 0;
		contentpane.add(txt_BattleName, Constraint);
		
		Constraint.gridx = 1;
		Constraint.gridy = 1;
		contentpane.add(txt_Location, Constraint);
		
		Constraint.gridx = 1;
		Constraint.gridy = 2;
		contentpane.add(txt_Deaths, Constraint);
		
		Constraint.gridx = 1;
		Constraint.gridy = 3;
		contentpane.add(txt_Year, Constraint);
		
		Constraint.gridx = 1;
		Constraint.gridy = 4;
		contentpane.add(txt_Duration, Constraint);
		
		Constraint.gridx = 1;
		Constraint.gridy = 5;
		contentpane.add(txt_RelatedFigure, Constraint);
		
		Constraint.gridx = 1;
		Constraint.gridy = 6;
		contentpane.add(txtArea_Description, Constraint);
		
		Constraint.gridx = 5;
		Constraint.gridy = 0;
		contentpane.add(txt_LeaderName, Constraint);
		
		Constraint.gridx = 5;
		Constraint.gridy = 1;
		contentpane.add(txt_Country, Constraint);
		
		Constraint.gridx = 5;
		Constraint.gridy = 2;
		contentpane.add(txt_BirthYear, Constraint);
		
		Constraint.gridx = 5;
		Constraint.gridy = 3;
		contentpane.add(txt_DeathYear, Constraint);
		
		Constraint.gridx = 5;
		Constraint.gridy = 4;
		contentpane.add(txtArea_FigDesc, Constraint);
		
		Constraint.gridx = 2;
		Constraint.gridy = 0;
		contentpane.add(btn_NewBattle, Constraint);
		
		Constraint.gridx = 3;
		Constraint.gridy = 0;
		contentpane.add(btn_SaveClose, Constraint);
		
		Constraint.gridx = 2;
		Constraint.gridy = 1;
		contentpane.add(btn_DeleteBattle, Constraint);
		
		Constraint.gridx = 2;
		Constraint.gridy = 2;
		contentpane.add(btn_UpdateBattle, Constraint);
		
		Constraint.gridx = 3;
		Constraint.gridy = 1;
		contentpane.add(btn_CreateFigure, Constraint);
		
		Constraint.gridx = 0;
		Constraint.gridy = 7;
		Constraint.gridwidth = 8;
		contentpane.add(lbl_YearSlider, Constraint);
		
		Constraint.gridheight = 2;
		Constraint.gridx = 0;
		Constraint.gridy = 8;
		contentpane.add(sldr_YearSlider, Constraint);
		
		Constraint.gridx = 2;
		Constraint.gridy = 4;
		Constraint.gridwidth = 2;
		contentpane.add(sldr_RecordSlider, Constraint);
		
		Constraint.gridx = 2;
		Constraint.gridy = 3;
		contentpane.add(lbl_RecordSlider, Constraint);
	}
	
	public void CreateFigureGUI() {
		//creates a window so that a new figure can be created
		NewWorldLeader Leader = new NewWorldLeader(startyear, endyear);
		Leader.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Leader.setBounds(100, 100, 600, 600);
		Leader.pack();
		Leader.setVisible(true);
	}
	
	/**
	 * @param SliderValue
	 */
	public void CreateBattleGUI(int SliderValue) {
		//creates a new window so that a new battle can be created
		CreateEvent NewBattle = new CreateEvent(filename, startyear, endyear, SliderValue);
		NewBattle.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		NewBattle.setBounds(100, 100, 600, 600);
		NewBattle.pack();
		NewBattle.setVisible(true);
	}

	public void saveBattlexml() {
		try 
			{
			
			//saves all the data to an xml file
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
		
			//creates root element
			Element root = doc.createElement(filename);
			doc.appendChild(root);
			//sets the start year and end year as attributes to the root element because they are only needed
			//once and they will never change
			Attr start = doc.createAttribute("StartYear");
			start.setValue(Integer.toString(startyear));
			root.setAttributeNode(start);
		
			Attr end = doc.createAttribute("EndYear");
			end.setValue(Integer.toString(endyear));
			root.setAttributeNode(end);
			
			//creates secondary element
			for(int i = 0; i < Battle.size(); i++) 
				{
				//creates element Battles for each battle
				Element Battles = doc.createElement("Battle");
				root.appendChild(Battles);
		
				//set attributes for secondary element
				//adds the data from each record in the battle array under their own tags
				Element BattleName = doc.createElement("BattleName");
				BattleName.appendChild(doc.createTextNode(Battle.get(i).getBattleName()));
				Battles.appendChild(BattleName);
		
				Element Location = doc.createElement("Location");
				Location.appendChild(doc.createTextNode(Battle.get(i).getLocation()));
				Battles.appendChild(Location);
				
				Element RelatedFigure = doc.createElement("RelatedFigure");
				RelatedFigure.appendChild(doc.createTextNode(Battle.get(i).getFigure()));
				Battles.appendChild(RelatedFigure);
				
				Element Description = doc.createElement("Description");
				Description.appendChild(doc.createTextNode(Battle.get(i).getDescription()));
				Battles.appendChild(Description);
		
				Element Deaths = doc.createElement("Deaths");
				Deaths.appendChild(doc.createTextNode(Integer.toString(Battle.get(i).getDeath())));
				Battles.appendChild(Deaths);
		
				Element Date = doc.createElement("Date");
				Date.appendChild(doc.createTextNode(Integer.toString(Battle.get(i).getDate())));
				Battles.appendChild(Date);
		
				Element Duration = doc.createElement("Duration");
				Duration.appendChild(doc.createTextNode(Integer.toString(Battle.get(i).getDuration())));
				Battles.appendChild(Duration);
				}

			for(int i = 0; i < Figures.size(); i++){
				
				//creates an element figure for each record in the figure array
				Element Figure = doc.createElement("Figure");
				root.appendChild(Figure);
		
				//set attributes for secondary element
				//adds all the records from the figure array under their own tag
				Element LeaderName = doc.createElement("LeaderName");
				LeaderName.appendChild(doc.createTextNode(Figures.get(i).getLeaderName()));
				Figure.appendChild(LeaderName);
				
				Element Country = doc.createElement("Country");
				Country.appendChild(doc.createTextNode(Figures.get(i).getCountry()));
				Figure.appendChild(Country);
				
				Element Birth = doc.createElement("Birth");
				Birth.appendChild(doc.createTextNode(Integer.toString(Figures.get(i).getBirth())));
				Figure.appendChild(Birth);
				
				Element Death = doc.createElement("Death");
				Death.appendChild(doc.createTextNode(Integer.toString(Figures.get(i).getDeath())));
				Figure.appendChild(Death);
				
				Element FigDesc = doc.createElement("Description");
				FigDesc.appendChild(doc.createTextNode(Figures.get(i).getDescription()));
				Figure.appendChild(FigDesc);
				
				Element IMGPath = doc.createElement("ImagePath");
				IMGPath.appendChild(doc.createTextNode(Figures.get(i).getImageName()));
				Figure.appendChild(IMGPath);
			}
			//changes all the data added into the xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//uses the name entered as the file name
			StreamResult result = new StreamResult(new File(filename + ".xml"));
			
			transformer.transform(source, result);
		
			}
		catch (ParserConfigurationException pce) 
			{
			pce.printStackTrace();
			}
		catch (TransformerException tfe) 
			{
			tfe.printStackTrace();
			}
	}
	
	public void ArrayisEmpty() {
		//commands that happens when the array is empty, makes sets up the sliders, and disables the new battle button until
		//their is a figure present
		sldr_YearSlider = new JSlider(JSlider.HORIZONTAL, startyear, endyear, startyear);	
		sldr_RecordSlider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
		btn_DeleteBattle.setEnabled(false);
	}

	public static void EnableBattleCreation() {
		//after a figure is created make the new battle button enabled
		btn_NewBattle.setEnabled(true);
	}
	
	/**
	 * @param ImgName
	 * @return
	 */
	public ImageIcon CreateScaleIMG(String ImgName) {
		//used to create an image and scale it
		ImageIcon FigIMG = new ImageIcon(); //creates a blank ImageIcon that will be returned when the image is created
		try {

			//Creates the Image based on the file path in the array list
			File ImgFile = new File(ImgName);
			//checks that the file still exists
			if(ImgFile.exists()) {
				//creates a bufferedImage so that the image can be scaled
				BufferedImage TestScale = ImageIO.read(ImgFile);
				//collects the current parameters of the image
				int width = TestScale.getWidth(); 
				int height = TestScale.getHeight();
				int scalewi = 180;
				int scalehei = 250; 
				System.out.println("Picture Width: " + width + "Picture Height: " + height);
				
				if(width==height) {
					scalewi = 250;
					scalehei = 250;
				}
				
				else if(width > height) 
				{
					scalewi = 250;
					scalehei = 180;	
				}
				//creates new parameters but scaled to 20%

					
					//re create the image but with the new height and width and puts it into the ImageIcon FigIMG
					FigIMG = new ImageIcon(TestScale.getScaledInstance(scalewi, scalehei, Image.SCALE_SMOOTH));		
				


			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return FigIMG; //returns the newly formed ImageIcon
	}

}
