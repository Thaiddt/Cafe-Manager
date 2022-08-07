package cafeteria;

import cafeteria.constant.Constant;
import cafeteria.view.LoginView;

import javax.swing.*;

public class MainApplication {
	
	public static void main(String[] args) {
		setLookAndFeel();
		LoginView app = new LoginView();
		app.setVisible(true);
	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(Constant.LOOK_AND_FEEL_FLAT_LIGHT_LAF);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
