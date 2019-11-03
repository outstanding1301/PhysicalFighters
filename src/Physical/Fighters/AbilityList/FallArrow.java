/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class FallArrow extends AbilityBase
/*     */ {
/*     */   public FallArrow()
/*     */   {
/*  31 */     if (PhysicalFighters.SRankUsed) {
/*  32 */       InitAbility("중력화살", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.S, new String[] {
/*  33 */         "화살에 맞은 플레이어는 공중으로 뜹니다. [추가타 가능]" });
/*  34 */       InitAbility(0, 0, true);
/*  35 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  36 */       EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  37 */       EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  38 */       EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  44 */     switch (CustomData) {
/*     */     case 0: 
/*  46 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  47 */       if ((Event0.getDamager() instanceof Arrow)) {
/*  48 */         Arrow a = (Arrow)Event0.getDamager();
/*  49 */         if (PlayerCheck((Player)a.getShooter())) {
/*  50 */           if (((Event0.getEntity() instanceof Player)) && 
/*  51 */             ((Player)a.getShooter() == 
/*  52 */             (Player)Event0
/*  53 */             .getEntity()))
/*  54 */             if (!EventManager.DamageGuard)
/*  55 */               return 9999;
/*  56 */           return 0;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  61 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  62 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  63 */         (Event1.getItemDrop().getItemStack().getType() == Material.ARROW)) {
/*  64 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  65 */         if (!inv.contains(Material.ARROW, 64)) {
/*  66 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  71 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  72 */       if (PlayerCheck(Event2.getPlayer()))
/*  73 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  76 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  77 */       if (PlayerCheck(Event3.getEntity()))
/*  78 */         return 3;
/*     */       break;
/*     */     }
/*  81 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  86 */     switch (CustomData) {
/*     */     case 0: 
/*  88 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  89 */       Player p = (Player)Event0.getEntity();
/*  90 */       Location l1 = Event0.getEntity().getLocation();
/*  91 */       Location l2 = Event0.getEntity().getLocation();
/*  92 */       l2.setY(l1.getY() + 4.0D);
/*  93 */       goVelocity(p, l2, 1);
/*  94 */       Event0.getEntity().getWorld()
/*  95 */         .createExplosion(Event0.getEntity().getLocation(), 0.0F);
/*  96 */       p.teleport(l2);
/*  97 */       break;
/*     */     case 1: 
/*  99 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/* 100 */       Event1.getPlayer().sendMessage(
/* 101 */         ChatColor.RED + "소유한 화살이 64개 이하일시 화살을 버릴수 없습니다.");
/* 102 */       Event1.setCancelled(true);
/* 103 */       break;
/*     */     case 2: 
/* 105 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 106 */       Event2.getPlayer().sendMessage(
/* 107 */         ChatColor.GREEN + "이전에 소유했던 화살은 모두 소멸하며 다시 지급됩니다.");
/* 108 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 109 */       inv.remove(new ItemStack(262, 64));
/* 110 */       inv.setItem(8, new ItemStack(262, 64));
/* 111 */       inv.setItem(7, new ItemStack(261, 1));
/* 112 */       break;
/*     */     case 3: 
/* 114 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 115 */       List<ItemStack> itemlist = Event3.getDrops();
/* 116 */       for (int l = 0; l < itemlist.size(); l++) {
/* 117 */         if (((ItemStack)itemlist.get(l)).getType() == Material.ARROW)
/* 118 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 124 */     p.getInventory().setItem(8, new ItemStack(262, 64));
/* 125 */     p.getInventory().setItem(7, new ItemStack(261, 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 130 */     p.getInventory().removeItem(new ItemStack[] { new ItemStack(262, 64) });
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\FallArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */