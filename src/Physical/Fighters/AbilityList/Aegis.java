/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Aegis extends AbilityBase
/*    */ {
/*    */   public Aegis()
/*    */   {
/* 21 */       InitAbility("이지스", AbilityBase.Type.Active_Continue, AbilityBase.Rank.A, new String[] {
/* 22 */         "능력 사용시 일정시간동안 무적이 됩니다. 무적은 대부분의", 
/* 23 */         "데미지를 무력화시키며 능력 사용중엔 Mirroring 능력도 ", "무력화됩니다." });
/* 24 */       InitAbility(28, 6, true);
/* 25 */       RegisterLeftClickEvent();
/* 26 */       EventManager.onEntityDamage.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 32 */     if (!PhysicalFighters.ReverseMode) {
/* 33 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 34 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard)
/* 35 */         return 0;
/*    */     } else {
/* 37 */       EntityDamageEvent Event = (EntityDamageEvent)event;
/* 38 */       if (PlayerCheck(Event.getEntity()))
/* 39 */         return 0;
/*    */     }
/* 41 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 46 */     if (!PhysicalFighters.ReverseMode) {
/* 47 */       EntityDamageEvent Event = (EntityDamageEvent)event;
/* 48 */       if (PlayerCheck(Event.getEntity())) {
/* 49 */         Player p = (Player)Event.getEntity();
/* 50 */         p.setFireTicks(0);
/* 51 */         Event.setCancelled(true);
/*    */       }
/*    */     } else {
/* 54 */       EntityDamageEvent Event = (EntityDamageEvent)event;
/* 55 */       Event.setDamage((int) (Event.getDamage() * 1000.0D));
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Aegis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */