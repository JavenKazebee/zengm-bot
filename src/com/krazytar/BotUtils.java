package com.krazytar;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class BotUtils {
    public static final String BOT_PREFIX = ".";
    public static List<Object> naPlayerCache = new ArrayList();
    public static List<Object> krPlayerCache = new ArrayList();
    public static List<Object> euPlayerCache = new ArrayList();
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
    
    public static void loadJSON(Region region) {
        String file = "";
        List<Object> playerCache = new ArrayList();
        switch(region) {
            case KR:
                file = "kr.json";
                break;
            case EU:
                file = "eu.json";
                break;
            case NA:
                file = "na.json";
                break;
        }
        JSONParser parser = new JSONParser();
        System.out.println("Parsing JSON: " + file);
        System.out.println(new File(file).exists());
        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject jobj = (JSONObject) obj;
            JSONArray players = (JSONArray) jobj.get("players");
            for(Object o : players) {
                playerCache.add(o);
            }
        } catch (IOException | ParseException ioe) {
            System.out.println("Houston, we have a problem.");
        }
        
        switch(region) {
            case KR:
                krPlayerCache = playerCache;
                break;
            case NA:
                naPlayerCache = playerCache;
                break;
            case EU:
                euPlayerCache = playerCache;
                break;
        }
    }
}
