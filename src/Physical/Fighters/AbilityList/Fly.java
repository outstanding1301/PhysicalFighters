/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Fly
/*    */   extends AbilityBase
/*    */ {
/*    */   public Fly()
/*    */   {
/* 24 */     InitAbility("플라이", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.GOD, new String[] {
/* 25 */       "철괴를 휘두를시에 10초간 하늘을 날라다닐 수 있습니다.", 
/* 26 */       "낙하 데미지를 받지 않습니다." });
/* 27 */     InitAbility(60, 0, true);
/* 28 */     RegisterLeftClickEvent();
/* 29 */     EventManager.onEntityDamage.add(new EventData(this, 3));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 34 */     if (CustomData == 0) {
/* 35 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 36 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem))) {
/* 37 */         return 0;
/*    */       }
/*    */     }
/* 40 */     else if (CustomData == 3) {
/* 41 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/* 42 */       if ((PlayerCheck(Event2.getEntity())) && 
/* 43 */         (Event2.getCause() == EntityDamageEvent.DamageCause.FALL)) {
/* 44 */         GetPlayer().sendMessage(
/* 45 */           ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/* 46 */         Event2.setCancelled(true);
/*    */       }
/*    */     }
/*    */     
/* 50 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 54 */     GetPlayer().setAllowFlight(true);
/* 55 */     GetPlayer().setFlying(true);
/*    */     
/* 57 */     Timer timer = new Timer();
/* 58 */     timer.schedule(new offTimer(), 10000L);
/*    */   }
/*    */   
/*    */   class offTimer extends TimerTask {
/*    */     offTimer() {}
/*    */     
/* 64 */     public void run() { Fly.this.GetPlayer().setFlying(false);
/* 65 */       Fly.this.GetPlayer().setAllowFlight(false);
/* 66 */       Fly.this.GetPlayer().sendMessage(ChatColor.DARK_PURPLE + "지속시간이 끝났습니다.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Fly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */