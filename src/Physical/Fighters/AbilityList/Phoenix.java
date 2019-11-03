/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Phoenix extends AbilityBase
/*     */ {
/*  26 */   private int ReviveCounter = 0;
/*  27 */   private boolean AbilityUse = false;
/*     */   
/*     */   public Phoenix() {
/*  30 */     if (!PhysicalFighters.Toner) {
/*  31 */       InitAbility("불사조", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.A, new String[] {
/*  32 */         "자연사할시 무제한으로 인벤토리를 잃지 않고 부활합니다.", 
/*  33 */         "타인에게 사망할 경우 1회에 한하여 자연사와 같이 부활합니다.", 
/*  34 */         "부활시 자신의 능력이 모두에게 알려지게 됩니다.", "하지만 이 능력도 데스노트만은 막을수 없을것입니다." });
/*  35 */       InitAbility(0, 0, true);
/*  36 */       EventManager.onEntityDeath.add(new EventData(this, 0));
/*  37 */       EventManager.onPlayerRespawn.add(new EventData(this, 1));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  43 */     switch (CustomData) {
/*     */     case 0: 
/*  45 */       EntityDeathEvent Event0 = (EntityDeathEvent)event;
/*  46 */       if (PlayerCheck(Event0.getEntity())) {
/*  47 */         return 0;
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  51 */       PlayerRespawnEvent Event1 = (PlayerRespawnEvent)event;
/*  52 */       if (PlayerCheck(Event1.getPlayer()))
/*  53 */         return 1;
/*     */       break;
/*     */     }
/*  56 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  61 */     switch (CustomData) {
/*     */     case 0: 
/*  63 */       PlayerDeathEvent Event0 = (PlayerDeathEvent)event;
/*  64 */       Player killed = Event0.getEntity();
/*     */       
/*  66 */       EventManager.invsave.put(killed, killed.getInventory()
/*  67 */         .getContents());
/*  68 */       EventManager.arsave.put(killed, killed.getInventory()
/*  69 */         .getArmorContents());
/*  70 */       Event0.getDrops().clear();
/*     */       
/*  72 */       if (this.AbilityUse) {
/*  73 */         Bukkit.broadcastMessage(ChatColor.RED + 
/*  74 */           "불사조가 죽었습니다. 더 이상 부활할수 없습니다.");
/*  75 */         if (PhysicalFighters.AutoKick) {
/*  76 */           if (PhysicalFighters.AutoBan) {
/*  77 */             killed.setBanned(true);
/*  78 */             killed.kickPlayer("당신은 죽었습니다. 다시 들어오실 수 없습니다.");
/*     */           } else {
/*  80 */             killed.kickPlayer("당신은 죽었습니다. 게임에서 퇴장합니다.");
/*     */           }
/*     */         }
/*     */       } else {
/*  84 */         Bukkit.broadcastMessage(ChatColor.GREEN + 
/*  85 */           "불사조가 죽었습니다. 다시 부활할 수 있습니다.");
/*     */       }
/*     */       
/*  88 */       if ((killed.getKiller() instanceof Player)) {
/*  89 */         this.AbilityUse = true;
/*     */       }
/*  91 */       this.ReviveCounter += 1;
/*     */       
/*  93 */       break;
/*     */     case 1: 
/*  95 */       PlayerRespawnEvent Event1 = (PlayerRespawnEvent)event;
/*  96 */       ItemStack[] ar = (ItemStack[])EventManager.arsave.get(Event1.getPlayer());
/*  97 */       ItemStack[] inv = (ItemStack[])EventManager.invsave.get(Event1.getPlayer());
/*     */       
/*  99 */       if (ar != null) {
/* 100 */         Event1.getPlayer().getInventory().setArmorContents(ar);
/*     */       }
/* 102 */       if (inv != null) {
/* 103 */         Event1.getPlayer().getInventory().setContents(inv);
/*     */       }
/* 105 */       EventManager.arsave.remove(Event1.getPlayer());
/* 106 */       EventManager.invsave.remove(Event1.getPlayer());
/* 107 */       if (!this.AbilityUse) {
/* 108 */         Bukkit.broadcastMessage(ChatColor.GREEN + 
/* 109 */           "불사조가 부활하였습니다. 부활 횟수 : " + 
/* 110 */           String.valueOf(this.ReviveCounter) + "회");
/*     */       }
/*     */       
/* 113 */       Event1.getPlayer().addPotionEffect(
/* 114 */         new PotionEffect(PotionEffectType.FAST_DIGGING, 
/* 115 */         600, 0), true);
/* 116 */       Event1.getPlayer().addPotionEffect(
/* 117 */         new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 
/* 118 */         0), true);
/* 119 */       Event1.getPlayer().addPotionEffect(
/* 120 */         new PotionEffect(PotionEffectType.JUMP, 600, 0), true);
/* 121 */       Event1.getPlayer().addPotionEffect(
/* 122 */         new PotionEffect(PotionEffectType.SPEED, 600, 0), true);
/* 123 */       Event1.getPlayer().addPotionEffect(
/* 124 */         new PotionEffect(PotionEffectType.WATER_BREATHING, 600, 
/* 125 */         0), true);
/* 126 */       Event1.getPlayer()
/* 127 */         .addPotionEffect(
/* 128 */         new PotionEffect(PotionEffectType.REGENERATION, 
/* 129 */         600, 0), true);
/* 130 */       Event1.getPlayer().addPotionEffect(
/* 131 */         new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 
/* 132 */         600, 0), true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p)
/*     */   {
/* 138 */     this.ReviveCounter = 0;
/* 139 */     this.AbilityUse = false;
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Phoenix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */