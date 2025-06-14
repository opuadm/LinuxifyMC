package com.opuadm.commands.cli.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.opuadm.commands.cli.FakeFS;
import com.opuadm.LinuxifyMC;

@SuppressWarnings("unused")
public class Uname {
    public boolean execute(CommandSender sender, Player player, FakeFS fs, String[] args) {
        if (args.length == 1 || (args.length == 2 && args[1].equals("-s"))) {
            sender.sendMessage(LinuxifyMC.kernelname);
        } else if (args.length == 2 && args[1].equals("-v")) {
            sender.sendMessage(LinuxifyMC.kernelver);
        } else if (args.length == 3 && args[1].equals("-s") && args[2].equals("-v")) {
            sender.sendMessage(LinuxifyMC.kernelname + " " + LinuxifyMC.kernelver);
        }
        return true;
    }
}
