package com.robinsinghdevgan.helper;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperMethods {
	public static void selectRandomOption(WebElement comboMenuElement) {
		Random rand = new Random();
		Select dropDownMenu = null;
		dropDownMenu = new Select(comboMenuElement);
		int max = dropDownMenu.getOptions().size();;
		int min = 1;
		
		dropDownMenu.selectByIndex(rand.nextInt((max - min)) + min);

	}
}
