package com.krazytar;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Events {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] message = event.getMessage().getContent().split(" ");
        switch(message[0]) {
            case BotUtils.BOT_PREFIX + "playerinfo":
                Commands.playerInfo(message[1]);
                break;
            case BotUtils.BOT_PREFIX + "spam":
                Commands.spam(event.getChannel(), event.getMessage().getMentions().get(0), Integer.parseInt(message[2]));
                break;
        }
    }
}
