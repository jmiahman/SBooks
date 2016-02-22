package io.github.jmiahman.sbooks.cmds;

import io.github.jmiahman.sbooks.utils.ConfigManager;
import io.github.jmiahman.sbooks.utils.Utils;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;
import java.util.Optional;

public class SBooksExecutor implements CommandExecutor
{

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		Optional<Player> target = args.<Player> getOne("target");

		if (!target.isPresent())
		{
			if (src instanceof Player)
			{
				final Player player = (Player) src;
					Utils.givePlayerBook(player);
			}
			else
			{
				src.sendMessage(Text.of(TextColors.DARK_RED, "Error! ", TextColors.RED, "Must be an in-game player to use /SBooks!"));
			}
		}

		return CommandResult.success();
	}
}
