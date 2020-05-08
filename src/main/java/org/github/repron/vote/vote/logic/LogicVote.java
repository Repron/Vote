package org.github.repron.vote.vote.logic;

import org.bukkit.command.CommandSender;
import org.github.repron.vote.vote.Vote;

import java.util.HashSet;

public class LogicVote implements Runnable {

    private String topic;
    private boolean isVote;
    private HashSet<CommandSender> voted;
    private int allVotes;
    private int forVotes;
    private Vote plugin;

    public LogicVote(Vote plugin) {
        isVote = false;
        this.plugin = plugin;
        voted = new HashSet<>();
        allVotes = 0;
        forVotes = 0;
    }

    public boolean sendCommand(CommandSender player, String arg) {
        if (arg.equals("ano") || arg.equals("nie")) {
            System.out.println("Ano/nie");
            if (isVote) {
                if (voted.add(player)) {
                    allVotes++;
                    if (arg.equals("ano")) {
                        forVotes++;
                        player.sendMessage("you voted yes");
                        System.out.println("Ano");
                        return true;
                    } else {
                        player.sendMessage("you voted no");
                        return true;
                    }

                } else {
                    System.out.println("Vote already");
                    player.sendMessage("ju alredi voutid");
                    return true;
                }
            } else {
                player.sendMessage("yes vote in progress");
                return true;
            }
        }
        if (arg.equals("day")) {
            if (!isVote) {
                isVote = true;
                voted.add(player);
                allVotes++;
                forVotes++;
                plugin.getServer().broadcastMessage(player.getName() + " started a vote for day");
                plugin.getServer().getScheduler().runTaskLater(plugin, this, 600);
                return true;
            } else {
                player.sendMessage("vote alreadyi n progreess");
                return true;
            }
        }
        player.sendMessage("invalid argument");
        return true;
    }

    @Override
    public void run() {
        isVote = false;
        plugin.getServer().broadcastMessage(String.valueOf(forVotes));
        plugin.getServer().broadcastMessage(String.valueOf(allVotes/2));
        plugin.getServer().broadcastMessage("" + 100 * forVotes / allVotes + "%");
        if (allVotes <= forVotes * 2) {
            plugin.getServer().getWorlds().get(0).setTime(1600);
            plugin.getServer().broadcastMessage("vote sucess");
        } else {
            plugin.getServer().broadcastMessage("vote fejld");
        }
        forVotes = 0;
        allVotes = 0;
        voted = new HashSet<>();
    }
}

