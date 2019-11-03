/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Kimimaro extends AbilityBase
/*     */ {
/*     */   public Kimimaro()
/*     */   {
/*  28 */     InitAbility("키미마로", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.SS, new String[] {
/*  29 */       "뼈다귀로 상대를 공격할시에 강한 데미지를 주고,", 
/*  30 */       "40% 확률로 상대에게 10초간 독효과를 준다." });
/*  31 */     InitAbility(0, 0, true);
/*  32 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  33 */     EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  34 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  35 */     EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  40 */     switch (CustomData) {
/*     */     case 0: 
/*  42 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  43 */       if ((PlayerCheck(Event.getDamager())) && (ItemCheck(Material.BONE.getId())))
/*  44 */         return 0;
/*     */       break;
/*     */     case 1: 
/*  47 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  48 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  49 */         (Event1.getItemDrop().getItemStack().getType() == Material.BONE)) {
/*  50 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  51 */         if (!inv.contains(Material.BONE, 1)) {
/*  52 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  57 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  58 */       if (PlayerCheck(Event2.getPlayer()))
/*  59 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  62 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  63 */       if (PlayerCheck(Event3.getEntity()))
/*  64 */         return 3;
/*     */       break;
/*     */     }
/*  67 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  72 */     switch (CustomData) {
/*     */     case 0: 
/*  74 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*  75 */       Event.setDamage(Event.getDamage() + 10);
/*  76 */       Player p = (Player)Event.getEntity();
/*  77 */       if (Math.random() <= 0.4D)
/*  78 */         p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0), true);
/*  79 */       break;
/*     */     case 1: 
/*  81 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  82 */       Event1.getPlayer().sendMessage(ChatColor.RED + "뼈는 버릴 수 없습니다.");
/*  83 */       Event1.setCancelled(true);
/*  84 */       break;
/*     */     case 2: 
/*  86 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  87 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/*  88 */       inv.setItem(8, new ItemStack(Material.BONE.getId(), 1));
/*  89 */       break;
/*     */     case 3: 
/*  91 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  92 */       List<ItemStack> itemlist = Event3.getDrops();
/*  93 */       for (int l = 0; l < itemlist.size(); l++) {
/*  94 */         if (((ItemStack)itemlist.get(l)).getType() == Material.BONE)
/*  95 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 101 */     p.getInventory().setItem(8, new ItemStack(Material.BONE.getId(), 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 106 */     p.getInventory().setItem(8, new ItemStack(Material.BONE.getId(), 1));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Kimimaro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */