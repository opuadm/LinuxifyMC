package com.opuadm.commands.cli.cmds;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import com.opuadm.commands.cli.FakeFS;
import com.opuadm.LinuxifyMC;

@SuppressWarnings("unused")
public class RM {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        boolean recursive = false;
        String path = null;

        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args[i].contains("r") || args[i].contains("R")) {
                    recursive = true;
                }
                continue;
            }
            path = args[i];
        }

        if (path == null) {
            sender.sendMessage("Usage: rm [-r] <path>");
            return true;
        }

        if (fs.deleteNode(path, recursive)) {
            sender.sendMessage("");
        } else {
            if (!recursive && fs.directoryExists(path)) {
                sender.sendMessage(LinuxifyMC.shellname + ": rm: " + path + ": is a directory");
            } else {
                sender.sendMessage(LinuxifyMC.shellname + ": rm: " + path + ": No such file or directory");
            }
        }
        return true;
    }
}
