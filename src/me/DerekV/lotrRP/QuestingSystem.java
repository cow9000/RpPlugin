package me.DerekV.lotrRP;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.minecraft.server.v1_7_R3.PacketPlayOutNamedSoundEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuestingSystem implements Listener{
	
	 public static lotrRP plugin;
	   
	 public QuestingSystem(lotrRP instance) {
	     plugin = instance; 
	 }
	 
	 
	 HashMap<String, Player> map = new HashMap<String, Player>();
	 long timer;
	 
	 @EventHandler
	 public void clickleft(NPCLeftClickEvent event){
		 if(map.containsValue(event.getClicker())){
			 map.remove("t", event.getClicker());
			 event.getClicker().sendMessage(ChatColor.RED + "You have left the quest!");
		 }
		 
	 }
	 
	 
	@EventHandler
	public void click(net.citizensnpcs.api.event.NPCRightClickEvent event){
		//Handle a click on a NPC. The event has a getNPC() method. 
		//Be sure to check event.getNPC() == this.getNPC() so you only handle clicks on this NPC!
		
		//event.getClicker().sendMessage(ChatColor.GREEN + "You clicked on " + event.getNPC().getName());
		Player p = (Player) event.getClicker();
		
		  FileConfiguration userconfig = null;
	      File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+event.getClicker().getName()+".yml");
	      userconfig = YamlConfiguration.loadConfiguration(userfile);
		
		/*String Questname = plugin.getConfig().getString(event.getNPC().getName() + ".quest.name");
		String NPCname = event.getNPC().getName();
		String playerQuest = userconfig.getString(event.getClicker().getUniqueId() + ".quest.assigner").toString();
		String objective = plugin.getConfig().getString(event.getNPC().getName() + ".quest.objective");
		
		
		if(playerQuest == NPCname){
			if(objective.equals("get")){
				String item = plugin.getConfig().getString(event.getNPC().getName() + ".quest.item");
				if(event.getClicker().getInventory().contains(new ItemStack(Material.ANVIL,5))){
					p.sendMessage("You suck :)");
				}
			}
			
			
		}*/
		
		if(event.getNPC().getName().equals("Fast Travel")){
			p.sendMessage(ChatColor.GREEN + "Fast traveling!");
			Inventory myInventory = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Fast Travel");
			
			List<String> location = plugin.getConfig().getStringList("fastT.loc.names");
			String[] items = location.toArray(new String[location.size()]);
			for(int i = 0; i < location.size(); i++){

				ItemStack i2 = new ItemStack(86);
				ItemMeta im2 = i2.getItemMeta();
				
				String name = items[i];
				
				im2.setDisplayName(ChatColor.GREEN + name);
				i2.setItemMeta(im2);
				myInventory.addItem(i2);
			}
			
			
			
			p.openInventory(myInventory);
		}
	      
	    long last = System.currentTimeMillis();
	    //Make a timer so player doesnt get automatically signed up
	    if(last - timer> 350){
			if(map.containsValue(event.getClicker())){
				if(userconfig.getString(event.getClicker().getUniqueId() + ".quest.name") != null){
					p.sendMessage(ChatColor.GREEN + "Quest accepted!");
					map.remove("t", event.getClicker());
					userconfig.set(event.getClicker().getUniqueId() + ".quest.name", plugin.getConfig().getString(event.getNPC().getName() + ".quest.name"));
					userconfig.set(event.getClicker().getUniqueId() + ".quest.assigner", event.getNPC().getName());
					try {
						userconfig.save(userfile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	    }
	    
	    
		if(plugin.getConfig().getString(event.getNPC().getName() + ".quest.name") != null ){
			if(userconfig.getString(event.getClicker().getUniqueId() + ".quest.name").equals("")){
				if(!map.containsValue(event.getClicker())){
				
				
				
				p.sendMessage(ChatColor.DARK_PURPLE + event.getNPC().getName() + " - " + plugin.getConfig().getString(event.getNPC().getName() + ".quest.desc"));
				p.sendMessage(ChatColor.GREEN + "Do you want to accept? Right click - Accept, Left click - Deny");
				map.put("t", event.getClicker());
				timer = System.currentTimeMillis();
				}
			}else{
				p.sendMessage(ChatColor.GREEN + event.getNPC().getName() + " - Have you finished my quest yet?");
			}
		}
		
		else if(plugin.getConfig().getString(event.getNPC().getName() + ".converse") != null){
			
			if(plugin.getConfig().getString(event.getNPC().getName() + ".sound") !=null){
				p.playSound(p.getLocation(), plugin.getConfig().getString(event.getNPC().getName() + ".sound"), 1, 1);
			}

			 String converstation = plugin.getConfig().getString(event.getNPC().getName() + ".converse");
			converstation = converstation.replace("/p", "" + event.getClicker().getName());
			p.sendMessage(ChatColor.GOLD + event.getNPC().getName() + " says -" + ChatColor.GRAY + converstation);
		}
	}
	
	
}
