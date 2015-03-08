package com.rubin.matchit;

import com.badlogic.androidgames.framework.Pixmap;

public class PixmapSelection {
	
	/**
	 * Chooses a pixmap of the specified type and color for the pattern at the 
	 * top of the screen
	 * @param type of shape to be selected
	 * @param color color of shape to be selected
	 * @return pixmap of specified type and shape
	 */
	public static Pixmap choosePatternPixmap(int type, int color) {
		if (type == Constants.DIAMOND && color == Constants.BLUE)
			return Assets.patternDiamondBlue;
		else if (type == Constants.DIAMOND && color == Constants.PURPLE)
			return Assets.patternDiamondPurple;
		else if (type == Constants.DIAMOND && color == Constants.GREEN)
			return Assets.patternDiamondGreen;
		else if (type == Constants.DIAMOND && color == Constants.RED)
			return Assets.patternDiamondRed;
		
		else if (type == Constants.CIRCLE && color == Constants.BLUE)
			return Assets.patternCircleBlue;
		else if (type == Constants.CIRCLE && color == Constants.PURPLE)
			return Assets.patternCirclePurple;
		else if (type == Constants.CIRCLE && color == Constants.GREEN)
			return Assets.patternCircleGreen;
		else if (type == Constants.CIRCLE && color == Constants.RED)
			return Assets.patternCircleRed;
		
		else if (type == Constants.STAR && color == Constants.BLUE)
			return Assets.patternStarBlue;
		else if (type == Constants.STAR && color == Constants.PURPLE)
			return Assets.patternStarPurple;
		else if (type == Constants.STAR && color == Constants.GREEN)
			return Assets.patternStarGreen;
		else if (type == Constants.STAR && color == Constants.RED)
			return Assets.patternStarRed;
		
		else if (type == Constants.HEART && color == Constants.BLUE)
			return Assets.patternHeartBlue;
		else if (type == Constants.HEART && color == Constants.PURPLE)
			return Assets.patternHeartPurple;
		else if (type == Constants.HEART && color == Constants.GREEN)
			return Assets.patternHeartGreen;
		else if (type == Constants.HEART && color == Constants.RED)
			return Assets.patternHeartRed;
		
		else if (type == Constants.TRIANGLE && color == Constants.BLUE)
			return Assets.patternTriangleBlue;
		else if (type == Constants.TRIANGLE && color == Constants.PURPLE)
			return Assets.patternTrianglePurple;
		else if (type == Constants.TRIANGLE && color == Constants.GREEN)
			return Assets.patternTriangleGreen;
		else if (type == Constants.TRIANGLE && color == Constants.RED)
			return Assets.patternTriangleRed;
		
		else if (type == Constants.ARROW && color == Constants.BLUE)
			return Assets.patternArrowBlue;
		else if (type == Constants.ARROW && color == Constants.PURPLE)
			return Assets.patternArrowPurple;
		else if (type == Constants.ARROW && color == Constants.GREEN)
			return Assets.patternArrowGreen;
		else if (type == Constants.ARROW && color == Constants.RED)
			return Assets.patternArrowRed;
		
		else if (type == Constants.HEXAGON && color == Constants.BLUE)
			return Assets.patternHexagonBlue;
		else if (type == Constants.HEXAGON && color == Constants.PURPLE)
			return Assets.patternHexagonPurple;
		else if (type == Constants.HEXAGON && color == Constants.GREEN)
			return Assets.patternHexagonGreen;
		else if (type == Constants.HEXAGON && color == Constants.RED)
			return Assets.patternHexagonRed;
		
		else if (type == Constants.OCTOGON && color == Constants.BLUE)
			return Assets.patternOctogonBlue;
		else if (type == Constants.OCTOGON && color == Constants.PURPLE)
			return Assets.patternOctogonPurple;
		else if (type == Constants.OCTOGON && color == Constants.GREEN)
			return Assets.patternOctogonGreen;
		else if (type == Constants.OCTOGON && color == Constants.RED)
			return Assets.patternOctogonRed;
		return null;
	}
	
	/**
	 * Chooses a pixmap of the specified type and color for the initial scrolling
	 * @param type of shape to be selected
	 * @param color color of shape to be selected
	 * @return pixmap of specified type and shape
	 */
	public static Pixmap chooseScrollPixmapUp(int type, int color){
		if (type == Constants.DIAMOND && color == Constants.BLUE)
			return Assets.scrollDiamondBlueUp;
		else if (type == Constants.DIAMOND && color == Constants.PURPLE)
			return Assets.scrollDiamondPurpleUp;
		else if (type == Constants.DIAMOND && color == Constants.GREEN)
			return Assets.scrollDiamondGreenUp;
		else if (type == Constants.DIAMOND && color == Constants.RED)
			return Assets.scrollDiamondRedUp;
		
		else if (type == Constants.CIRCLE && color == Constants.BLUE)
			return Assets.scrollCircleBlueUp;
		else if (type == Constants.CIRCLE && color == Constants.PURPLE)
			return Assets.scrollCirclePurpleUp;
		else if (type == Constants.CIRCLE && color == Constants.GREEN)
			return Assets.scrollCircleGreenUp;
		else if (type == Constants.CIRCLE && color == Constants.RED)
			return Assets.scrollCircleRedUp;
		
		else if (type == Constants.STAR && color == Constants.BLUE)
			return Assets.scrollStarBlueUp;
		else if (type == Constants.STAR && color == Constants.PURPLE)
			return Assets.scrollStarPurpleUp;
		else if (type == Constants.STAR && color == Constants.GREEN)
			return Assets.scrollStarGreenUp;
		else if (type == Constants.STAR && color == Constants.RED)
			return Assets.scrollStarRedUp;
		
		else if (type == Constants.HEART && color == Constants.BLUE)
			return Assets.scrollHeartBlueUp;
		else if (type == Constants.HEART && color == Constants.PURPLE)
			return Assets.scrollHeartPurpleUp;
		else if (type == Constants.HEART && color == Constants.GREEN)
			return Assets.scrollHeartGreenUp;
		else if (type == Constants.HEART && color == Constants.RED)
			return Assets.scrollHeartRedUp;
		
		else if (type == Constants.TRIANGLE && color == Constants.BLUE)
			return Assets.scrollTriangleBlueUp;
		else if (type == Constants.TRIANGLE && color == Constants.PURPLE)
			return Assets.scrollTrianglePurpleUp;
		else if (type == Constants.TRIANGLE && color == Constants.GREEN)
			return Assets.scrollTriangleGreenUp;
		else if (type == Constants.TRIANGLE && color == Constants.RED)
			return Assets.scrollTriangleRedUp;
		
		else if (type == Constants.ARROW && color == Constants.BLUE)
			return Assets.scrollArrowBlueUp;
		else if (type == Constants.ARROW && color == Constants.PURPLE)
			return Assets.scrollArrowPurpleUp;
		else if (type == Constants.ARROW && color == Constants.GREEN)
			return Assets.scrollArrowGreenUp;
		else if (type == Constants.ARROW && color == Constants.RED)
			return Assets.scrollArrowRedUp;
		
		else if (type == Constants.HEXAGON && color == Constants.BLUE)
			return Assets.scrollHexagonBlueUp;
		else if (type == Constants.HEXAGON && color == Constants.PURPLE)
			return Assets.scrollHexagonPurpleUp;
		else if (type == Constants.HEXAGON && color == Constants.GREEN)
			return Assets.scrollHexagonGreenUp;
		else if (type == Constants.HEXAGON && color == Constants.RED)
			return Assets.scrollHexagonRedUp;
		
		else if (type == Constants.OCTOGON && color == Constants.BLUE)
			return Assets.scrollOctogonBlueUp;
		else if (type == Constants.OCTOGON && color == Constants.PURPLE)
			return Assets.scrollOctogonPurpleUp;
		else if (type == Constants.OCTOGON && color == Constants.GREEN)
			return Assets.scrollOctogonGreenUp;
		else if (type == Constants.OCTOGON && color == Constants.RED)
			return Assets.scrollOctogonRedUp;
		
		return null;
	}

	/**
	 * Chooses a pixmap of the specified type and color for the correctly tapped shape
	 * @param type of shape to be selected
	 * @param color color of shape to be selected
	 * @return pixmap of specified type and shape
	 */
	public static Pixmap chooseScrollPixmapDown(int type, int color){
		if (type == Constants.DIAMOND && color == Constants.BLUE)
			return Assets.scrollDiamondBlueDown;
		else if (type == Constants.DIAMOND && color == Constants.PURPLE)
			return Assets.scrollDiamondPurpleDown;
		else if (type == Constants.DIAMOND && color == Constants.GREEN)
			return Assets.scrollDiamondGreenDown;
		else if (type == Constants.DIAMOND && color == Constants.RED)
			return Assets.scrollDiamondRedDown;
		
		else if (type == Constants.CIRCLE && color == Constants.BLUE)
			return Assets.scrollCircleBlueDown;
		else if (type == Constants.CIRCLE && color == Constants.PURPLE)
			return Assets.scrollCirclePurpleDown;
		else if (type == Constants.CIRCLE && color == Constants.GREEN)
			return Assets.scrollCircleGreenDown;
		else if (type == Constants.CIRCLE && color == Constants.RED)
			return Assets.scrollCircleRedDown;
		
		else if (type == Constants.STAR && color == Constants.BLUE)
			return Assets.scrollStarBlueDown;
		else if (type == Constants.STAR && color == Constants.PURPLE)
			return Assets.scrollStarPurpleDown;
		else if (type == Constants.STAR && color == Constants.GREEN)
			return Assets.scrollStarGreenDown;
		else if (type == Constants.STAR && color == Constants.RED)
			return Assets.scrollStarRedDown;
		
		else if (type == Constants.HEART && color == Constants.BLUE)
			return Assets.scrollHeartBlueDown;
		else if (type == Constants.HEART && color == Constants.PURPLE)
			return Assets.scrollHeartPurpleDown;
		else if (type == Constants.HEART && color == Constants.GREEN)
			return Assets.scrollHeartGreenDown;
		else if (type == Constants.HEART && color == Constants.RED)
			return Assets.scrollHeartRedDown;
		
		else if (type == Constants.TRIANGLE && color == Constants.BLUE)
			return Assets.scrollTriangleBlueDown;
		else if (type == Constants.TRIANGLE && color == Constants.PURPLE)
			return Assets.scrollTrianglePurpleDown;
		else if (type == Constants.TRIANGLE && color == Constants.GREEN)
			return Assets.scrollTriangleGreenDown;
		else if (type == Constants.TRIANGLE && color == Constants.RED)
			return Assets.scrollTriangleRedDown;
		
		else if (type == Constants.ARROW && color == Constants.BLUE)
			return Assets.scrollArrowBlueDown;
		else if (type == Constants.ARROW && color == Constants.PURPLE)
			return Assets.scrollArrowPurpleDown;
		else if (type == Constants.ARROW && color == Constants.GREEN)
			return Assets.scrollArrowGreenDown;
		else if (type == Constants.ARROW && color == Constants.RED)
			return Assets.scrollArrowRedDown;
		
		else if (type == Constants.HEXAGON && color == Constants.BLUE)
			return Assets.scrollHexagonBlueDown;
		else if (type == Constants.HEXAGON && color == Constants.PURPLE)
			return Assets.scrollHexagonPurpleDown;
		else if (type == Constants.HEXAGON && color == Constants.GREEN)
			return Assets.scrollHexagonGreenDown;
		else if (type == Constants.HEXAGON && color == Constants.RED)
			return Assets.scrollHexagonRedDown;
		
		else if (type == Constants.OCTOGON && color == Constants.BLUE)
			return Assets.scrollOctogonBlueDown;
		else if (type == Constants.OCTOGON && color == Constants.PURPLE)
			return Assets.scrollOctogonPurpleDown;
		else if (type == Constants.OCTOGON && color == Constants.GREEN)
			return Assets.scrollOctogonGreenDown;
		else if (type == Constants.OCTOGON && color == Constants.RED)
			return Assets.scrollOctogonRedDown;
		
		
		return null;
	}

}
