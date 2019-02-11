package com.oscarcv.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oscarcv.game.ChopperBounce;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ChopperBounce.WIDTH;
		config.height = ChopperBounce.HEIGHT;
		config.title = ChopperBounce.TITLE;
		new LwjglApplication(new ChopperBounce(), config);
	}
}
