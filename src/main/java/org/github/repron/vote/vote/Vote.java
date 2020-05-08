package org.github.repron.vote.vote;

import org.bukkit.plugin.java.JavaPlugin;
import org.github.repron.vote.vote.commands.CommandVote;
import org.github.repron.vote.vote.logic.LogicVote;

import java.util.Objects;

public final class Vote extends JavaPlugin {

    private LogicVote logicVote;

    @Override
    public void onEnable() {
        System.out.println("si pipikus");

        logicVote = new LogicVote(this);
        Objects.requireNonNull(this.getCommand("vote")).setExecutor(new CommandVote(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public LogicVote getLogicVote(){
        return logicVote;
    }
}
