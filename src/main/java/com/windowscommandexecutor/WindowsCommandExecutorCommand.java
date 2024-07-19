package com.windowscommandexecutor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WindowsCommandExecutorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length > 0) {
                    String windowsCommand = String.join(" ", args);
                    try {
                        Process process = Runtime.getRuntime().exec("cmd /c " + windowsCommand);

                        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
                        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));

                        StringBuilder output = new StringBuilder();
                        String line;

                        while ((line = stdInput.readLine()) != null) {
                            output.append(line).append("\n");
                        }
                        StringBuilder errorOutput = new StringBuilder();
                        while ((line = stdError.readLine()) != null) {
                            errorOutput.append(line).append("\n");
                        }

                        stdInput.close();
                        stdError.close();

                        if (output.length() > 0) {
                            player.sendMessage(ChatColor.GREEN + "命令输出:\n" + ChatColor.WHITE + output);
                        } else if (errorOutput.length() > 0) {
                            player.sendMessage(ChatColor.RED + "你输入了一个错误的命令:\n" + ChatColor.WHITE + errorOutput);
                        } else {
                            player.sendMessage(ChatColor.YELLOW + "命令执行没有输出.");
                        }
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "执行命令时出错: " + ChatColor.WHITE + e.getMessage());
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "请提供要执行的Windows命令.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "你没有权限使用这个命令.");
            }
        }
        return true;
    }
}
