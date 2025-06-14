package com.opuadm.commands.cli.cmds;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import com.opuadm.commands.cli.FakeFS;
import com.opuadm.LinuxifyMC;

@SuppressWarnings("unused")
public class Mkdir {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Usage: mkdir <directory_name>");
            return true;
        }
        String dirName = args[1];

        if (fs.createDirectory(dirName)) {
            sender.sendMessage("");
        } else {
            sender.sendMessage(LinuxifyMC.shellname + ": mkdir: Failed to create directory '" + dirName + "'");
        }
        return true;
    }
}
