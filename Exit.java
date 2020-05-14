package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class Exit extends Command{

		private GameWorld gw;
		private ClosingApp Exit;
		public Exit(String command, GameWorld gw){
			super(command);
			this.gw = gw;
		}
		
		private class ClosingApp extends Form{
			ClosingApp(){
			this.setTitle("Exit");
			Boolean bOk = Dialog.show("Confirm Exit", "Are you sure you want to Exit the game?", "Yes", "No");
			if (bOk)
				gw.exit();
			}
		}
		
		public void actionPerformed(ActionEvent e) {
		
			Exit = new ClosingApp();
			
			
		}

}
		
