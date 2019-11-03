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
/*    */ public class Poison extends AbilityBase
/*    */ {
/*    */   public Poison()
/*    */   {
/* 19 */     InitAbility("포이즌", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.B, new String[] {
/* 20 */       "자신에게 공격받은 사람은 3초간 독에 감염됩니다." });
/* 21 */     InitAbility(0, 0, true);
/* 22 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 27 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 28 */     if ((PlayerCheck(Event.getDamager())) && ((Event.getEntity() instanceof Player))) {
/* 29 */       return 0;
/*    */     }
/* 31 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 36 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 37 */     Player p = (Player)Event.getEntity();
/* 38 */     p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 2), true);
/* 39 */     p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 2), false);
/* 40 */     p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 2), false);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Poison.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */