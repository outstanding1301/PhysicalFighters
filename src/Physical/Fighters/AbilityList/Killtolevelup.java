/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Killtolevelup extends AbilityBase
/*     */ {
/*  26 */   int dama = 5;
/*     */   
/*  28 */   public Killtolevelup() { InitAbility("폭주", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.SS, new String[] {
/*  29 */       "깃털의 처음 데미지는 5입니다.", 
/*  30 */       "깃털로 1킬을 할때마다 데미지가 2씩 늘어납니다."});
/*  32 */     InitAbility(0, 0, true);
/*  33 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  34 */     EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  35 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  36 */     EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  41 */     switch (CustomData) {
/*     */     case 0: 
/*  43 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  44 */       if ((PlayerCheck(Event.getDamager())) && (ItemCheck(Material.FEATHER.getId())))
/*  45 */         return 0;
/*     */       break;
/*     */     case 1: 
/*  48 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  49 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  50 */         (Event1.getItemDrop().getItemStack().getType() == Material.FEATHER)) {
/*  51 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  52 */         if (!inv.contains(Material.FEATHER, 1)) {
/*  53 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  58 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  59 */       if (PlayerCheck(Event2.getPlayer()))
/*  60 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  63 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  64 */       if (PlayerCheck(Event3.getEntity()))
/*  65 */         return 3;
/*  66 */       if ((Event3.getEntity().getKiller() != null) && 
/*  67 */         (PlayerCheck(Event3.getEntity().getKiller())) && (ItemCheck(Material.FEATHER.getId())) && ((Event3.getEntity() instanceof Player)))
/*  68 */         return 4;
/*     */       break;
/*     */     }
/*  71 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  76 */     switch (CustomData) {
/*     */     case 0: 
/*  78 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  79 */       Event.setDamage(Event.getDamage() + this.dama);
/*  80 */       break;
/*     */     case 1: 
/*  82 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  83 */       Event1.getPlayer().sendMessage(ChatColor.RED + "깃털은 버릴 수 없습니다.");
/*  84 */       Event1.setCancelled(true);
/*  85 */       break;
/*     */     case 2: 
/*  87 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  88 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/*  89 */       inv.setItem(8, new ItemStack(Material.FEATHER.getId(), 1));
/*  90 */       break;
/*     */     case 3: 
/*  92 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  93 */       List<ItemStack> itemlist = Event3.getDrops();
/*  94 */       for (int l = 0; l < itemlist.size(); l++) {
/*  95 */         if (((ItemStack)itemlist.get(l)).getType() == Material.FEATHER) {
/*  96 */           itemlist.remove(l);
/*     */         }
/*     */       }
/*  99 */       break;
/*     */     case 4: 
/* 101 */       EntityDeathEvent Event4 = (EntityDeathEvent)event;
/* 102 */       //if (this.dama < 12)
/*     */       //{
/* 104 */         this.dama += 2;
/* 105 */         Bukkit.broadcastMessage(String.format(ChatColor.RED + "%s님을 죽이고 %s님이  폭주했습니다.", new Object[] {
/* 106 */           ((Player)Event4.getEntity()).getName(), Event4.getEntity().getKiller().getName() }));
/* 107 */         Event4.getEntity().getKiller().sendMessage(ChatColor.RED + "붉은 피를보니... 내가 더 강해진 것 같군.. 큭..");
/*     */       //}
/*     */       //else
/*     */       //{
/* 111 */         //this.dama = 12;
/* 112 */         //Event4.getEntity().getKiller().sendMessage(ChatColor.RED + "당신은 이미 충분히 성장했습니다.");
/*     */       //}
/*     */       break;
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p)
/*     */   {
/* 120 */     p.getInventory().setItem(8, new ItemStack(Material.FEATHER.getId(), 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 125 */     p.getInventory().setItem(8, new ItemStack(Material.FEATHER.getId(), 1));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Killtolevelup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */