package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CollidedWithBase extends Command{
	private GameWorld gw;
	public CollidedWithBase(String command, GameWorld gw) {
		super(command);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		//this.gw.accelerate(5);
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[]{cOk, cCancel};
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter the title:", myTF, cmds);

		if (c == cOk) {
			switch(myTF.getText()) {

			case "1":
				gw.baseCollision(1);
				break;
			case "2":
				gw.baseCollision(2);
				break;
			case "3":
				gw.baseCollision(3);
				break;
			case "4":
				gw.baseCollision(4);
				break;
			case "5":
				gw.baseCollision(5);
				break;
			case "6":
				gw.baseCollision(6);
				break;
			case "7":
				gw.baseCollision(7);
				break;
			case "8":
				gw.baseCollision(8);
				break;
			case "9":
				gw.baseCollision(9);
				break;
			default:
				System.out.println("Wrong input for base!!!");
				break;
			}
		}
	}
}
