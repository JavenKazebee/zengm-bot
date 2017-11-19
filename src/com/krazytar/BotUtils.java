package com.krazytar;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class BotUtils {
    public static final String BOT_PREFIX = ".";
    public static IDiscordClient getBuiltClient() {
        ClientBuilder cb = new ClientBuilder();
        System.out.println("Please enter token:");
        cb.withToken(new Scanner(System.in).nextLine());
        return cb.build();
    }
    
    public static void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
    }
    
    public static void loadJSON() {
        File f = new File("data.json");
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(f));
        } catch (IOException | ParseException ioe) {
            
        }
    }
}
