/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ 
/*    */ public class Explosion extends AbilityBase
/*    */ {
/*    */   public Explosion()
/*    */   {
/* 20 */     if (!PhysicalFighters.Toner) {
/* 21 */       InitAbility("익스플로젼", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.B, new String[] {
/* 22 */         "사망시에 엄청난 연쇄폭발을 일으켜 주변의 유저들을 죽입니다." });
/* 23 */       InitAbility(0, 0, true);
/* 24 */       EventManager.onEntityDeath.add(new EventData(this, 0));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     switch (CustomData) {
/*    */     case 0: 
/* 32 */       EntityDeathEvent Event0 = (EntityDeathEvent)event;
/* 33 */       if (PlayerCheck(Event0.getEntity()))
/* 34 */         return 0;
/*    */       break;
/*    */     }
/* 37 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 42 */     switch (CustomData) {
/*    */     case 0: 
/* 44 */       PlayerDeathEvent Event0 = (PlayerDeathEvent)event;
/* 45 */       Player killed = Event0.getEntity();
/* 46 */       killed.getWorld().createExplosion(killed.getLocation(), 8.0F, 
/* 47 */         false);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Explosion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */