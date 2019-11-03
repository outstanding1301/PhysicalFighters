/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Booster extends AbilityBase
/*    */ {
/*    */   public Booster()
/*    */   {
/* 20 */     InitAbility("부스터", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.B, new String[] {
/* 21 */       "공격시에 딜레이가 매우 낮습니다. 단 당신의 데미지는 3~6로 랜덤입니다." });
/* 22 */     InitAbility(0, 0, true);
/* 23 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 28 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 29 */     if ((PlayerCheck(Event.getDamager())) && 
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
/* 40 */     p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0), true);
/* 41 */     p.setNoDamageTicks(8);
/* 42 */     Random rand = new Random();
/* 43 */     int r = rand.nextInt(6);
/* 44 */     switch (r) {
/* 45 */     case 0:  Event.setDamage((int) 3.0D);
/*    */     case 1: 
/* 47 */       Event.setDamage((int) 5.0D);
/*    */     case 2: 
/* 49 */       Event.setDamage((int) 4.0D);
/*    */     case 5: 
/* 51 */       Event.setDamage((int) 6.0D);
/*    */     case 3: 
/* 53 */       Event.setDamage((int) 5.0D);
/*    */     case 4: 
/* 55 */       Event.setDamage((int) 3.0D);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Booster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */