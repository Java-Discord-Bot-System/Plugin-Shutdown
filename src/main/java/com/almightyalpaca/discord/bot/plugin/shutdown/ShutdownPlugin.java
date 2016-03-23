package com.almightyalpaca.discord.bot.plugin.shutdown;

import java.lang.reflect.Field;

import com.almightyalpaca.discord.bot.system.command.AbstractCommand;
import com.almightyalpaca.discord.bot.system.command.annotation.Command;
import com.almightyalpaca.discord.bot.system.events.CommandEvent;
import com.almightyalpaca.discord.bot.system.exception.PluginLoadingException;
import com.almightyalpaca.discord.bot.system.exception.PluginUnloadingException;
import com.almightyalpaca.discord.bot.system.extension.PluginExtension;
import com.almightyalpaca.discord.bot.system.plugins.Plugin;
import com.almightyalpaca.discord.bot.system.plugins.PluginInfo;

public class ShutdownPlugin extends Plugin {

	class ShutdownCommand extends AbstractCommand {

		public ShutdownCommand() {
			super("shutdown", "Shutdown the bot!", "");
		}

		@Command(dm = true, guild = true, async = true)
		private void onCommand(final CommandEvent event) {
			try {
				final Field field = ShutdownPlugin.this.getBridge().getClass().getDeclaredField("pluginExtension");
				field.setAccessible(true);
				((PluginExtension) field.get(ShutdownPlugin.this.getBridge())).getPluginManager().shutdown();
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private static final PluginInfo INFO = new PluginInfo("com.almightyalpaca.discord.bot.plugin.shutdown", "1.0.0", "Almighty Alpaca", "Shutdown Plugin", "Shutdown the bot!");

	public ShutdownPlugin() {
		super(ShutdownPlugin.INFO);
	}

	@Override
	public void load() throws PluginLoadingException {
		this.registerCommand(new ShutdownCommand());
	}

	@Override
	public void unload() throws PluginUnloadingException {}
}
