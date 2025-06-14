package com.opuadm.commands.cli.cmds;

import com.opuadm.commands.cli.FakeFS;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class LS {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        String path = fs.getCurrentDirectory();
        boolean showHidden = false;
        boolean showDetails = false;

        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args[i].contains("a")) showHidden = true;
                if (args[i].contains("o")) showDetails = true;
                continue;
            }
            path = args[i];
        }

        String listing = fs.listDirectory(path, showHidden, showDetails);
        if (listing != null) sender.sendMessage(listing);
        return true;
    }
}
