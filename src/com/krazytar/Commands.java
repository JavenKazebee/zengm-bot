package com.krazytar;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class Commands {
    public static void playerInfo(String player) {
        
    }
    
    public static void spam(IChannel channel, IUser target, int num) {
        for (int i = 0; i < num; i++) {
            BotUtils.sendMessage(channel, "Hello " + target.mention());
        }
    }
}
