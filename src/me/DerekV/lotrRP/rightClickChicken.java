package me.DerekV.lotrRP;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class rightClickChicken implements Listener{
	
	 @EventHandler
	 public void onPlayerUse(PlayerInteractEvent event){
		 Player p = (Player) event.getPlayer();
		 if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
		    if(p.getItemInHand().getType().equals(Material.CLAY_BALL)){
		    	if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "????")){
					//CraftWorld cw = (CraftWorld)p.getWorld();
					//CustomEntityChicken bc = new CustomEntityChicken(cw.getHandle());
					//bc.setPosition(p.getTargetBlock(null,100).getX(), p.getTargetBlock(null,100).getY(), p.getTargetBlock(null,100).getZ());
					//cw.getHandle().addEntity(bc);
		    		Material chest = Material.CHEST;
		    		
		    		
		    		
		    		Silverfish mimic = (Silverfish) p.getWorld().spawnCreature(p.getLocation(), EntityType.SILVERFISH);
		    		FallingBlock block = mimic.getWorld().spawnFallingBlock(mimic.getLocation().clone().add(0, 3 /* or 2, what you find better :)*/, 0), chest, (byte) 0);
		    		block.setDropItem(false);
		    		block.setVelocity(new Vector(0,0,0));
		    		mimic.setPassenger(block);
					p.getInventory().removeItem(new ItemStack(Material.CLAY_BALL, 1));
		    	}
		    }
		 }
		 
		 
		 
	 }
	 
	 @EventHandler
	    public void checkBlock(EntityChangeBlockEvent e) {
		    if(e.getEntity() instanceof FallingBlock) {
		    	 FallingBlock fblock = (FallingBlock) e.getEntity();
		         if(fblock.getMaterial() == Material.CHEST) {
		             e.getBlock().setType(Material.CHEST);
		             if(e.getBlock().getType() == Material.CHEST) {
		            	e.setCancelled(true);
		               Chest chest = (Chest) e.getBlock().getState();
		               double i = Math.random() * 10 + 1;
		            	   chest.getBlockInventory().addItem(new ItemStack(Material.ARROW, (int) (i + 1)));
		            	   chest.getBlockInventory().addItem(new ItemStack(Material.APPLE, (int) (i + 3)));
		            	   chest.getBlockInventory().addItem(new ItemStack(Material.GOLD_INGOT, (int) Math.round(i / 3)));
		            	   chest.getBlockInventory().addItem(new ItemStack(Material.GOLD_NUGGET, 1 * 2));
		            	   if(i > 9){
		            		   ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET,1);
		            		   ItemMeta im = helmet.getItemMeta();
		            		   im.setDisplayName(ChatColor.GREEN + "The mimics helmet");
		            		   im.addEnchant(Enchantment.THORNS, 3, true);
		            		   im.addEnchant(Enchantment.DURABILITY, 5, true);
		            		   im.addEnchant(Enchantment.PROTECTION_FALL, 3, true);
		            		   helmet.setItemMeta(im);
		            		   chest.getBlockInventory().addItem(helmet);
		            	   }
		         }
		    }   
	 	}
	 }
}
