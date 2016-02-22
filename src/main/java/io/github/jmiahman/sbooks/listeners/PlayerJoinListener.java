package io.github.jmiahman.sbooks.listeners;

import io.github.jmiahman.sbooks.utils.ConfigManager;
import io.github.jmiahman.sbooks.utils.Utils;
import org.spongepowered.api.data.manipulator.mutable.entity.JoinData;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.util.Optional;

public class PlayerJoinListener
{
	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join event)
	{
		Optional<JoinData> optionalJoinData = event.getTargetEntity().getOrCreate(JoinData.class);

		if (optionalJoinData.isPresent())
		{
			JoinData joinData = optionalJoinData.get();

			if (joinData.lastPlayed().get().equals(joinData.firstPlayed().get()))
			{
				Utils.givePlayerBook(event.getTargetEntity());
			}
		}
	}
}
