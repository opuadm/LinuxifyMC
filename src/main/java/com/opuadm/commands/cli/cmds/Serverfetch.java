package com.opuadm.commands.cli.cmds;

import com.opuadm.commands.cli.FakeFS;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

@SuppressWarnings("unused")
public class Serverfetch {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        sender.sendMessage("Server MC Version: " + Bukkit.getServer().getMinecraftVersion());
        sender.sendMessage("Bukkit Version: " + Bukkit.getVersion());
        sender.sendMessage("Server Port: " + Bukkit.getServer().getPort());
        sender.sendMessage("Java Version: " + System.getProperty("java.version"));
        return true;
    }
}
