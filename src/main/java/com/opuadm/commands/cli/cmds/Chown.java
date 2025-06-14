package com.opuadm.commands.cli.cmds;

import com.opuadm.LinuxifyMC;
import com.opuadm.commands.cli.FakeFS;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Chown {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("Usage: chown [OPTION]... [OWNER][:[GROUP][ FILE...");
            return true;
        }
        String newOwner = args[1];
        String path = args[2];

        if (fs.chown(path, newOwner)) {
            sender.sendMessage("");
        } else {
            sender.sendMessage(LinuxifyMC.shellname + ": chown: Failed to change owner for '" + path + "'");
        }
        return true;
    }
}
