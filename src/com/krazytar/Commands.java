package com.krazytar;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class Commands {
    static String[] champions = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe", "Aurelion Sol", "Azir", "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Camille", "Cassiopeia", "Cho'Gath", "Corki", "Darius", "Diana", "Dr. Mundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks", "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Illaoi", "Irelia", "Ivern", "Janna", "Jarvan IV", "Jax", "Jayce", "Jhin", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin", "Katarina", "Kayle", "Kayn", "Kennen", "Kha'Zix", "Kindred", "Kled", "Kog'Maw", "Leblanc", "Lee Sin", "Leona", "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Master Yi", "Maokai", "Miss Fortune", "Mordekaiser", "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Orrn", "Pantheon", "Poppy", "Quinn", "Rakan", "Rammus", "Rek'Sai", "Renekton", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani", "Shaco", "Shen", "Shyvana", "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "Tahm Kench", "Taliyah", "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "Twisted Fate", "Twitch", "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Vel'Koz", "Vi", "Viktor", "Vladimir", "Volibear", "Wukong", "Warwick", "Xayah", "Xerath", "Xin Zhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"};
    static String[] positions = {"Top", "Jungle", "Mid", "ADC", "Support"};
    static String[] messages = {"Have fun feeding with ", "Good luck with ", "0/10 incoming using ", "Top tier pick: ", "Don't feed on ", "Try not to flame on ", "Have fun with "};
    static String[] builds = {"Full AP", "Full Tank", "AD Assassin", "AD/AS", "AP/AS On Hit", "AP Bruiser", "AD Bruiser", "Full CDR/Mana", "Full Crit"};
    static String[] encouragements = {"Be happy.", "You are amazing.", "People like you.", "Smile!", "Today is a great day.", "Don't give up!"};
    static String[] teams = {"TSM", "IMT", "C9", "CLG", "DIG", "REN", "GV", "NV", "TL", "P1"};
    
    public static void playerInfo(IChannel channel, String player, Region region) {
        List<Object> playerCache = new ArrayList();
        switch(region) {
            case NA:
                playerCache = BotUtils.naPlayerCache;
                break;
            case EU:
                playerCache = BotUtils.euPlayerCache;
                break;
            case KR:
                playerCache = BotUtils.krPlayerCache;
        }
        String name = "", pos = "";
        String teamText = "";
        int ovr = 0, pot = 0, age = 0, team = 0;
        for(Object o : playerCache) {
            JSONObject p = (JSONObject) o;
            JSONArray ratings = (JSONArray) p.get("ratings");
            JSONObject rat = (JSONObject) ratings.get(ratings.size() - 1);
            name = p.get("userID").toString();
            if(name.equals(player)) {
                pos = p.get("pos").toString();
                team = Integer.parseInt(p.get("tid").toString());
                ovr = Integer.parseInt(rat.get("ovr").toString());
                pot = Integer.parseInt(rat.get("pot").toString());
                
                switch (team) {
                    case -3:
                        teamText = "Retired";
                        break;
                    case -1:
                        teamText = "Free Agent";
                        break;
                    default:
                        teamText = teams[team];
                        break;
                }
                break;
            }
            
            
        }
        BotUtils.sendMessage(channel, "Name: " + name + " Rating: " + ovr + "/" + pot + " Team: " + teamText);
    }
    
    public static void spam(IChannel channel, IUser target, IUser sender) {
        if(sender.hasRole(channel.getGuild().getRolesByName("Rito").get(0)) || sender.hasRole(channel.getGuild().getRolesByName("Commissioner").get(0)) || sender.hasRole(channel.getGuild().getRolesByName("Moderator").get(0))) {
            for (int i = 0; i < 5; i++) {
            BotUtils.sendMessage(channel, "Hello " + target.mention());
        }
        } else {
            BotUtils.sendMessage(channel, sender.mention() + ", only Rito, Commissioners, and Moderators may use that command.");
        }
    }
    
    public static void ultimateBravery(IChannel channel) {
        int champ = (int)Math.floor(Math.random() * champions.length);
        int position = (int) Math.floor(Math.random() * positions.length);
        int message = (int) Math.floor(Math.random() * messages.length);
        int build = (int) Math.floor(Math.random() * builds.length);
        BotUtils.sendMessage(channel, messages[message] + builds[build] + " " + champions[champ] + " " + positions[position]);
    }
    
    public static void encourage(IChannel channel) {
        int encourage = (int) Math.floor(Math.random() * encouragements.length);
        BotUtils.sendMessage(channel, encouragements[encourage]);
    }
}
