package eu.inn.biosign.device.config.impl;

/*
 * #%L
 * Java Applet for biometric trait acquisition [http://www.biosignin.org]
 * WacomDtuDeviceConfigImpl.java is part of BioSignIn project
 * %%
 * Copyright (C) 2014 Innovery SpA
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */


import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import eu.inn.biosign.device.config.DeviceConfig;

public class WacomDtuDeviceConfigImpl extends DeviceConfig {

	// public static int buttonOkX1 = 280;
	// public static int buttonOkY1 = 420;
	// public static int buttonOkX2 = 420;
	// public static int buttonOkY2 = 460;
	// public static int buttonClearX1 = 446;
	// public static int buttonClearY1 = 420;
	// public static int buttonClearX2 = 586;
	// public static int buttonClearY2 = 462;
	// public static int buttonCancelX1 = 625;
	// public static int buttonCancelY1 = 420;
	// public static int buttonCancelX2 = 765;
	// public static int buttonCancelY2 = 460;


	@Override
	public int getSigThickness() {
		// TODO Auto-generated method stub
		return 3;
	}

	Rectangle retryButton = new Rectangle(725, 700, 220, 75);
	Rectangle okButton = new Rectangle(450, 700, 220, 75);
	Rectangle cancelButton;// = new Rectangle(1008, 700, 220, 75);
	Rectangle nextButton;
	Rectangle prevButton;
@Override
public Rectangle getNextButton() {
	// TODO Auto-generated method stub
	return nextButton;
}
@Override
public Rectangle getPrevButton() {
	// TODO Auto-generated method stub
	return prevButton;
}
@Override
public void setNextButton(Rectangle rect) {
	nextButton=rect;
	// TODO Auto-generated method stub
	
}
@Override
public void setPrevButton(Rectangle rect) {
	prevButton=rect;
	// TODO Auto-generated method stub
	
}
	
	
	@Override
	protected void setCancelButton(Rectangle rect) {
		cancelButton=rect;	
	}
	
	@Override
	protected void setOkButton(Rectangle rect) {
		okButton=rect;
	}

	@Override
	public Rectangle getOkButton() {
		return okButton;
	}
	
	@Override
	public Rectangle getRetryButton() {
		return retryButton;
	}

	@Override
	protected void setRetryButton(Rectangle rect) {
		retryButton = rect;
	}

	@Override
	public Rectangle getCancelButton() {
		return cancelButton;
	}

	@Override
	public Rectangle getActiveAreaForBackground() {
		return activeAreaForBackground;
	}


	Rectangle activeAreaForBackground = new Rectangle(0, 0, 1280, 665);
@Override
protected void setActiveAreaForBackground(Rectangle rect) {
	activeAreaForBackground=rect;
	
	
	
}
	
	
	@Override
	public double getDpiWidth() {
		// TODO Auto-generated method stub
		return 1280 / 8.54;
	}
	@Override
	public Image getButtonsImage() throws IOException {
		if (buttonsImage==null)
			buttonsImage= ImageIO.read(this.getClass().getClassLoader()
				.getResourceAsStream("images/DTU-1031/signWithButtons.png"));
		return buttonsImage;
	}

	Image buttonsImage = null;
	@Override
	protected void setButtonsImage(Image img) {
		buttonsImage=img;
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public boolean isButtonImageFixed() {
		// TODO Auto-generated method stub
		return true;
	}

	// public static final String TEST1_PATH = "classpath:test1.html";
	//
	// public static URL getTest1Url() {
	// try {
	// return ResourceUtils.getURL(TEST1_PATH);
	// } catch (FileNotFoundException e) {
	// throw new RuntimeException(e);
	// }
	// }
	 
	@Override
	protected String getDefaultTemplate() {return "template1";};
	
	@Override
	protected String getTemplatePath() {
		
		return "templates/DTU1031/";
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isAirModeSupported() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Rectangle getAirModeButton() {
		// TODO Auto-generated method stub
		return airModeButton;
	}

	@Override
	public void setAirModeButton(Rectangle area) {
		// TODO Auto-generated method stub
		airModeButton=area;
	}
		Rectangle airModeButton = null;
	
	
}
