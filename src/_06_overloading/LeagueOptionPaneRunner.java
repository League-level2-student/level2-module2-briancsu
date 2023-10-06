package _06_overloading;

import java.awt.Color;

public class LeagueOptionPaneRunner {
	public static void main(String[] args) {
		LeagueOptionPane.showMessageDialog("1");
		LeagueOptionPane.showMessageDialog("2","Message");
		LeagueOptionPane.showMessageDialog("3","Message","league.png");
		LeagueOptionPane.showMessageDialog("4","Message","league.png",Color.WHITE);
		
	}
}
