package io.github.jmiahman.sbooks.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.scheduler.Task;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Utils
{

	public static void givePlayerBook(Player player)
	{
		ItemStack stack = ItemStack.builder().itemType(ItemTypes.WRITTEN_BOOK).quantity(1).build();
		stack.offer(Keys.BOOK_AUTHOR, ConfigManager.getBookAuthor());
		stack.offer(Keys.BOOK_PAGES, ConfigManager.getBookPages());
		stack.offer(Keys.DISPLAY_NAME, ConfigManager.getBookTitle());
		player.getInventory().offer(stack);
	}
}
