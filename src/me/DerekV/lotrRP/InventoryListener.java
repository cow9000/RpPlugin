package me.DerekV.lotrRP;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener{
	
	
	 public static lotrRP plugin;
	   
	 public InventoryListener(lotrRP instance) {
	     plugin = instance; 
	 }
	
	
	@EventHandler
	public void onMiddleClick(InventoryClickEvent e){
		if(e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT || e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT){
		    
			
			  FileConfiguration userconfig = null;
		      File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+e.getWhoClicked().getName()+".yml");
		      userconfig = YamlConfiguration.loadConfiguration(userfile);
		    
		    
			  ItemStack item = e.getCurrentItem();
			  String itemName = item.getType().toString();
			  if(item.getItemMeta().getLore() == null){
				  
			  }else{
			  List<String> itemLore = item.getItemMeta().getLore();
			  }
			  int itemStack = item.getAmount();
			  
			  if(item.getItemMeta().getDisplayName()!=null){
				  itemName = item.getItemMeta().getDisplayName();
			  }
			  
			  
			  
			  List<String> location = plugin.getConfig().getStringList("fastT.loc.names");
				String[] items = location.toArray(new String[location.size()]);
				for(int i = 0; i < location.size(); i++){
					
					String name = items[i];
					
					
					
					
					  if(itemName.equals(ChatColor.GREEN + name)){
						  Player p = (Player) e.getWhoClicked();
						  
						  
						  double x = plugin.getConfig().getInt("fastT.loc."  + name + ".locx");
						  double y = plugin.getConfig().getInt("fastT.loc."  + name + ".locy");
						  double z = plugin.getConfig().getInt("fastT.loc."  + name + ".locz");
						  float pitch = plugin.getConfig().getInt("fastT.loc."  + name + ".locpitch");
						  float yaw = plugin.getConfig().getInt("fastT.loc."  + name + ".locyaw");
						  Location loc = new Location(p.getWorld(),x,y,z);
						  loc.setYaw(yaw);
						  loc.setPitch(pitch);
						  p.teleport(loc);
						  p.sendMessage(ChatColor.GREEN + "Teleported!");
						  e.setCancelled(true);
					  }
				}
			  
			 
			  
			  
			  
			  if(itemName.equals(ChatColor.RED + "Fire Arrows")){
				  
				  
				  List<String> current = userconfig.getStringList(e.getWhoClicked().getUniqueId() + ".custom");
				  current.add("fire");
				  userconfig.set(e.getWhoClicked().getUniqueId() + ".custom", current);
				  
				  Player p = (Player) e.getWhoClicked();
				  p.sendMessage(ChatColor.GREEN + "You have selected " + itemName + "!" );
				  
				  //Save document
				  try {
					userconfig.save(userfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  e.setCancelled(true);
				  e.getWhoClicked().playEffect(EntityEffect.VILLAGER_ANGRY);
				  ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.IRONGOLEM_DEATH, itemStack, itemStack);
				  
			  }else if(itemName.equals(ChatColor.RED + "Fire Arrows !")){
				  
				  e.setCancelled(true);
				  e.getWhoClicked().playEffect(EntityEffect.VILLAGER_ANGRY);
				  ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.IRONGOLEM_DEATH, itemStack, itemStack);
			  }else if(itemName.equals(ChatColor.RED + "Fire Arrows 2")){
				  
				  List<String> current = userconfig.getStringList(e.getWhoClicked().getUniqueId() + ".custom");
				  current.add("fire2");
				  userconfig.set(e.getWhoClicked().getUniqueId() + ".custom", current);
				  
				  Player p = (Player) e.getWhoClicked();
				  p.sendMessage(ChatColor.GREEN + "You have selected " + itemName + "!" );
				  //Save document
				  try {
					userconfig.save(userfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  e.setCancelled(true);
				  
				  
			  }else if(itemName.equals(ChatColor.RED + "Fire Arrows 2!")){
				  e.setCancelled(true);
				  e.getWhoClicked().playEffect(EntityEffect.VILLAGER_ANGRY);
				  ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.IRONGOLEM_DEATH, itemStack, itemStack);
			  }else if(itemName.equals(ChatColor.RED + "Dodge Arrow 1")){
				  List<String> current = userconfig.getStringList(e.getWhoClicked().getUniqueId() + ".custom");
				  current.add("fire3");
				  userconfig.set(e.getWhoClicked().getUniqueId() + ".custom", current);
				  
				  Player p = (Player) e.getWhoClicked();
				  p.sendMessage(ChatColor.GREEN + "You have selected " + itemName + "!" );
				  //Save document
				  try {
					userconfig.save(userfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  e.setCancelled(true);
			  }
			  else if(itemName.equals(ChatColor.RED + "Dodge Arrow 1!")){
				  e.setCancelled(true);
				  e.getWhoClicked().playEffect(EntityEffect.VILLAGER_ANGRY);
				  ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.IRONGOLEM_DEATH, itemStack, itemStack);
			  }else if(itemName.equals(ChatColor.RED + "Damaging Bow")){
				  List<String> current = userconfig.getStringList(e.getWhoClicked().getUniqueId() + ".custom");
				  current.add("fire4");
				  userconfig.set(e.getWhoClicked().getUniqueId() + ".custom", current);
				  
				  Player p = (Player) e.getWhoClicked();
				  p.sendMessage(ChatColor.GREEN + "You have selected " + itemName + "!" );
				  //Save document
				  try {
					userconfig.save(userfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  e.setCancelled(true);
			  }
			  else if(itemName.equals(ChatColor.RED + "Damaging Bow!")){
				  e.setCancelled(true);
				  e.getWhoClicked().playEffect(EntityEffect.VILLAGER_ANGRY);
				  ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.IRONGOLEM_DEATH, itemStack, itemStack);
			  }
			 
			  /*if(itemLore.equals(null)){
				  itemLore.add("None");
			  }*/
			  
			  Player p = (Player) e.getWhoClicked();
			  if(e.isCancelled()){
				  p.closeInventory();
			  }
		}
	}
	

}