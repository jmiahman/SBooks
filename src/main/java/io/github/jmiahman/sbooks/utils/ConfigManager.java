package io.github.jmiahman.sbooks.utils;

import com.google.common.collect.Lists;
import io.github.jmiahman.sbooks.config.BookConfig;
import io.github.jmiahman.sbooks.config.Configs;
import io.github.jmiahman.sbooks.config.Configurable;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ConfigManager
{
	private static Configurable bookConfig = BookConfig.getConfig();

	public static Text getBookTitle()
	{
		return TextSerializers.FORMATTING_CODE.deserialize(Configs.getConfig(bookConfig).getNode((Object[]) ("book.title").split("\\.")).getString());
	}

	public static Text getBookAuthor()
	{
		return TextSerializers.FORMATTING_CODE.deserialize(Configs.getConfig(bookConfig).getNode((Object[]) ("book.author").split("\\.")).getString());
	}

	public static List<Text> getBookPages()
	{
		List<Text> pages = Lists.newArrayList();
		CommentedConfigurationNode valueNode = Configs.getConfig(bookConfig).getNode("book", "pages");

		for (Object page : valueNode.getChildrenMap().keySet())
		{
			int pageNum = 1;
		/*	String pageString = Configs.getConfig(bookConfig).getNode("book", "pages", String.valueOf(page)).getString(); */
			String pageString = Configs.getConfig(bookConfig).getNode("book", "pages", pageNum).getString();
			pages.add(TextSerializers.FORMATTING_CODE.deserialize(pageString));
			pageNum = pageNum + 1;
		}

		return pages;
	}
}
