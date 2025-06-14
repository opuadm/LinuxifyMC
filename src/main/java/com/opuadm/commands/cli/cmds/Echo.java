package com.opuadm.commands.cli.cmds;

import com.opuadm.commands.cli.FakeFS;
import com.opuadm.LinuxifyMC;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

@SuppressWarnings("unused")
public class Echo {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        String fullCommand = String.join(" ", args);
        int redirectPos = fullCommand.indexOf(">>");

        if (redirectPos > 0) {
            String textToEcho = fullCommand.substring(5, redirectPos).trim();
            String fileName = fullCommand.substring(redirectPos + 2).trim();

            if (fs.appendToFile(fileName, textToEcho)) {
                sender.sendMessage("");
            } else {
                sender.sendMessage(LinuxifyMC.shellname + ": echo: Failed to append to file '" + fileName + "'");
            }
        } else {
            if (args.length > 1) {
                String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                sender.sendMessage(message);
            } else {
                sender.sendMessage("");
            }
        }
        return true;
    }
}
