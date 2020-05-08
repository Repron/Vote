package com.github.repron.vote.commands;

import com.github.repron.vote.Vote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandVote implements CommandExecutor {

    private Vote plugin;

    public CommandVote(Vote plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            plugin.getLogicVote().sendCommand(sender, args[0]);
        } else {
            sender.sendMessage("Nesprávny počet argumentov");
        }
        return false;

    }
}
