package wartimeline;

import javax.swing.JFrame;

/**
 * @author Matthew Holland ST20064644
 *
 */
public class Run {

	public static void createMenu() {
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setBounds(100, 100, 600, 600);
		menu.pack();
		menu.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createMenu();
	}
}
