package com.github.repron.vote.logic;

import com.github.repron.vote.Vote;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LogicVote implements Runnable {

    private String topic;
    private boolean isVote;
    private HashSet<CommandSender> voted;
    private int allVotes;
    private int forVotes;
    private Vote plugin;
    private HashSet<String> yes;
    private HashSet<String> no;
    private HashSet<String> topics;

    public LogicVote(Vote plugin) {
        yes = new HashSet<>(Arrays.asList("ano", "yes", "y"));
        no = new HashSet<>(Arrays.asList("nie", "no", "n"));
        topics = new HashSet<>(Arrays.asList("day", "night"));

        isVote = false;
        this.plugin = plugin;
        voted = new HashSet<>();
        allVotes = 0;
        forVotes = 0;
    }

    public boolean sendCommand(CommandSender player, String arg) {
        if (yes.contains(arg) || no.contains(arg)) {
            if (isVote) {
                if (voted.add(player)) {
                    allVotes++;
                    if (yes.contains(arg)) {
                        forVotes++;
                        player.sendMessage("Volil si pre " + ChatColor.AQUA + topic);
                        return true;
                    } else {
                        player.sendMessage("Volil si proti " + ChatColor.AQUA + topic);
                        return true;
                    }

                } else {
                    player.sendMessage("Už si raz hlasoval");
                    return true;
                }
            } else {
                player.sendMessage("Neprebieha žiadne hlasovanie.");
                return true;
            }
        }
        if (topics.contains(arg)) {
            if (!isVote) {
                topic = arg;
                isVote = true;
                voted.add(player);
                allVotes++;
                forVotes++;
                plugin.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.RESET + " začal hlasovanie za: " + ChatColor.AQUA + topic + ChatColor.RESET + ". Hlasujte s " + ChatColor.YELLOW + "/vote");
                plugin.getServer().getScheduler().runTaskLater(plugin, this, 600);
                return true;
            } else {
                player.sendMessage("Práve prebieha iné hlasovanie.");
                return true;
            }
        }
        player.sendMessage("Nesprávny argument");
        return true;
    }

    @Override
    public void run() {
        isVote = false;

        if (allVotes < forVotes * 2) {
            plugin.getServer().broadcastMessage("Hlasovanie za " + ChatColor.AQUA + topic + ChatColor.RESET + " " + 100 * forVotes / allVotes + "%, úspešné");
            executeVote();
        } else {
            plugin.getServer().broadcastMessage("Hlasovanie za " + ChatColor.AQUA + topic + ChatColor.RESET + " " + 100 * forVotes / allVotes + "%, neúspešné");
        }
        forVotes = 0;
        allVotes = 0;
        voted = new HashSet<>();
    }

    private void executeVote() {
        switch (topic) {
            case "day":
                plugin.getServer().getWorlds().get(0).setTime(1000);
                break;
            case "night":
                plugin.getServer().getWorlds().get(0).setTime(13000);
                break;
        }
    }

    public List<String> getTabComplete() {
        if (isVote) {
            return Arrays.asList("yes", "no");
        } else {
            return new ArrayList<>(topics);
        }
    }
}

