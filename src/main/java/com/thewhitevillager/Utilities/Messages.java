package com.thewhitevillager.Utilities;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    public static String broadcastPrefix = "";

    public static String privateMassageChars = ChatColor.GRAY + "" + ChatColor.ITALIC;
    public static void privateMassage(Player player, String massage) {
        player.sendMessage(privateMassageChars + massage);
    }
    public static void privateMassage(Player player, Component massage) {
        player.sendMessage(massage);
    }
    public static void privatePrefixMassage(Player player, String massage, ChatColor prefixColor) {
        player.sendMessage("[" + prefixColor + "\u25b6" + ChatColor.RESET + "] " + massage);
    }

    public static void broadcastMassage(String massage) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(massage);
        }
    }
    public static void broadcastMassage(String massage, Component prefix) {
        if (prefix == null) {
            broadcastMassage(massage);
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(prefix.append(Component.text(massage)));
            }
        }
    }

    public static void broadcastMassage(Component massage) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(massage);
        }
    }
    public static void broadcastMassage(Component massage, Component prefix) {
        if (prefix == null) {
            broadcastMassage(massage);
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(prefix.append(massage));
            }
        }
    }


    public static void broadcastActionBar(String massage) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendActionBar(Component.text(massage));
        }
    }
}
