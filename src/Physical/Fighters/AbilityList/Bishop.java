/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Bishop extends AbilityBase
/*     */ {
/*  22 */   private final int DurationTime = 300;
/*     */   
/*     */   public Bishop() {
/*  25 */     if (!PhysicalFighters.Specialability) {
/*  26 */       InitAbility("비숍", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] {
/*  27 */         "철괴 왼클릭시 맞은 사람에게 각종 축복을 겁니다.", "철괴 오른클릭시 자신에게 각종 축복을 겁니다.", 
/*  28 */         "금괴를 적에게 왼클릭시 각종 저주를 겁니다.", "세 기능은 쿨타임을 공유하며 모든 효과 지속시간은", 
/*  29 */         "15초입니다." });
/*  30 */       InitAbility(30, 0, true);
/*  31 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*  32 */       RegisterRightClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  38 */     switch (CustomData) {
/*     */     case 0: 
/*  40 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/*  41 */       if (((Event1.getEntity() instanceof Player)) && 
/*  42 */         (PlayerCheck(Event1.getDamager())) && !EventManager.DamageGuard) {
/*  43 */         if (ItemCheck(ACC.DefaultItem))
/*  44 */           return 0;
/*  45 */         if (ItemCheck(Material.GOLD_INGOT.getId())) {
/*  46 */           return 2;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  51 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/*  52 */       if ((PlayerCheck(Event2.getPlayer())) && 
/*  53 */         (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/*  54 */         return 1;
/*     */       }
/*     */       break;
/*     */     }
/*  58 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  63 */     switch (CustomData) {
/*     */     case 0: 
/*  65 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  66 */       Player p0 = (Player)Event0.getEntity();
/*  67 */       p0.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 
/*  68 */         300, 0), true);
/*  69 */       p0.addPotionEffect(new PotionEffect(
/*  70 */         PotionEffectType.DAMAGE_RESISTANCE, 300, 0), true);
/*  71 */       p0.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 
/*  72 */         300, 0), true);
/*  73 */       p0.addPotionEffect(new PotionEffect(
/*  74 */         PotionEffectType.WATER_BREATHING, 300, 0), true);
/*  75 */       p0.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 
/*  76 */         300, 0), true);
/*  77 */       p0.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 
/*  78 */         300, 0), true);
/*  79 */       p0.sendMessage(ChatColor.GREEN + "비숍이 당신에게 축복을 걸었습니다. 15초 지속.");
/*  80 */       Event0.setCancelled(true);
/*  81 */       break;
/*     */     case 1: 
/*  83 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/*  84 */       Player p1 = Event1.getPlayer();
/*  85 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 
/*  86 */         300, 0), true);
/*  87 */       p1.addPotionEffect(new PotionEffect(
/*  88 */         PotionEffectType.DAMAGE_RESISTANCE, 300, 0), true);
/*  89 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 
/*  90 */         300, 0), true);
/*  91 */       p1.addPotionEffect(new PotionEffect(
/*  92 */         PotionEffectType.WATER_BREATHING, 300, 0), true);
/*  93 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 
/*  94 */         300, 0), true);
/*  95 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 
/*  96 */         300, 0), true);
/*  97 */       p1.sendMessage(ChatColor.GREEN + "자신에게 축복을 걸었습니다. 15초 지속.");
/*  98 */       break;
/*     */     case 2: 
/* 100 */       EntityDamageByEntityEvent Event2 = (EntityDamageByEntityEvent)event;
/* 101 */       Player p2 = (Player)Event2.getEntity();
/* 102 */       p2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 
/* 103 */         300, 0), true);
/* 104 */       p2.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 
/* 105 */         300, 0), true);
/* 106 */       p2.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 
/* 107 */         300, 0), true);
/* 108 */       p2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 
/* 109 */         300, 0), true);
/* 110 */       p2.sendMessage(ChatColor.RED + "비숍이 당신에게 저주를 걸었습니다. 15초 지속.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Bishop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */