package com.github.repron.vote;

import com.github.repron.vote.commands.CommandVote;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.repron.vote.logic.LogicVote;

import java.util.Objects;

public final class Vote extends JavaPlugin {

    private LogicVote logicVote;

    @Override
    public void onEnable() {
        logicVote = new LogicVote(this);

        CommandVote commandVote = new CommandVote(this);

        System.out.println("Vote plugin loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public LogicVote getLogicVote() {
        return logicVote;
    }
}
