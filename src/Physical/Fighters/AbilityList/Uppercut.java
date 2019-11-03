/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Uppercut extends AbilityBase
/*    */ {
/*    */   public Uppercut()
/*    */   {
/* 19 */     InitAbility("어퍼컷", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/* 20 */       "플레이어를 공격하면 피격플레이어는 공중으로 아주 높이 뜨게됩니다." });
/* 21 */     InitAbility(10, 0, true, AbilityBase.ShowText.No_CoolDownText);
/* 22 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 26 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 27 */     if (PlayerCheck(Event.getDamager()) && !EventManager.DamageGuard) {
/* 28 */       return 0;
/*    */     }
/* 30 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 34 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 35 */     Entity e = Event.getEntity();
/* 36 */     Location lt = e.getLocation();
/* 37 */     lt.setY(lt.getY() + 5.0D);
/* 38 */     e.setVelocity(e.getVelocity().add(
/* 39 */       e.getLocation().toVector().subtract(lt.toVector()).normalize()
/* 40 */       .multiply(-2)));
/* 41 */     Event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Uppercut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */