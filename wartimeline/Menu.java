package wartimeline;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * 
 * @author Matthew Holland ST20064644
 * 
 *
 */

public class Menu extends JFrame{

	private static final long serialVersionUID = 2L;
	private JButton 	btn_New, btn_Open, btn_Ok, 
						btn_Next, btn_Next2, btn_OpenFile; //Button declarations
	
	private JTextField 	txt_Name, txt_StartText, 
						txt_EndText, txt_FileNameText; //TextField Declarations
	
	private JLabel 		lbl_Welcome, lbl_WarName, lbl_StartYear, 
						lbl_EndYear, lbl_FileNameLabel; //JLabel Declarations
	
	private String 		TimelineTitle; //Global variable to store the title of the timeline
	
	static JFrame 		Error;
	
	
	
	//-----------Menu Constructor for GUI
	public Menu() {

		//--------Create JPanel to hold the components
		JPanel ContentPane = new JPanel();
		ContentPane.setLayout(new GridBagLayout());
		setContentPane(ContentPane);
		
		//--------Creating JLabels
		lbl_Welcome = new JLabel("Welcome to the War TimeLine Creator");
		lbl_Welcome.setHorizontalAlignment(JLabel.CENTER);

		lbl_WarName = new JLabel("War Name: ");
		lbl_WarName.setToolTipText("Name of the War I.E World War 2");
		lbl_WarName.setHorizontalAlignment(JLabel.RIGHT);
		lbl_WarName.setVisible(false);
		
		lbl_FileNameLabel = new JLabel("Enter File Name: ");
		lbl_FileNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		lbl_FileNameLabel.setVisible(false);
		//--------Creating TextField
		txt_Name = new JTextField(20);
		txt_Name.setVisible(false);
		
		txt_StartText = new JTextField(20);
		txt_StartText.setVisible(false);
		
		txt_EndText = new JTextField(20);
		txt_EndText.setVisible(false);
		
		lbl_StartYear = new JLabel("Start Year");
		lbl_StartYear.setVisible(false);
		lbl_StartYear.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_EndYear = new JLabel("End Year");
		lbl_EndYear.setVisible(false);
		lbl_EndYear.setHorizontalAlignment(JLabel.RIGHT);
		
		txt_FileNameText = new JTextField(20);
		txt_FileNameText.setVisible(false);
	//--------------OK button
		btn_Ok = new JButton("OK");
		btn_Ok.setVisible(false);
		btn_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimelineTitle = txt_Name.getText();
				int Format = NewValidation(); //sends the data off for validation
				if(Format == 5) { //if Format is returned as 5 then everything has validated correctly and is ok
					//call CreateTimeline and pass through the name, start year and end year
				CreateTimeLine(TimelineTitle, Integer.parseInt(txt_StartText.getText()), Integer.parseInt(txt_EndText.getText()));	
				}
			}
		});
		
		btn_New = new JButton("New War");
		btn_New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_FileNameLabel.setVisible(false);
				txt_FileNameText.setVisible(false);
				btn_OpenFile.setVisible(false);

				txt_Name.setVisible(true);
				btn_Next.setVisible(true);
				lbl_WarName.setVisible(true);
				pack();
			}
		});
		
		btn_Next = new JButton("Next");
		btn_Next.setVisible(false);
		btn_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_StartYear.setVisible(true);
				txt_StartText.setVisible(true);
				btn_Next2.setVisible(true);
				pack();
			}
		});
		
		btn_Next2 = new JButton("Next");
		btn_Next2.setVisible(false);
		btn_Next2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EndYear.setVisible(true);
				txt_EndText.setVisible(true);
				btn_Ok.setVisible(true);
				pack();

			}
		});
		
		btn_Open = new JButton("Open War");
		btn_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EndYear.setVisible(false);
				txt_EndText.setVisible(false);
				btn_Ok.setVisible(false);
				lbl_StartYear.setVisible(false);
				txt_StartText.setVisible(false);
				btn_Next2.setVisible(false);
				txt_Name.setVisible(false);
				btn_Next.setVisible(false);
				lbl_WarName.setVisible(false);
				lbl_FileNameLabel.setVisible(true);
				txt_FileNameText.setVisible(true);
				btn_OpenFile.setVisible(true);
				pack();
				File workingDirectory = new File(System.getProperty("user.dir"));
				JFileChooser chooser = new JFileChooser(workingDirectory);
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				chooser.setDialogTitle("Open War Timeline");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setFileFilter(xmlfilter);
				chooser.showOpenDialog(getComponent(0));
					File file = chooser.getSelectedFile();
					if(chooser.getSelectedFile() != null) {
						txt_FileNameText.setText(file.getName());	
					}
		




			}
		});
		
		btn_OpenFile = new JButton("Open File");
		btn_OpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//loadFigurexml(txt_FileNameText.getText());
				loadBattlexml(txt_FileNameText.getText());
			}
		});
		btn_OpenFile.setVisible(false);
		
		
		
		GridBagConstraints mConstraint = new GridBagConstraints();
		mConstraint.fill = GridBagConstraints.HORIZONTAL;
		mConstraint.insets = new Insets(10, 5, 10, 5);
		
		mConstraint.anchor = GridBagConstraints.CENTER;
		mConstraint.weighty = 2;
		mConstraint.gridx = 0;
		mConstraint.gridy = 1;
		ContentPane.add(btn_New, mConstraint);
		
		mConstraint.gridx = 2;
		mConstraint.gridy = 1;
		ContentPane.add(btn_Open, mConstraint);
		
		mConstraint.anchor = GridBagConstraints.SOUTH;
		mConstraint.weighty = 1;
		mConstraint.gridx = 1;
		mConstraint.gridy = 2;
		ContentPane.add(txt_Name, mConstraint);
		
		mConstraint.gridx = 0;
		ContentPane.add(lbl_WarName, mConstraint);
		
		mConstraint.gridx = 2;
		ContentPane.add(btn_Next, mConstraint);
		
		mConstraint.gridx = 0;
		mConstraint.gridy = 3;
		ContentPane.add(lbl_StartYear, mConstraint);
		
		mConstraint.gridx = 1;
		mConstraint.gridy = 3;
		ContentPane.add(txt_StartText, mConstraint);
		
		mConstraint.gridx = 2;
		mConstraint.gridy = 3;
		ContentPane.add(btn_Next2, mConstraint);
		
		mConstraint.gridx = 0;
		mConstraint.gridy = 4;
		ContentPane.add(lbl_EndYear, mConstraint);
		
		mConstraint.gridx = 1;
		mConstraint.gridy = 4;
		ContentPane.add(txt_EndText, mConstraint);
		
		mConstraint.gridx = 2;
		mConstraint.gridy = 4;
		ContentPane.add(btn_Ok, mConstraint);
		
		mConstraint.gridx = 0;
		mConstraint.gridy = 2;
		ContentPane.add(lbl_FileNameLabel, mConstraint);
		mConstraint.gridx = 1;
		mConstraint.gridy = 2;
		ContentPane.add(txt_FileNameText, mConstraint);
		mConstraint.gridx = 2;
		mConstraint.gridy = 2;
		ContentPane.add(btn_OpenFile, mConstraint);
		
		
		mConstraint.gridx = 1;
		mConstraint.gridy = 0;
		mConstraint.anchor = GridBagConstraints.NORTH;
		mConstraint.weighty = 1;
		ContentPane.add(lbl_Welcome, mConstraint);

	}
	
	/**
	 * @param WarName
	 * @param StartYear
	 * @param EndYear
	 */
	private static void CreateTimeLine(String WarName, int StartYear, int EndYear) {
		String StartString = Integer.toString(StartYear);
		String EndString = Integer.toString(EndYear);
		if(!WarName.isEmpty() && !StartString.isEmpty() && !EndString.isEmpty()) {
			Timeline TL = new Timeline(WarName, StartYear, EndYear);
			TL.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			TL.setBounds(100, 100, 600, 600);
			TL.pack();
			TL.setVisible(true);	
		}
		else if (WarName.isEmpty() && StartString.isEmpty() && EndString.isEmpty()) {
			JOptionPane.showMessageDialog(Error, "WarName, Start year or end year have not been filled in", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param FileName
	 */
	public void loadBattlexml(String FileName) {
		try {
			
			
			int SYI = 0;
			int EYI = 0;
			File fXmlFile = new File(FileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Battle");
			
			for (int temp = 0; temp< nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String BNS = eElement.getElementsByTagName("BattleName").item(0).getTextContent();
					String LS = eElement.getElementsByTagName("Location").item(0).getTextContent();
					String Figure = eElement.getElementsByTagName("RelatedFigure").item(0).getTextContent();
					String Desc = eElement.getElementsByTagName("Description").item(0).getTextContent();
					int DS = Integer.parseInt(eElement.getElementsByTagName("Deaths").item(0).getTextContent());
					int DAS = Integer.parseInt(eElement.getElementsByTagName("Date").item(0).getTextContent());
					int DUS = Integer.parseInt(eElement.getElementsByTagName("Duration").item(0).getTextContent());
					SYI = Integer.parseInt(doc.getDocumentElement().getAttribute("StartYear"));
					EYI = Integer.parseInt(doc.getDocumentElement().getAttribute("EndYear"));
					WarTemplate Rebuild = new WarTemplate(BNS, LS, Figure, Desc, DS, DAS, DUS);
					Timeline.Battle.add(Rebuild);
				}//end if
			}//end for loop
			
			NodeList Figlist = doc.getElementsByTagName("Figure");
			
			for(int temp = 0; temp< Figlist.getLength(); temp++) {
				Node Fignode = Figlist.item(temp);
				if(Fignode.getNodeType() == Node.ELEMENT_NODE) {
					Element FigElement = (Element) Fignode;
					String LeaderName = FigElement.getElementsByTagName("LeaderName").item(0).getTextContent();
					String Country = FigElement.getElementsByTagName("Country").item(0).getTextContent();
					String Description = FigElement.getElementsByTagName("Description").item(0).getTextContent();
					int Birth = Integer.parseInt(FigElement.getElementsByTagName("Birth").item(0).getTextContent());
					int Death = Integer.parseInt(FigElement.getElementsByTagName("Death").item(0).getTextContent());
					String ImgPath = FigElement.getElementsByTagName("ImagePath").item(0).getTextContent();
					
					WorldLeaders FigRebuild = new WorldLeaders(LeaderName, Country, Description, Birth, Death, ImgPath);
					Timeline.Figures.add(FigRebuild);
				}
			}
			Timeline Window = new Timeline(doc.getDocumentElement().getNodeName(), SYI, EYI);
			Window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			Window.setBounds(100, 100, 600, 600);
			Window.pack();
			Window.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return returnval
	 */
	public int NewValidation() {
		int StartYearInt, EndYearInt;
		int returnval = 0;
		File TestFile = new File(TimelineTitle + ".xml");
		if(!TestFile.exists()){
			if(txt_StartText.getText().matches("[0-9]+") && txt_StartText.getText().length() == 4) 
			{
				
				if(txt_EndText.getText().matches("[0-9]+") && txt_EndText.getText().length() == 4) {
					StartYearInt = Integer.parseInt(txt_StartText.getText());
					EndYearInt = Integer.parseInt(txt_EndText.getText());	
					if(StartYearInt < EndYearInt) {
					returnval = 5;		
					}
					else if(StartYearInt > EndYearInt) {
						JOptionPane.showMessageDialog(Error, "Start Year needs to come before End Year", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (!txt_EndText.getText().matches("[0-9]+") || txt_EndText.getText().length() != 4) {
					JOptionPane.showMessageDialog(Error, "End Year Needs to be 4 Numbers Long", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (!txt_StartText.getText().matches("[0-9]+") || txt_StartText.getText().length() != 4) {
				JOptionPane.showMessageDialog(Error, "Start Year Needs to be 4 Numbers Long", "Error Message", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else if (TestFile.exists()) {
			JOptionPane.showMessageDialog(Error, "A file with the name " + TimelineTitle + " already Exists", "Error Message", JOptionPane.ERROR_MESSAGE);
		}

		
		return returnval;
	}

}
