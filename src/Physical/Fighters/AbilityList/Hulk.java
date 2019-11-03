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
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.World;
import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class Hulk extends AbilityBase
/*     */ {
/*  24 */   boolean playerhulk = false;
/*  25 */   int playerhealth = 20;
/*     */   
/*  27 */   public Hulk() { if (PhysicalFighters.SRankUsed) {
/*  28 */       InitAbility("헐크", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SSS, new String[] {
/*  29 */         "철괴 오른쪽클릭시에 30초간 매우 강해집니다.", 
/*  30 */         "버프를 받으며, 모든 데미지를 반으로 줄여받으며, 일부 액티브능력을 무시합니다.", 
/*  31 */         "당신의 데미지는 1.5배가 되며, 당신의 공격 범위가 넓어집니다." });
/*  32 */       InitAbility(180, 0, true);
/*  33 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*  34 */       RegisterRightClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  40 */     switch (CustomData) {
/*     */     case 0: 
/*  42 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/*  43 */       if ((Event1.getEntity() instanceof Player)) {
/*  44 */         if (PlayerCheck(Event1.getDamager()))
/*  45 */           if (this.playerhulk)
/*     */           {
/*  47 */             Event1.setDamage((int)(Event1.getDamage() * 1.5D));
/*  48 */             ((Player)Event1.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0), true);
/*     */           }
/*     */           else {
/*  51 */             Event1.setDamage(Event1.getDamage());
/*     */           }
/*  53 */         if (PlayerCheck(Event1.getEntity())) {
/*  54 */           if (this.playerhulk)
/*     */           {
/*  56 */             Event1.setDamage(Event1.getDamage() / 2);
/*     */           }
/*     */           else
/*  59 */             Event1.setDamage(Event1.getDamage());
/*     */         }
/*     */       }
/*  62 */       break;
/*     */     case 1: 
/*  64 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/*  65 */       if ((PlayerCheck(Event2.getPlayer())) && 
/*  66 */         (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/*  67 */         return 0;
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/*  72 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  77 */     PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/*  78 */     Player p = Event2.getPlayer();
/*  79 */     this.playerhealth = ((int)((Damageable)p).getHealth());
/*  80 */     p.getWorld().createExplosion(p.getLocation(), 0.0F);
/*  81 */     p.setHealth(20);
/*  82 */     p.sendMessage(ChatColor.RED + "당신은 헐크로 변신했으며, 30초간 무척 강해집니다. 30초가 지나면 당신은 원래대로 돌아옵니다.");
/*  83 */     this.playerhulk = true;
/*  84 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 0), true);
/*  85 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0), true);
/*  86 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 0), true);
/*  87 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0), true);
/*  88 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 0), true);
/*  89 */     GetPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 0), true);
/*  90 */     Timer timer = new Timer();
/*  91 */     timer.schedule(new Pauck(GetPlayer()), 30000L);
/*     */   }
/*     */   
/*     */   class Pauck extends TimerTask
/*     */   {
/*     */     Player p1;
/*     */     
/*     */     Pauck(Player pp1)
/*     */     {
/* 100 */       this.p1 = pp1;
/*     */     }
/*     */     
/*     */     public void run() {
/* 104 */       this.p1.setHealth(Hulk.this.playerhealth);
/* 105 */       Hulk.this.playerhulk = false;
/* 106 */       this.p1.sendMessage(ChatColor.GREEN + "원래대로 돌아왔습니다.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Hulk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */