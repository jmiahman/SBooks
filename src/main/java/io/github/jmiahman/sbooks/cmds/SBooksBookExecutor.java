package io.github.jmiahman.sbooks.cmds;

import io.github.jmiahman.sbooks.utils.ConfigManager;
import io.github.jmiahman.sbooks.utils.Utils;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.entity.living.player.Player;
import java.util.Optional;

public class SBooksBookExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		final Player player = (Player) src;
                Optional<Player> target = args.<Player> getOne("target");


                if (!target.isPresent())
                {
		
                        if (src instanceof Player)
                        {
				Utils.givePlayerBook(player);
				src.sendMessage(Text.of(TextColors.GREEN, "Success: ", TextColors.YELLOW, "The Book has been Given!"));
				return CommandResult.success();
			}
		}
		else
		{
		src.sendMessage(Text.of(TextColors.GREEN, "Failure: ", TextColors.YELLOW, "The Book has not been Given!"));
		return CommandResult.empty();
		}		
	
	return CommandResult.success();
	}

}

