package com.windowscommandexecutor;

import org.bukkit.plugin.java.JavaPlugin;

public class WindowsCommandExecutor extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("windows").setExecutor(new WindowsCommandExecutorCommand());
        getLogger().info("WindowsCommandExecutor 已启用.");
    }

    @Override
    public void onDisable() {
        getLogger().info("WindowsCommandExecutor 已禁用.");
    }
}
