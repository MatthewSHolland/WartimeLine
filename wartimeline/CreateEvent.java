package wartimeline;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("rawtypes")
public class CreateEvent extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private JLabel 		lbl_BattleName, lbl_Location, lbl_Deaths, 
						lbl_Year, lbl_Duration, lbl_Figure, lbl_Description;
	
	private JTextField 	txt_BattleName, txt_Location, 
						txt_Deaths, txt_Year, txt_Duration;
	
	private JComboBox 	FigureBox;
	
	private JButton 	btn_Cancel, btn_SaveBattle;
	
	private JTextArea 	txtArea_Description;

	int 				StartYearDay, EndYearDay;
	
	JFrame 				Error = null;
	

	public CreateEvent(String Title, int StartYear, int EndYear, int SliderValue) {
		super("Create Event");
		StartYearDay = StartYear * 365;
		EndYearDay = EndYear * 365;
		JPanel eContentpane = new JPanel();
		eContentpane.setLayout(new GridBagLayout());
		setContentPane(eContentpane);
		
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
		
		lbl_Figure = new JLabel("Related Figure");
		lbl_Figure.setHorizontalAlignment(JLabel.RIGHT);
		
		lbl_Description = new JLabel("Description");
		lbl_Description.setHorizontalAlignment(JLabel.RIGHT);
		
		txt_BattleName = new JTextField(20);
		
		txt_Location = new JTextField(20);
				
		txt_Deaths = new JTextField(20);
				
		txt_Year = new JTextField(20);	
		txt_Year.setText(Integer.toString(SliderValue));
		txt_Year.setEditable(false);
		
		txt_Duration = new JTextField(20);
		
		txtArea_Description = new JTextArea(5, 20);
		txtArea_Description.setLineWrap(true);
		
		btn_Cancel = new JButton("Close");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_Cancel.addActionListener(e -> this.dispose());
		Populate();
		
		
		
		
		btn_SaveBattle = new JButton("Save Battle");
		btn_SaveBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_BattleName.getText().isEmpty() && !txt_Location.getText().isEmpty() && !txt_Deaths.getText().isEmpty() && !txt_Duration.getText().isEmpty() && !txtArea_Description.getText().isEmpty())
					{
					if(txt_Deaths.getText().matches("[0-9]+")) 
						{
						if(txt_Duration.getText().matches("[0-9]+")) 
							{
							if((StartYearDay + Integer.parseInt(txt_Duration.getText())) <= EndYearDay) {
								WarTemplate NewBattle = new WarTemplate(txt_BattleName.getText(), txt_Location.getText(), String.valueOf(FigureBox.getSelectedItem()), txtArea_Description.getText(), Integer.parseInt(txt_Deaths.getText()), Integer.parseInt(txt_Year.getText()), Integer.parseInt(txt_Duration.getText()));
								Timeline.Battle.add(NewBattle);
								btn_SaveBattle.setEnabled(false);
								pack();			
							}
							else if((StartYearDay + Integer.parseInt(txt_Duration.getText())) > EndYearDay) 
								{
								JOptionPane.showMessageDialog(Error, "The Duration plus year must not exceed the end of the war", "Error Message", JOptionPane.ERROR_MESSAGE);	
								}

							}
						else if(!txt_Duration.getText().matches("[0-9]+")) 
						{
						JOptionPane.showMessageDialog(Error, "Duration must be numbers", "Error Message", JOptionPane.ERROR_MESSAGE);
						}
					}//end if
					else if(!txt_Deaths.getText().matches("[0-9]+")) 
						{
						JOptionPane.showMessageDialog(Error, "Fatalities needs to be a number", "Error Message", JOptionPane.ERROR_MESSAGE);
						}//end else if
					}
				else if(txt_BattleName.getText().isEmpty() || txt_Location.getText().isEmpty() || txt_Deaths.getText().isEmpty() || txt_Duration.getText().isEmpty() || txtArea_Description.getText().isEmpty())
					{
					JOptionPane.showMessageDialog(Error, "All boxes must be filled in", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
		
		
		GridBagConstraints CC = new GridBagConstraints();
		CC.fill = GridBagConstraints.HORIZONTAL;
		CC.insets = new Insets(10, 5, 10, 5);
		CC.gridx = 0;
		CC.gridy = 0;
		eContentpane.add(lbl_BattleName, CC);
		
		CC.gridx = 0;
		CC.gridy = 1;
		eContentpane.add(lbl_Location, CC);
		
		CC.gridx = 0;
		CC.gridy = 2;
		eContentpane.add(lbl_Deaths, CC);
		
		CC.gridx = 0;
		CC.gridy = 3;
		eContentpane.add(lbl_Year, CC);
		
		CC.gridx = 0;
		CC.gridy = 4;
		eContentpane.add(lbl_Duration, CC);
		
		CC.gridx = 0;
		CC.gridy = 5;
		eContentpane.add(lbl_Figure, CC);
		
		CC.gridx = 0;
		CC.gridy = 6;
		eContentpane.add(lbl_Description, CC);
		
		CC.gridx = 1;
		CC.gridy = 0;
		eContentpane.add(txt_BattleName, CC);
		
		CC.gridx = 1;
		CC.gridy = 1;
		eContentpane.add(txt_Location, CC);
		
		CC.gridx = 1;
		CC.gridy = 2;
		eContentpane.add(txt_Deaths, CC);
		
		CC.gridx = 1;
		CC.gridy = 3;
		eContentpane.add(txt_Year, CC);
		
		CC.gridx = 1;
		CC.gridy = 4;
		eContentpane.add(txt_Duration, CC);
		
		CC.gridx = 1;
		CC.gridy = 5;
		eContentpane.add(FigureBox, CC);
		
		CC.gridx = 1;
		CC.gridy = 6;
		eContentpane.add(txtArea_Description, CC);
		
		CC.gridx = 2;
		CC.gridy = 0;
		eContentpane.add(btn_SaveBattle, CC);
		
		CC.gridx = 3;
		CC.gridy = 0;
		eContentpane.add(btn_Cancel, CC);	
	}
	
	@SuppressWarnings("unchecked")
	public void Populate() {
	String[] ComboList = new String[Timeline.Figures.size()];
	if(Timeline.Figures.size() != 0) {
			for(int FI = 0; FI < Timeline.Figures.size(); FI++) {
				ComboList[FI] = Timeline.Figures.get(FI).getLeaderName();
			}
		}
		FigureBox = new JComboBox(ComboList);
	}
}
