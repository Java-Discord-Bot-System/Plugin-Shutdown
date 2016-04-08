package com.almightyalpaca.discord.bot.plugin.shutdown;

import com.almightyalpaca.discord.bot.system.command.Command;
import com.almightyalpaca.discord.bot.system.command.CommandHandler;
import com.almightyalpaca.discord.bot.system.events.commands.CommandEvent;
import com.almightyalpaca.discord.bot.system.exception.PluginLoadingException;
import com.almightyalpaca.discord.bot.system.exception.PluginUnloadingException;
import com.almightyalpaca.discord.bot.system.plugins.Plugin;
import com.almightyalpaca.discord.bot.system.plugins.PluginInfo;

public class ShutdownPlugin extends Plugin {

	class ShutdownCommand extends Command {

		public ShutdownCommand() {
			super("shutdown", "Shutdown the bot!", "");
		}

		@CommandHandler(dm = true, guild = true, async = true)
		private void onCommand(final CommandEvent event) {
			ShutdownPlugin.this.getBridge().getExtensionManager().shutdown();
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
