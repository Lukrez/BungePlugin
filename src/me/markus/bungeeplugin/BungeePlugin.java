package me.markus.bungeeplugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeePlugin  extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "LoginFoo");
		this.getLogger().info("v" + this.getDescription().getVersion() + " enabled.");
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("v" + this.getDescription().getVersion() + " disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("test")) {
			
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			 
			try {
			    out.writeUTF("PlayerConnect");
			    //out.writeUTF("foobar"); // Target Server
			} catch (IOException e) {
			    // Can never happen
			}
			player.sendPluginMessage(this, "LoginFoo", b.toByteArray());
			player.sendMessage("running test");
			return true;
		}
		
		return false;
	}
	
}
