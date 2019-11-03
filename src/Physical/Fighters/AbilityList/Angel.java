/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ 
/*    */ public class Angel extends AbilityBase
/*    */ {
/* 19 */   public static String pp = "false";
/* 20 */   public static boolean ppon = false;
/*    */   
/*    */   public Angel() {
/* 23 */     if (Physical.Fighters.PhysicalFighters.Gods) {
/* 24 */       InitAbility("천사", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.GOD, new String[] {
/* 25 */         "철괴로 타격받은 대상에게 10초간 자신이 받는 데미지의 반을 흡수시킵니다.", 
/* 26 */         "독, 질식, 낙하 데미지를 받지 않습니다." });
/* 27 */       InitAbility(80, 0, true);
/* 28 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/* 29 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 35 */     if (CustomData == 0) {
/* 36 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 37 */       if ((PlayerCheck(Event.getDamager())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && 
/* 38 */         (!EventManager.DamageGuard) && 
/* 39 */         (pp == "false") && (!ppon)) {
/* 40 */         return 0;
/*    */       }
/*    */       
/* 43 */       if ((PlayerCheck(Event.getEntity())) && 
/* 44 */         (!EventManager.DamageGuard) && 
/* 45 */         (pp != "false") && (ppon)) {
/* 46 */         org.bukkit.Bukkit.getPlayer(pp).damage((int) (Event.getDamage() / 2.0D), Event.getEntity());
/* 47 */         Event.setDamage((int) (Event.getDamage() / 2.0D));
/*    */       }
/*    */       
/*    */     }
/* 51 */     else if (CustomData == 3) {
/* 52 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/* 53 */       if (PlayerCheck(Event2.getEntity())) {
/* 54 */         if ((Event2.getCause() == EntityDamageEvent.DamageCause.POISON) || 
/* 55 */           (Event2.getCause() == EntityDamageEvent.DamageCause.DROWNING)) {
/* 56 */           Event2.setCancelled(true);
/*    */         }
/* 58 */         else if (Event2.getCause() == EntityDamageEvent.DamageCause.FALL) {
/* 59 */           GetPlayer().sendMessage(
/* 60 */             ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/* 61 */           Event2.setCancelled(true);
/*    */         }
/*    */       }
/*    */     }
/* 65 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 69 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 70 */     pp = ((Player)Event.getEntity()).getName();
/* 71 */     GetPlayer().sendMessage(ChatColor.GREEN + ((Player)Event.getEntity()).getName() + "님은 이제 10초간 당신의 데미지의 반을 흡수합니다.");
/* 72 */     ((Player)Event.getEntity()).sendMessage(ChatColor.RED + "당신은 10초간 " + GetPlayer().getName() + "님이 받는 데미지의 반을 흡수합니다.");
/* 73 */     ppon = true;
/* 74 */     Timer timer = new Timer();
/* 75 */     timer.schedule(new offTimer(), 10000L);
/*    */   }
/*    */   
/*    */   class offTimer extends TimerTask {
/*    */     offTimer() {}
/*    */     
/*    */     public void run() {
/* 82 */       Angel.ppon = false;
/* 83 */       Angel.pp = "false";
/* 84 */       Angel.this.GetPlayer().sendMessage(ChatColor.DARK_PURPLE + "지속시간이 끝났습니다.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Angel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */