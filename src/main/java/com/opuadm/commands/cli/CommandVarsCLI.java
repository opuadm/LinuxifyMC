package com.opuadm.commands.cli;

import java.util.List;

public class CommandVarsCLI
{
    public static final String[] cmds = {
            "help",
            "test",
            "uname",
            "cd",
            "ls",
            "chmod",
            "chown",
            "mkdir",
            "rm",
            "cat",
            "serverfetch",
            "neofetch",
            "echo",
            "touch"
    };

    public static List<String> LsOpts() {
        return List.of("-a", "-o", "-ao");
    }

    public static List<String> UnameOpts()  { return List.of("-s", "-v"); }

    public static List<String> UnameOptsS()  { return List.of("-v"); }

    public static List<String> ChmodPerms() { return List.of("777", "644", "755", "700", "766"); }
}
