/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ public class Sasuke extends AbilityBase
/*    */ {
/*    */   public Sasuke()
/*    */   {
/* 20 */     InitAbility("사스케", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 21 */       "철괴로 상대를 타격시에 치도리를 사용해 상대를 엄청난 데미지로 감전시킵니다." });
/* 22 */     InitAbility(30, 0, true);
/* 23 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 28 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 29 */     if ((PlayerCheck(Event.getDamager())) && (ItemCheck(ACC.DefaultItem)) && 
/* 30 */       (!EventManager.DamageGuard)) {
/* 31 */       return 0;
/*    */     }
/* 33 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 38 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 39 */     Player p = (Player)Event.getEntity();
/* 40 */     p.damage(15);
/* 41 */     Event.getEntity().getWorld()
/* 42 */       .strikeLightning(Event.getEntity().getLocation());
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Sasuke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */