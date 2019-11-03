/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Tranceball extends AbilityBase
/*     */ {
/*     */   public Tranceball()
/*     */   {
/*  25 */     if (Physical.Fighters.PhysicalFighters.SRankUsed) {
/*  26 */       InitAbility("트랜스볼", Physical.Fighters.MainModule.AbilityBase.Type.Passive_Manual, AbilityBase.Rank.SS, new String[] {
/*  27 */         "눈덩이를 던져, 맞은 적과 위치를 바꿉니다." });
/*  28 */       InitAbility(1, 0, true);
/*  29 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  30 */       EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  31 */       EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  32 */       EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  38 */     switch (CustomData) {
/*     */     case 0: 
/*  40 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  41 */       if ((!EventManager.DamageGuard) && 
/*  42 */         ((Event0.getDamager() instanceof Snowball))) {
/*  43 */         Snowball a = (Snowball)Event0.getDamager();
/*  44 */         if (PlayerCheck((Player)a.getShooter())) {
/*  45 */           if (((Event0.getEntity() instanceof Player)) && ((Player)a.getShooter() == (Player)Event0.getEntity()))
/*  46 */             return 9999;
/*  47 */           return 0;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  52 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  53 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  54 */         (Event1.getItemDrop().getItemStack().getType() == Material.SNOW_BALL)) {
/*  55 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  56 */         if (!inv.contains(Material.SNOW_BALL, 16)) {
/*  57 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  62 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  63 */       if (PlayerCheck(Event2.getPlayer()))
/*  64 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  67 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  68 */       if (PlayerCheck(Event3.getEntity()))
/*  69 */         return 3;
/*     */       break;
/*     */     }
/*  72 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  77 */     switch (CustomData) {
/*     */     case 0: 
/*  79 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  80 */       Player pe = (Player)Event0.getEntity();
/*  81 */       Location l1 = pe.getLocation();
/*  82 */       Location l2 = GetPlayer().getLocation();
/*  83 */       GetPlayer().teleport(l1);
/*  84 */       pe.teleport(l2);
/*  85 */       break;
/*     */     case 1: 
/*  87 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  88 */       Event1.getPlayer().sendMessage(ChatColor.RED + "소유한 눈덩이가 16개 이하일시 못버립니다.");
/*  89 */       Event1.setCancelled(true);
/*  90 */       break;
/*     */     case 2: 
/*  92 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  93 */       Event2.getPlayer().sendMessage(ChatColor.GREEN + "눈덩이가 지급됩니다.");
/*  94 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/*  95 */       inv.setItem(8, new ItemStack(332, 64));
/*  96 */       inv.setItem(7, new ItemStack(332, 64));
/*  97 */       break;
/*     */     case 3: 
/*  99 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 100 */       List<ItemStack> itemlist = Event3.getDrops();
/* 101 */       for (int l = 0; l < itemlist.size(); l++) {
/* 102 */         if (((ItemStack)itemlist.get(l)).getType() == Material.SNOW_BALL)
/* 103 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 109 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 110 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 115 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 116 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Tranceball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */