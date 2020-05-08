package com.github.repron.vote;

import com.github.repron.vote.commands.CommandVote;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.repron.vote.logic.LogicVote;

import java.util.Objects;

public final class Vote extends JavaPlugin {

    private LogicVote logicVote;

    @Override
    public void onEnable() {
        System.out.println("Vote plugin loaded");
        logicVote = new LogicVote(this);
        Objects.requireNonNull(this.getCommand("vote")).setExecutor(new CommandVote(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public LogicVote getLogicVote() {
        return logicVote;
    }
}
