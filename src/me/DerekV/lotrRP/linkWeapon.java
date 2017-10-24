package me.DerekV.lotrRP;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_7_R3.ChatClickable;
import net.minecraft.server.v1_7_R3.ChatHoverable;
import net.minecraft.server.v1_7_R3.ChatMessage;
import net.minecraft.server.v1_7_R3.ChatModifier;
import net.minecraft.server.v1_7_R3.EnumClickAction;
import net.minecraft.server.v1_7_R3.EnumHoverAction;
import net.minecraft.server.v1_7_R3.IChatBaseComponent;
import net.minecraft.server.v1_7_R3.MinecraftServer;
import net.minecraft.server.v1_7_R3.PlayerList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class linkWeapon implements Listener{
	
	 public static lotrRP plugin;
	   
	 public linkWeapon(lotrRP instance) {
	     plugin = instance; 
	 }
	   public static void printHoverable(String pname, String ChatMsg, String ClickableMsg, String HoverMsg) {
	        IChatBaseComponent base;
	        base = new ChatMessage(ChatMsg);
	        base.setChatModifier(new ChatModifier());
	        base.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.SUGGEST_COMMAND, ClickableMsg));
	        base.getChatModifier().a(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage(HoverMsg)));
	        PlayerList list = MinecraftServer.getServer().getPlayerList();
	        list.getPlayer(pname).sendMessage(base);
	    }
	 
	
	@EventHandler
	public void onMiddleClick(InventoryClickEvent e){
		if(e.getClick() == ClickType.MIDDLE){
			plugin.logger.info(ChatColor.RED + "Middle click");
			
			  ItemStack item = e.getCurrentItem();
			  String itemName = item.getType().toString();;
			  List<String> itemLore = item.getItemMeta().getLore();
			  
			  int itemStack = item.getAmount();
			  
			  if(item.getItemMeta().getDisplayName()!=null){
				  itemName = item.getItemMeta().getDisplayName();
			  }
			 
			  /*if(itemLore.equals(null)){
				  itemLore.add("None");
			  }*/
			  
			  
			for(Player p : Bukkit.getOnlinePlayers()){
				printHoverable(p.getName(), ChatColor.GREEN + e.getWhoClicked().getName().toString() + " wants to sell '" + ChatColor.GOLD + itemName + ChatColor.GREEN + "'", "", "Item Name - " + item.getItemMeta().getDisplayName() + "\nLore - " + itemLore + "\nAmount - " + Integer.toString(itemStack) + "\nEnchants - " + item.getEnchantments() +"\nActual Item - " + item.getType().toString());
			}
		}
	}
	

}
