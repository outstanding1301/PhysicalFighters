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
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
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
/*     */ public class Kaiji extends AbilityBase
/*     */ {
/*     */   public Kaiji()
/*     */   {
/*  28 */     if (!PhysicalFighters.Toner) {
/*  29 */       InitAbility("카이지", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.S, new String[] {
/*  30 */         "다이아몬드로 상대를 타격할시에 30%확률로 상대를 즉사시키고,", "70%확률로 사망합니다." });
/*  31 */       InitAbility(20, 0, true);
/*  32 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  33 */       EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  34 */       EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  35 */       EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  41 */     switch (CustomData) {
/*     */     case 0: 
/*  43 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  44 */       if ((!EventManager.DamageGuard) && 
/*  45 */         (PlayerCheck(Event.getDamager())) && 
/*  46 */         (ItemCheck(Material.DIAMOND.getId())))
/*  47 */         return 0;
/*     */       break;
/*     */     case 1: 
/*  50 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  51 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  52 */         (Event1.getItemDrop().getItemStack().getType() == Material.DIAMOND)) {
/*  53 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  54 */         if (!inv.contains(Material.DIAMOND, 1)) {
/*  55 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  60 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  61 */       if (PlayerCheck(Event2.getPlayer()))
/*  62 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  65 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  66 */       if (PlayerCheck(Event3.getEntity()))
/*  67 */         return 3;
/*     */       break;
/*     */     }
/*  70 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  75 */     switch (CustomData) {
/*     */     case 0: 
/*  77 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  78 */       Player p = (Player)Event.getEntity();
/*  79 */       if (Math.random() <= 0.3D) {
/*  80 */         p.damage(5000);
/*  81 */         Bukkit.broadcastMessage(String.format(ChatColor.RED + 
/*  82 */           "%s님이  카'의지'에 능력에 의지가 꺾였습니다.", new Object[] {
/*  83 */           p.getName() }));
/*  84 */         if (PhysicalFighters.AutoKick) {
/*  85 */           p.kickPlayer("카이지에 의해 사망했습니다.");
/*  86 */           if (PhysicalFighters.AutoBan)
/*  87 */             p.setBanned(true);
/*     */         }
/*     */       } else {
/*  90 */         GetPlayer().damage(5000);
/*  91 */         Bukkit.broadcastMessage(String.format(ChatColor.RED + 
/*  92 */           "%s님이  도박하다가 손목이 날라갔습니다.", new Object[] {
/*  93 */           GetPlayer().getName() }));
/*  94 */         if (PhysicalFighters.AutoKick) {
/*  95 */           GetPlayer().kickPlayer("카이지에 의해 사망했습니다.");
/*  96 */           if (PhysicalFighters.AutoBan)
/*  97 */             GetPlayer().setBanned(true);
/*     */         }
/*     */       }
/* 100 */       break;
/*     */     case 1: 
/* 102 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/* 103 */       Event1.getPlayer().sendMessage(ChatColor.RED + "다이아는 버릴 수 없습니다.");
/* 104 */       Event1.setCancelled(true);
/* 105 */       break;
/*     */     case 2: 
/* 107 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 108 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 109 */       inv.setItem(8, new ItemStack(Material.DIAMOND.getId(), 1));
/* 110 */       break;
/*     */     case 3: 
/* 112 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 113 */       List<ItemStack> itemlist = Event3.getDrops();
/* 114 */       for (int l = 0; l < itemlist.size(); l++) {
/* 115 */         if (((ItemStack)itemlist.get(l)).getType() == Material.DIAMOND)
/* 116 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 122 */     p.getInventory().setItem(8, new ItemStack(Material.DIAMOND.getId(), 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 127 */     p.getInventory().setItem(8, new ItemStack(Material.DIAMOND.getId(), 1));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Kaiji.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */