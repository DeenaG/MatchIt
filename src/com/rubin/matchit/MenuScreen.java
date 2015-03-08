package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class MenuScreen extends Screen {	
	//keep track of how often the screen is updated
	private static final float TICK = .005f;
	private float tickTime = 0;
	
	private static final int LABEL_INCREMENT_Y = 200;
	private static final int INITIAL_LABEL_Y = 300;
	

	private Label[] labels = new Label[6];
	private Screen[] screens = {new PlayScreen(game), new SettingsScreen(game), new ModeScreen(game), new ThemeScreen(game), new StatsScreen(game), new CreditsScreen(game)};
	private Pixmap[] pixUp = {Assets.playUp, Assets.settingsUp, Assets.modeUp, Assets.themeUp, Assets.statsUp, Assets.creditsUp};
	private Pixmap[] pixDown = {Assets.playDown, Assets.settingsDown, Assets.modeDown, Assets.themeDown, Assets.statsDown, Assets.creditsDown};

	// screen switch variables
	private boolean menuItemPressed = false;
	private boolean screenSwitch = false;
	private float screenSwitchTime = 0;

	// dimensions of menu choices
	private static final int MENU_WIDTH = 600;
	private static final int MENU_HEIGHT = 150;

	// grid placement of menu choices
	private static final int LABEL_X = 240;
	private static final int ICON_X = 465;
	private static final int ICON_INITIAL_Y = 1670;

	// icon moving variables
	private int currentIconY = ICON_INITIAL_Y;
	private final int ICON_INCREMENT_Y = 10;
	private int iconStopY;

	// alpha value for images
	private static final int ALPHA = 255;

	/**
	 * Constructor
	 * @param game
	 */
	public MenuScreen(Game game) {
		super(game);
		for (int i=0; i<labels.length; i++)
			labels[i] = new Label(pixUp[i]);
	}

	@Override
	public void update(float deltaTime) {
		//clear keyboard events
		game.getInput().getKeyEvents();
				
		// list of touch events since the last update
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		// loop through the list of TouchEvents and check to see if menu item
		// has been pressed
		for (int i = 0; i < touchEvents.size() && !menuItemPressed; i++) {
			TouchEvent event = touchEvents.get(i);
			int y = INITIAL_LABEL_Y;
			if (event.type == TouchEvent.TOUCH_UP) {
				for (int j=0; j<labels.length; j++){
					if(inBounds(event, LABEL_X, y, MENU_WIDTH, MENU_HEIGHT)){
						labels[j].setPressed(true);
						labels[j].setPix(pixDown[j]);
						super.playSound(Assets.tap);
						iconStopY = y;
						menuItemPressed = true;
					}
					y += LABEL_INCREMENT_Y;
				}
				
			}
		}

		// if a menu item has been pressed, start the screen switch process
		if (menuItemPressed){
			tickTime += deltaTime;
			while (tickTime > TICK){
				tickTime -= TICK;
				if (currentIconY - ICON_INCREMENT_Y >= iconStopY)
					currentIconY -= ICON_INCREMENT_Y;
				else{
					currentIconY = iconStopY;
					screenSwitch = true;
				}
			}				
		}

		if (screenSwitch)
			screenSwitchTime += deltaTime;
		
		if (screenSwitchTime >= 0.5) {
			for (int i=0; i<labels.length; i++)
				if(labels[i].isPressed())
					game.setScreen(screens[i]);
		}
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		
		// draw the icon:
		// if a menu item has not been pressed, draw at bottom of screen
		// if menu item has been pressed, move icon to the menu item
		g.drawPixmap(Assets.menuIcon, ICON_X, currentIconY, ALPHA);
		
		// draw the menu items
		// if the menu item has not been pressed, draw the up version
		// if a menu item has been pressed, draw the down version
		int y = INITIAL_LABEL_Y;
		for(int i=0; i<labels.length; i++){
			g.drawPixmap(labels[i].getPix(), LABEL_X, y, ALPHA);
			y+=LABEL_INCREMENT_Y;
		}
	}
	
	

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		Settings.currentPage = Constants.MAIN_MENU;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
