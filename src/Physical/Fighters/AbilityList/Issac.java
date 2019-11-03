/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Issac
/*     */   extends AbilityBase
/*     */ {
/*  29 */   private int bullet = 0;
/*     */   private int item;
/*     */   
/*     */   public Issac() {
/*  33 */     InitAbility("아이작", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/*  34 */       "금괴를 들고 우클릭시 눈물을 발사합니다.", 
/*  35 */       "철괴 하나당 5개의 눈물을 발사할 수 있습니다.", 
/*  36 */       "눈물의 데미지는 3입니다." });
/*  37 */     InitAbility(0, 0, true, AbilityBase.ShowText.Custom_Text);
/*     */     
/*  39 */     RegisterRightClickEvent();
/*  40 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 3));
/*  41 */     EventManager.onProjectileHitEvent.add(new EventData(this, 5));
/*  42 */     this.item = Material.GOLD_INGOT.getId();
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  46 */     switch (CustomData) {
/*     */     case 1: 
/*  48 */       PlayerInteractEvent Event0 = (PlayerInteractEvent)event;
/*  49 */       if ((!EventManager.DamageGuard) && 
/*  50 */         (PlayerCheck(Event0.getPlayer())) && (ItemCheck(this.item))) {
/*  51 */         if (this.bullet != 0) {
/*  52 */           return 10;
/*     */         }
/*     */         
/*     */ 
/*  56 */         if (GetPlayer().getInventory().contains(Material.IRON_INGOT)) {
/*  57 */           return 20;
/*     */         }
/*  59 */         GetPlayer().sendMessage(ChatColor.RED + "철괴가 없습니다.");
/*     */       }
/*     */       
/*  62 */       break;
/*     */     case 3: 
/*  64 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/*  65 */       if ((Event1.getDamager() instanceof Snowball)) {
/*  66 */         Snowball a = (Snowball)Event1.getDamager();
/*  67 */         if (PlayerCheck((Entity)a.getShooter())) {
/*  68 */           if (((Event1.getEntity() instanceof Player)) && 
/*  69 */             ((Player)a.getShooter() == 
/*  70 */             (Player)Event1
/*  71 */             .getEntity()))
/*  72 */             return -1;
/*  73 */           return 3;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 5: 
/*  78 */       ProjectileHitEvent Event2 = (ProjectileHitEvent)event;
/*  79 */       if ((Event2.getEntity() instanceof Snowball)) {
/*  80 */         Snowball a = (Snowball)Event2.getEntity();
/*  81 */         if (((a.getShooter() instanceof Player)) && 
/*  82 */           (PlayerCheck((Player)a.getShooter()))) {
/*  83 */           a.remove();
/*  84 */           return -2;
/*     */         }
/*     */       }
/*     */       
/*     */       break;
/*     */     }
/*     */     
/*  91 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  97 */     switch (CustomData) {
/*     */     case 3: 
/*  99 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*     */       
/* 101 */       Event0.setDamage(6);
/* 102 */       break;
/*     */     case 10: 
/* 104 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/* 105 */       this.bullet -= 1;
/* 106 */       Event1.getPlayer().launchProjectile(Snowball.class);
/* 107 */       break;
/*     */     case 20: 
/* 109 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 110 */       PlayerInventory inv = GetPlayer().getInventory();
/* 111 */       int sell = inv.first(Material.IRON_INGOT);
/* 112 */       if (inv.getItem(sell).getAmount() == 1) {
/* 113 */         inv.clear(sell);
/*     */       } else {
/* 115 */         inv.getItem(sell).setAmount(inv.getItem(sell).getAmount() - 1);
/*     */       }
/* 117 */       GetPlayer().updateInventory();
/* 118 */       this.bullet = 5;
/* 119 */       GetPlayer().sendMessage(ChatColor.GREEN + "눈물을 충전했습니다.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Issac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */