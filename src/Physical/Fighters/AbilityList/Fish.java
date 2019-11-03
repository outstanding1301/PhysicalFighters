/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerDropItemEvent;
/*    */ import org.bukkit.event.player.PlayerRespawnEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class Fish extends Physical.Fighters.MainModule.AbilityBase
/*    */ {
/*    */   public Fish()
/*    */   {
/* 20 */     InitAbility("강태공", Physical.Fighters.MainModule.AbilityBase.Type.Passive_Manual, Physical.Fighters.MainModule.AbilityBase.Rank.A, new String[] {
/* 21 */       "낚싯대로 상대를 타격시 상대에게 강한 데미지를 주고, 매우 낮은 확률로 물고기를 얻습니다.", 
/* 22 */       "물고기를 들고 상대를 타격시에, 더욱더 강한 데미지를 줍니다." });
/* 23 */     InitAbility(0, 0, true);
/* 24 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/* 25 */     EventManager.onPlayerDropItem.add(new EventData(this, 1));
/* 26 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 31 */     switch (CustomData) {
/*    */     case 0: 
/* 33 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 34 */       if ((PlayerCheck(Event.getDamager())) && 
/* 35 */         (ItemCheck(Material.FISHING_ROD.getId())))
/* 36 */         return 0;
/* 37 */       if ((PlayerCheck(Event.getDamager())) && (ItemCheck(349))) {
/* 38 */         return 3;
/*    */       }
/*    */       
/*    */       break;
/*    */     case 1: 
/* 43 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/* 44 */       if ((PlayerCheck(Event1.getPlayer())) && 
/* 45 */         (Event1.getItemDrop().getItemStack().getType() == Material.FISHING_ROD)) {
/* 46 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/* 47 */         if (!inv.contains(Material.FISHING_ROD, 1)) {
/* 48 */           return 1;
/*    */         }
/*    */       }
/*    */       break;
/*    */     case 2: 
/* 53 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 54 */       if (PlayerCheck(Event2.getPlayer()))
/* 55 */         return 2;
/*    */       break;
/*    */     }
/* 58 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 63 */     switch (CustomData) {
/*    */     case 0: 
/* 65 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/* 66 */       Event0.setDamage(Event0.getDamage() + 7);
/* 67 */       if (Math.random() <= 0.05D)
/*    */       {
/*    */ 
/* 70 */         Event0.getEntity().getWorld().dropItemNaturally(Event0.getEntity().getLocation(), 
/* 71 */           new ItemStack(349)); }
/* 72 */       break;
/*    */     case 1: 
/* 74 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/* 75 */       Event1.getPlayer().sendMessage(org.bukkit.ChatColor.RED + "낚싯대는 버릴 수 없습니다.");
/* 76 */       Event1.setCancelled(true);
/* 77 */       break;
/*    */     case 2: 
/* 79 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 80 */       Event2.getPlayer().sendMessage(org.bukkit.ChatColor.GREEN + "낚싯대가 지급됩니다.");
/* 81 */       Event2.getPlayer().getInventory()
/* 82 */         .setItem(8, new ItemStack(Material.FISHING_ROD.getId(), 1));
/* 83 */       break;
/*    */     case 3: 
/* 85 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 86 */       Event.setDamage(Event.getDamage() + 12);
/*    */     }
/*    */   }
/*    */   
/*    */   public void A_SetEvent(Player p)
/*    */   {
/* 92 */     p.getInventory().setItem(8, 
/* 93 */       new ItemStack(Material.FISHING_ROD.getId(), 1));
/*    */   }
/*    */   
/*    */   public void A_ResetEvent(Player p)
/*    */   {
/* 98 */     p.getInventory().setItem(8, 
/* 99 */       new ItemStack(Material.FISHING_ROD.getId(), 1));
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Fish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */