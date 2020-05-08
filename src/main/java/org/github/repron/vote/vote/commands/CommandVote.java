package org.github.repron.vote.vote.commands;

import org.bukkit.entity.Player;
import org.github.repron.vote.vote.Vote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.rmi.activation.ActivationGroup_Stub;

public class CommandVote implements CommandExecutor {

    public Vote plugin;

    public CommandVote(Vote plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            plugin.getLogicVote().sendCommand(sender,args[0]);
        } else {
            sender.sendMessage("Nepičuj tak veľa ty kokot!!!!!!!!!!");
        }
        return false;

    }
}
