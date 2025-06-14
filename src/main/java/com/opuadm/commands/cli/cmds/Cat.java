package com.opuadm.commands.cli.cmds;

import com.opuadm.LinuxifyMC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.opuadm.commands.cli.FakeFS;

import java.util.Objects;

@SuppressWarnings("unused")
public class Cat {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Usage: cat <file_name>");
            return true;
        }
        String fileName = args[1];

        String content = fs.getFile(fileName);
        sender.sendMessage(Objects.requireNonNullElseGet(content, () -> LinuxifyMC.shellname + ": cat: Failed to read file '" + fileName + "'"));
        return true;
    }
}
