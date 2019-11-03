/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Blind extends AbilityBase
/*    */ {
/*    */   public Blind()
/*    */   {
/* 20 */     if (!PhysicalFighters.Specialability) {
/* 21 */       InitAbility("블라인드", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.C, new String[] {
/* 22 */         "자신에게 공격받은 사람은 3초간 시야를 잃습니다." });
/* 23 */       InitAbility(0, 0, true);
/* 24 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 31 */     if ((!EventManager.DamageGuard) && 
/* 32 */       (PlayerCheck(Event.getDamager())) && 
/* 33 */       ((Event.getEntity() instanceof Player))) {
/* 34 */       return 0;
/*    */     }
/* 36 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 41 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 42 */     Player p = (Player)Event.getEntity();
/* 43 */     p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0), 
/* 44 */       true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Blind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */