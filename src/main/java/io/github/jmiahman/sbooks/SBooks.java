package io.github.jmiahman.sbooks;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import io.github.jmiahman.sbooks.cmds.SBooksBookExecutor;
import io.github.jmiahman.sbooks.cmds.SBooksExecutor;
import io.github.jmiahman.sbooks.cmds.SBooksReloadExecutor;
import io.github.jmiahman.sbooks.config.BookConfig;
import io.github.jmiahman.sbooks.listeners.PlayerJoinListener;
import io.github.jmiahman.sbooks.utils.ConfigManager;
import io.github.jmiahman.sbooks.utils.Utils;
import me.flibio.updatifier.Updatifier;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Updatifier(repoName = "SBooks", repoOwner = "jmiahman", version = "v" + PluginInfo.VERSION)
@Plugin(id = PluginInfo.ID, name = PluginInfo.NAME, version = PluginInfo.VERSION, dependencies = PluginInfo.DEPENDENCIES)
public class SBooks
{
	protected SBooks()
	{
		;
	}

	private static SBooks sbooks;
	public static List<String> allSBooks = Lists.newArrayList();
	public static Game game;

	@Inject
	private Logger logger;

	public Logger getLogger()
	{
		return logger;
	}

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;

	public static SBooks getSBooks()
	{
		return sbooks;
	}

	@Listener
	public void onServerInit(GameInitializationEvent event)
	{
		getLogger().info("SBooks loading...");
		sbooks = this;
		game = Sponge.getGame();

		// Config File
		if (!Files.exists(configDir))
		{
			try
			{
				Files.createDirectories(configDir);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		// Create data Directory
		if (!Files.exists(configDir.resolve("data")))
		{
			try
			{
				Files.createDirectories(configDir.resolve("data"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		// Create book.conf
		BookConfig.getConfig().setup();
	
/*		HashMap<List<String>, CommandSpec> subcommands = new HashMap<List<String>, CommandSpec>();

                subcommands.put(Arrays.asList("book"), CommandSpec.builder()
                        .permission("sbooks.book")
                        .description(Text.of("Obtian a SBooks' Book"))
                        .arguments(GenericArguments.seq(GenericArguments.onlyOne(GenericArguments.string(Text.of("book name")))))
                        .executor(new SBooksBookExecutor())
                        .extendedDescription(Text.of("To use /SBooks book simply do /SBooks book"))
                        .build());


		subcommands.put(Arrays.asList("reload"), CommandSpec.builder()
			.permission("sbooks.reload")
			.description(Text.of("Reload the SBooks Config"))
			.executor(new SBooksReloadExecutor())
			.extendedDescription(Text.of("To reload the config, simply do /SBooks reload"))
			.build());
*/
		CommandSpec sbooksCommandSpec = CommandSpec.builder()
			.extendedDescription(Text.of("SBooks Command"))
			.permission("sbooks.use")
			.executor(new SBooksBookExecutor())
			.build();

                game.getCommandManager().register(this, sbooksCommandSpec, "sbooks");

                CommandSpec sbooksreloadCommandSpec = CommandSpec.builder()
                        .extendedDescription(Text.of("Reload the SBooks Config"))
                        .permission("sbooks.reload")
                        .executor(new SBooksReloadExecutor())
                        .build();

		game.getCommandManager().register(this, sbooksreloadCommandSpec, "sbooks-reload");

		Sponge.getEventManager().registerListeners(this, new PlayerJoinListener());

		getLogger().info("-----------------------------");
		getLogger().info("SBooks was made by JMiahMan!");
		getLogger().info("Please post all errors with SBooks on the Sponge Thread or on GitHub!");
		getLogger().info("Have fun, and enjoy your SBooks! :P");
		getLogger().info("-----------------------------");
		getLogger().info("SBooks Loaded!");
	}

	public Path getConfigDir()
	{
		return configDir;
	}
}
