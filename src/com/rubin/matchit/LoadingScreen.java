package com.rubin.matchit;

import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Screen;

public class LoadingScreen extends Screen {
	
	public LoadingScreen(Game game){
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		//Log.e("e", "e");
		Graphics g = game.getGraphics();
		
		Assets.test = g.newPixmap("test.png", PixmapFormat.ARGB8888);
		
		//font 
		Assets.font = g.newFont("fonts/BONVENOCF-LIGHT.OTF");
		
		//splash screen
		Assets.splashScreenIcon = g.newPixmap("splash_screen/splash_screen_icon.png", PixmapFormat.ARGB4444);
		Assets.splashScreenText = g.newPixmap("splash_screen/splash_screen_text.png", PixmapFormat.ARGB4444);
	
		
		//main menu
		Assets.playUp = g.newPixmap("main_menu/play_up.png", PixmapFormat.ARGB4444);
		Assets.playDown = g.newPixmap("main_menu/play_down.png", PixmapFormat.ARGB4444);
		Assets.settingsUp = g.newPixmap("main_menu/settings_up.png", PixmapFormat.ARGB4444);
		Assets.settingsDown = g.newPixmap("main_menu/settings_down.png", PixmapFormat.ARGB4444);
		Assets.modeUp = g.newPixmap("main_menu/mode_up.png", PixmapFormat.ARGB4444);
		Assets.modeDown = g.newPixmap("main_menu/mode_down.png", PixmapFormat.ARGB4444);
		Assets.statsUp = g.newPixmap("main_menu/stats_up.png", PixmapFormat.ARGB4444);
		Assets.statsDown = g.newPixmap("main_menu/stats_down.png", PixmapFormat.ARGB4444);
		Assets.creditsUp = g.newPixmap("main_menu/credits_up.png", PixmapFormat.ARGB4444);
		Assets.creditsDown = g.newPixmap("main_menu/credits_down.png", PixmapFormat.ARGB4444);
		Assets.themeUp = g.newPixmap("main_menu/theme_up.png", PixmapFormat.ARGB4444);
		Assets.themeDown = g.newPixmap("main_menu/theme_down.png", PixmapFormat.ARGB4444);
		Assets.menuIcon = g.newPixmap("main_menu/menu_icon.png", PixmapFormat.ARGB4444);
		
		//navigation
		Assets.back = g.newPixmap("navigation/back.png", PixmapFormat.ARGB4444);
		Assets.forward = g.newPixmap("navigation/forward.png", PixmapFormat.ARGB4444);
		Assets.play = g.newPixmap("navigation/play.png", PixmapFormat.ARGB4444);
		
		//settings
		Assets.sound = g.newPixmap("settings/settings_sound.png", PixmapFormat.ARGB4444);
		Assets.tutorial = g.newPixmap("settings/settings_tutorial.png", PixmapFormat.ARGB4444);
		Assets.on = g.newPixmap("settings/settings_on.png", PixmapFormat.ARGB4444);
		Assets.off = g.newPixmap("settings/settings_off.png", PixmapFormat.ARGB4444);
		
		//mode
		Assets.straightOn = g.newPixmap("mode/mode_straight_on.png", PixmapFormat.ARGB4444);
		Assets.straightOff = g.newPixmap("mode/mode_straight_off.png", PixmapFormat.ARGB4444);
		Assets.zigzagOn = g.newPixmap("mode/mode_zigzag_on.png", PixmapFormat.ARGB4444);
		Assets.zigzagOff = g.newPixmap("mode/mode_zigzag_off.png", PixmapFormat.ARGB4444);
		Assets.randomOn = g.newPixmap("mode/mode_random_on.png", PixmapFormat.ARGB4444);
		Assets.randomOff = g.newPixmap("mode/mode_random_off.png", PixmapFormat.ARGB4444);
		
		//stats 
		Assets.gamesPlayed = g.newPixmap("stats/stats_games_played.png", PixmapFormat.ARGB4444);
		Assets.highScores = g.newPixmap("stats/stats_high_scores.png", PixmapFormat.ARGB4444);
		
		//credits
		Assets.fontCredit = g.newPixmap("credits_screen/credits_fonts.png", PixmapFormat.ARGB4444);
		Assets.soundCredit = g.newPixmap("credits_screen/credits_sounds.png", PixmapFormat.ARGB4444);
		Assets.developerCredit = g.newPixmap("credits_screen/credits_developer.png", PixmapFormat.ARGB4444);
		
		//directions
		Assets.directions1 = g.newPixmap("directions/directions1.png", PixmapFormat.ARGB4444);
		Assets.directions2 = g.newPixmap("directions/directions2.png", PixmapFormat.ARGB4444);
		Assets.directions3 = g.newPixmap("directions/directions3.png", PixmapFormat.ARGB4444);
		Assets.directions4 = g.newPixmap("directions/directions4.png", PixmapFormat.ARGB4444);
		Assets.directions5 = g.newPixmap("directions/directions5.png", PixmapFormat.ARGB4444);
		
		//play pattern
		Assets.patternCircleBlue = g.newPixmap("play_screen/pattern_circle_blue.png", PixmapFormat.ARGB4444);
		Assets.patternCircleGreen = g.newPixmap("play_screen/pattern_circle_green.png", PixmapFormat.ARGB4444);
		Assets.patternCirclePurple = g.newPixmap("play_screen/pattern_circle_purple.png", PixmapFormat.ARGB4444);
		Assets.patternCircleRed = g.newPixmap("play_screen/pattern_circle_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternDiamondBlue = g.newPixmap("play_screen/pattern_diamond_blue.png", PixmapFormat.ARGB4444);
		Assets.patternDiamondGreen = g.newPixmap("play_screen/pattern_diamond_green.png", PixmapFormat.ARGB4444);
		Assets.patternDiamondPurple = g.newPixmap("play_screen/pattern_diamond_purple.png", PixmapFormat.ARGB4444);
		Assets.patternDiamondRed = g.newPixmap("play_screen/pattern_diamond_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternHeartBlue = g.newPixmap("play_screen/pattern_heart_blue.png", PixmapFormat.ARGB4444);
		Assets.patternHeartGreen = g.newPixmap("play_screen/pattern_heart_green.png", PixmapFormat.ARGB4444);
		Assets.patternHeartPurple = g.newPixmap("play_screen/pattern_heart_purple.png", PixmapFormat.ARGB4444);
		Assets.patternHeartRed = g.newPixmap("play_screen/pattern_heart_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternStarBlue = g.newPixmap("play_screen/pattern_star_blue.png", PixmapFormat.ARGB4444);
		Assets.patternStarGreen = g.newPixmap("play_screen/pattern_star_green.png", PixmapFormat.ARGB4444);
		Assets.patternStarPurple = g.newPixmap("play_screen/pattern_star_purple.png", PixmapFormat.ARGB4444);
		Assets.patternStarRed = g.newPixmap("play_screen/pattern_star_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternTriangleBlue = g.newPixmap("play_screen/pattern_triangle_blue.png", PixmapFormat.ARGB4444);
		Assets.patternTriangleGreen = g.newPixmap("play_screen/pattern_triangle_green.png", PixmapFormat.ARGB4444);
		Assets.patternTrianglePurple = g.newPixmap("play_screen/pattern_triangle_purple.png", PixmapFormat.ARGB4444);
		Assets.patternTriangleRed = g.newPixmap("play_screen/pattern_triangle_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternArrowBlue = g.newPixmap("play_screen/pattern_arrow_blue.png", PixmapFormat.ARGB4444);
		Assets.patternArrowGreen = g.newPixmap("play_screen/pattern_arrow_green.png", PixmapFormat.ARGB4444);
		Assets.patternArrowPurple = g.newPixmap("play_screen/pattern_arrow_purple.png", PixmapFormat.ARGB4444);
		Assets.patternArrowRed = g.newPixmap("play_screen/pattern_arrow_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternHexagonBlue = g.newPixmap("play_screen/pattern_hexagon_blue.png", PixmapFormat.ARGB4444);
		Assets.patternHexagonGreen = g.newPixmap("play_screen/pattern_hexagon_green.png", PixmapFormat.ARGB4444);
		Assets.patternHexagonPurple = g.newPixmap("play_screen/pattern_hexagon_purple.png", PixmapFormat.ARGB4444);
		Assets.patternHexagonRed = g.newPixmap("play_screen/pattern_hexagon_red.png", PixmapFormat.ARGB4444);
		
		Assets.patternOctogonBlue = g.newPixmap("play_screen/pattern_octogon_blue.png", PixmapFormat.ARGB4444);
		Assets.patternOctogonGreen = g.newPixmap("play_screen/pattern_octogon_green.png", PixmapFormat.ARGB4444);
		Assets.patternOctogonPurple = g.newPixmap("play_screen/pattern_octogon_purple.png", PixmapFormat.ARGB4444);
		Assets.patternOctogonRed = g.newPixmap("play_screen/pattern_octogon_red.png", PixmapFormat.ARGB4444);
		
		//play scroll
		Assets.scrollCircleBlueDown = g.newPixmap("play_screen/scroll_circle_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollCircleBlueUp = g.newPixmap("play_screen/scroll_circle_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollCircleGreenDown = g.newPixmap("play_screen/scroll_circle_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollCircleGreenUp = g.newPixmap("play_screen/scroll_circle_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollCirclePurpleDown = g.newPixmap("play_screen/scroll_circle_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollCirclePurpleUp = g.newPixmap("play_screen/scroll_circle_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollCircleRedDown = g.newPixmap("play_screen/scroll_circle_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollCircleRedUp = g.newPixmap("play_screen/scroll_circle_red_up.png", PixmapFormat.ARGB4444);
		
		
		Assets.scrollDiamondBlueDown = g.newPixmap("play_screen/scroll_diamond_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondBlueUp = g.newPixmap("play_screen/scroll_diamond_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondGreenDown = g.newPixmap("play_screen/scroll_diamond_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondGreenUp = g.newPixmap("play_screen/scroll_diamond_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondPurpleDown = g.newPixmap("play_screen/scroll_diamond_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondPurpleUp = g.newPixmap("play_screen/scroll_diamond_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondRedDown = g.newPixmap("play_screen/scroll_diamond_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollDiamondRedUp = g.newPixmap("play_screen/scroll_diamond_red_up.png", PixmapFormat.ARGB4444);
		
		Assets.scrollHeartBlueDown = g.newPixmap("play_screen/scroll_heart_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartBlueUp = g.newPixmap("play_screen/scroll_heart_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartGreenDown = g.newPixmap("play_screen/scroll_heart_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartGreenUp = g.newPixmap("play_screen/scroll_heart_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartPurpleDown = g.newPixmap("play_screen/scroll_heart_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartPurpleUp = g.newPixmap("play_screen/scroll_heart_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartRedDown = g.newPixmap("play_screen/scroll_heart_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHeartRedUp = g.newPixmap("play_screen/scroll_heart_red_up.png", PixmapFormat.ARGB4444);
		
		Assets.scrollStarBlueDown = g.newPixmap("play_screen/scroll_star_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollStarBlueUp = g.newPixmap("play_screen/scroll_star_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollStarGreenDown = g.newPixmap("play_screen/scroll_star_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollStarGreenUp = g.newPixmap("play_screen/scroll_star_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollStarPurpleDown = g.newPixmap("play_screen/scroll_star_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollStarPurpleUp = g.newPixmap("play_screen/scroll_star_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollStarRedDown = g.newPixmap("play_screen/scroll_star_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollStarRedUp = g.newPixmap("play_screen/scroll_star_red_up.png", PixmapFormat.ARGB4444);
		
		Assets.scrollTriangleBlueDown = g.newPixmap("play_screen/scroll_triangle_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollTriangleBlueUp = g.newPixmap("play_screen/scroll_triangle_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollTriangleGreenDown = g.newPixmap("play_screen/scroll_triangle_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollTriangleGreenUp = g.newPixmap("play_screen/scroll_triangle_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollTrianglePurpleDown = g.newPixmap("play_screen/scroll_triangle_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollTrianglePurpleUp = g.newPixmap("play_screen/scroll_triangle_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollTriangleRedDown = g.newPixmap("play_screen/scroll_triangle_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollTriangleRedUp = g.newPixmap("play_screen/scroll_triangle_red_up.png", PixmapFormat.ARGB4444);
		
		Assets.scrollArrowBlueDown = g.newPixmap("play_screen/scroll_arrow_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowBlueUp = g.newPixmap("play_screen/scroll_arrow_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowGreenDown = g.newPixmap("play_screen/scroll_arrow_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowGreenUp = g.newPixmap("play_screen/scroll_arrow_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowPurpleDown = g.newPixmap("play_screen/scroll_arrow_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowPurpleUp = g.newPixmap("play_screen/scroll_arrow_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowRedDown = g.newPixmap("play_screen/scroll_arrow_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollArrowRedUp = g.newPixmap("play_screen/scroll_arrow_red_up.png", PixmapFormat.ARGB4444);
		
		Assets.scrollHexagonBlueDown = g.newPixmap("play_screen/scroll_hexagon_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonBlueUp = g.newPixmap("play_screen/scroll_hexagon_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonGreenDown = g.newPixmap("play_screen/scroll_hexagon_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonGreenUp = g.newPixmap("play_screen/scroll_hexagon_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonPurpleDown = g.newPixmap("play_screen/scroll_hexagon_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonPurpleUp = g.newPixmap("play_screen/scroll_hexagon_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonRedDown = g.newPixmap("play_screen/scroll_hexagon_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollHexagonRedUp = g.newPixmap("play_screen/scroll_hexagon_red_up.png", PixmapFormat.ARGB4444);
		
		Assets.scrollOctogonBlueDown = g.newPixmap("play_screen/scroll_octogon_blue_down.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonBlueUp = g.newPixmap("play_screen/scroll_octogon_blue_up.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonGreenDown = g.newPixmap("play_screen/scroll_octogon_green_down.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonGreenUp = g.newPixmap("play_screen/scroll_octogon_green_up.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonPurpleDown = g.newPixmap("play_screen/scroll_octogon_purple_down.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonPurpleUp = g.newPixmap("play_screen/scroll_octogon_purple_up.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonRedDown = g.newPixmap("play_screen/scroll_octogon_red_down.png", PixmapFormat.ARGB4444);
		Assets.scrollOctogonRedUp = g.newPixmap("play_screen/scroll_octogon_red_up.png", PixmapFormat.ARGB4444);
		
		//sounds
		Assets.tap = game.getAudio().newSound("sounds/tap.wav");
		Assets.error = game.getAudio().newSound("sounds/error.wav");
		
		
		//load the settings file
		Settings.load(game.getFileIO());
		
		//load splash screen
		game.setScreen(new SplashScreen(game));
		/*
		Log.d("current screen", Integer.toString(Settings.currentPage));
		if (Settings.currentPage == Constants.SPLASH_SCREEN)
			game.setScreen(new SplashScreen(game));
		else if (Settings.currentPage == Constants.MAIN_MENU)
			game.setScreen(new MenuScreen(game));
		else if (Settings.currentPage == Constants.PLAY)
			game.setScreen(new PlayScreen(game));
		else if (Settings.currentPage == Constants.SETTINGS)
			game.setScreen(new SettingsScreen(game));
		else if (Settings.currentPage == Constants.MODE)
			game.setScreen(new ModeScreen(game));
		else if (Settings.currentPage == Constants.THEME)
			game.setScreen(new ThemeScreen(game));
		else if (Settings.currentPage == Constants.STATS)
			game.setScreen(new StatsScreen(game));
		else if (Settings.currentPage == Constants.CREDITS)
			game.setScreen(new CreditsScreen(game));
		*/
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
