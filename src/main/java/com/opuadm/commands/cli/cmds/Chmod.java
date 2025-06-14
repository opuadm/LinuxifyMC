package com.opuadm.commands.cli.cmds;

import com.opuadm.LinuxifyMC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.opuadm.commands.cli.FakeFS;

@SuppressWarnings("unused")
public class Chmod {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("Usage: chmod [OPTION]... OCTAL-MODE FILE...");
            return true;
        }
        String permissions = args[1];
        String path = args[2];

        if (fs.chmod(path, permissions)) {
            sender.sendMessage("");
        } else {
            sender.sendMessage(LinuxifyMC.shellname + ": chmod: Failed to change permissions for '" + path + "'");
        }
        return true;
    }
}
