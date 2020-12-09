package wartimeline;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class NewWorldLeader extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	private JTextField 		txt_LeaderName, txt_Country, 
							txt_Birth, txt_Death, txt_ImageName;
	
	private JLabel 			lbl_LeaderName, lbl_Country, lbl_Birth,
							lbl_Death, lbl_Description;
	
	private JButton 		btn_Save, btn_Cancel, btn_PicUpload;
	
	private int 			StartYear, EndYear;
	
	private JTextArea		txtArea_Description;
	
	JFrame 					Error;

	/**
	 * @param StartYear
	 * @param EndYear
	 */
	public NewWorldLeader(int StartYear, int EndYear) {
	super("Create World Leader");
	this.StartYear = StartYear;
	this.EndYear = EndYear;
	
	JPanel FContentPane = new JPanel();
	FContentPane.setLayout(new GridBagLayout());
	setContentPane(FContentPane);
	
	lbl_LeaderName = new JLabel("Leader Name");
	lbl_LeaderName.setHorizontalAlignment(JLabel.RIGHT);
	
	lbl_Country = new JLabel("Country");
	lbl_Country.setHorizontalAlignment(JLabel.RIGHT);
	
	lbl_Birth = new JLabel("Birth Year");
	lbl_Birth.setHorizontalAlignment(JLabel.RIGHT);
	
	lbl_Death = new JLabel("Death Year");
	lbl_Death.setHorizontalAlignment(JLabel.RIGHT);
	
	lbl_Description = new JLabel("Description");
	lbl_Description.setHorizontalAlignment(JLabel.RIGHT);
	
	txt_LeaderName = new JTextField(20);
	
	txt_Country = new JTextField(20);
	
	txt_Birth = new JTextField(20);
	
	txt_Death = new JTextField(20);
	
	txt_ImageName = new JTextField(20);
	
	txtArea_Description = new JTextArea(5, 20);
	txtArea_Description.setLineWrap(true);
	
	btn_Save = new JButton("Save Leader");
	btn_Save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int Validated = FigureValidation();
			if(Validated == 4) {
				String LeaderName = txt_LeaderName.getText();
				String Country = txt_Country.getText();
				String Description = txtArea_Description.getText();
				int Birth = Integer.parseInt(txt_Birth.getText());
				int Death = Integer.parseInt(txt_Death.getText());
				if(!txt_ImageName.getText().isEmpty()) {
				WorldLeaders newFigureIMG = new WorldLeaders(LeaderName, Country, Description, Birth, Death, txt_ImageName.getText());	
				Timeline.Figures.add(newFigureIMG);
				}
				else if(txt_ImageName.getText().isEmpty()) {
				WorldLeaders newFigure = new WorldLeaders(LeaderName, Country, Description, Birth, Death, "");
				Timeline.Figures.add(newFigure);	
				}
				btn_Save.setEnabled(false);
				Timeline.EnableBattleCreation();
				CloseWindow();
			}
		}
	});
	
	btn_Cancel = new JButton("Close");
	btn_Cancel.addActionListener(e -> this.dispose());
	
	btn_PicUpload = new JButton("Upload Picture");
	btn_PicUpload.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UploadImage();
		}
	});
	
	GridBagConstraints F = new GridBagConstraints();
	F.fill = GridBagConstraints.HORIZONTAL;
	F.insets = new Insets(10, 5, 10, 5);
	
	F.gridx = 0;
	F.gridy = 0;
	FContentPane.add(lbl_LeaderName, F);
	
	F.gridx = 1;
	F.gridy = 0;
	FContentPane.add(txt_LeaderName, F);
	
	F.gridx = 0;
	F.gridy = 1;
	FContentPane.add(lbl_Country, F);
	
	F.gridx = 1;
	F.gridy = 1;
	FContentPane.add(txt_Country, F);
	
	F.gridx = 0;
	F.gridy = 2;
	FContentPane.add(lbl_Birth, F);
	
	F.gridx = 1;
	F.gridy = 2;
	FContentPane.add(txt_Birth, F);
	
	F.gridx = 0;
	F.gridy = 3;
	FContentPane.add(lbl_Death, F);
	
	F.gridx = 1;
	F.gridy = 3;
	FContentPane.add(txt_Death, F);
	
	F.gridx = 0;
	F.gridy = 4;
	FContentPane.add(lbl_Description, F);
	
	F.gridx = 1;
	F.gridy = 4;
	FContentPane.add(txtArea_Description, F);
	
	F.gridx = 1;
	F.gridy = 5;
	FContentPane.add(txt_ImageName, F);
	
	F.gridx = 2;
	F.gridy = 0;
	FContentPane.add(btn_Save, F);
	
	F.gridx = 3;
	F.gridy = 0;
	FContentPane.add(btn_Cancel, F);
	
	F.gridx = 0;
	F.gridy = 5;
	FContentPane.add(btn_PicUpload, F);
	}
	
	public int FigureValidation() 
		{
		int Validated = 0;
		int NameExists = 0;
		String BirthString, DeathString;
		int Death, Birth;
		for(int check = 0; check < Timeline.Figures.size(); check++) 
			{
			if(!txt_LeaderName.getText().equalsIgnoreCase(Timeline.Figures.get(check).getLeaderName())) 
				{
				NameExists++;	
				}
			}
		
		
		if(!txt_LeaderName.getText().isEmpty() && !txt_Country.getText().isEmpty() && !txt_Birth.getText().isEmpty() && !txt_Death.getText().isEmpty()) 
			{
			if(NameExists == Timeline.Figures.size()) 
				{
				BirthString = txt_Birth.getText();
				DeathString = txt_Death.getText();
				if(BirthString.matches("[0-9]+") && BirthString.length() == 4) 
					{
					if(DeathString.matches("[0-9]+") && DeathString.length() == 4) 
						{
						Death = Integer.parseInt(DeathString);
						Birth = Integer.parseInt(BirthString);
						if(Birth<Death) 
							{
							if(Death>StartYear) 
								{
								if(Birth<EndYear)
									{
									Validated = 4;	
									}
								else if(Birth>=EndYear) 
									{
									JOptionPane.showMessageDialog(Error, "The Figure cannot be born after the war", "Error Message", JOptionPane.ERROR_MESSAGE);
									}
								}
							else if(Death<=StartYear) 
								{
								JOptionPane.showMessageDialog(Error, "The Figure cannot die before beginning of the war", "Error Message", JOptionPane.ERROR_MESSAGE);
								}
							}
						else if(Death<Birth)
							{
							JOptionPane.showMessageDialog(Error, "The figure cannot die before they are born", "Error Message", JOptionPane.ERROR_MESSAGE);
							}
						}
					else if(!DeathString.matches("[0-9]+") || DeathString.length() != 4) 
						{
						JOptionPane.showMessageDialog(Error, "Death Year Needs to be 4 Numbers Long", "Error Message", JOptionPane.ERROR_MESSAGE);
						}
					}
				else if(!BirthString.matches("[0-9]+") || BirthString.length() != 4) 
					{
					JOptionPane.showMessageDialog(Error, "Birth Year Needs to be 4 Numbers Long", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			else if(NameExists != Timeline.Figures.size()) 
				{
				JOptionPane.showMessageDialog(Error, "That Figure Already Exists", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		else if(txt_LeaderName.getText().isEmpty() || txt_Country.getText().isEmpty() || txt_Birth.getText().isEmpty() || txt_Death.getText().isEmpty()) 
			{
			JOptionPane.showMessageDialog(Error, "All boxes Must be Filled out", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		
			
		return Validated;
		
		}
	
	
	public void CloseWindow() {
		btn_Save.addActionListener(e-> this.dispose());
	}
	
	public File UploadImage() {
		File FileName = null;
		File workingDirectory = new File(System.getProperty("user.dir"));
		JFileChooser IMGchooser = new JFileChooser(workingDirectory);
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("jpg files (*.jpg)", "jpg");
		IMGchooser.setDialogTitle("Open JPEG File");
		IMGchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		IMGchooser.setFileFilter(xmlfilter);
		IMGchooser.showOpenDialog(getComponent(0));
		if(IMGchooser.getSelectedFile() != null) {
			FileName = IMGchooser.getSelectedFile();
			txt_ImageName.setText(FileName.getPath());
		}
		return FileName;
	}
}
