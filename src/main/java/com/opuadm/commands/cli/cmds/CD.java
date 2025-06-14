package com.opuadm.commands.cli.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.opuadm.commands.cli.FakeFS;
import com.opuadm.LinuxifyMC;

@SuppressWarnings("unused")
public class CD {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        if (args.length > 1) {
            String newPath = args[1];
            if (fs.directoryExists(newPath)) {
                if (fs.setCurrentDirectory(newPath)) {
                    sender.sendMessage("Current directory: " + fs.getCurrentDirectory());
                } else {
                    sender.sendMessage(LinuxifyMC.shellname + ": cd: " + newPath + ": failed to change directory");
                }
            } else {
                sender.sendMessage(LinuxifyMC.shellname + ": cd: " + newPath + ": No such file or directory");
            }
        } else {
            sender.sendMessage("Current directory: " + fs.getCurrentDirectory());
        }
        return true;
    }
}
