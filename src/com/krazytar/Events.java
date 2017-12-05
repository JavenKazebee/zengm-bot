package com.krazytar;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Events {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] message = event.getMessage().getContent().split(" ");
        switch(message[0]) {
            case BotUtils.BOT_PREFIX + "krplayer":
                Commands.playerInfo(event.getChannel(), message[1], Region.KR);
                break;
            case BotUtils.BOT_PREFIX + "euplayer":
                Commands.playerInfo(event.getChannel(), message[1], Region.EU);
                break;
            case BotUtils.BOT_PREFIX + "naplayer":
                Commands.playerInfo(event.getChannel(), message[1], Region.NA);
                break;
            case BotUtils.BOT_PREFIX + "spam":
                Commands.spam(event.getChannel(), event.getMessage().getMentions().get(0), event.getAuthor());
                break;
            case BotUtils.BOT_PREFIX + "ub":
                Commands.ultimateBravery(event.getChannel());
                break;
            case BotUtils.BOT_PREFIX + "encourage":
                Commands.encourage(event.getChannel());
                break;
        }
    }
}
