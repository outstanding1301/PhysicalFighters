/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Ckyomi extends AbilityBase
/*    */ {
/*    */   public Ckyomi()
/*    */   {
/* 19 */     InitAbility("츠쿠요미", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.A, new String[] {
/* 20 */       "상대를 공격하면 상대에게 5초간 혼란효과와 디버프를 줍니다." });
/* 21 */     InitAbility(0, 0, true);
/* 22 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 27 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 28 */     if ((!EventManager.DamageGuard) && 
/* 29 */       (PlayerCheck(Event.getDamager())) && 
/* 30 */       ((Event.getEntity() instanceof Player))) {
/* 31 */       return 0;
/*    */     }
/* 33 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 38 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 39 */     Player p = (Player)Event.getEntity();
/* 40 */     p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 
/* 41 */       0), true);
/* 42 */     p.addPotionEffect(
/* 43 */       new PotionEffect(PotionEffectType.WEAKNESS, 100, 0), true);
/* 44 */     p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 
/* 45 */       0), true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Ckyomi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */