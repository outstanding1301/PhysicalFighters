/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Assassin
/*    */   extends AbilityBase
/*    */ {
/*    */   public Assassin()
/*    */   {
/* 21 */     InitAbility("어쌔신", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.C, new String[] {
/* 22 */       "뒤에서 공격할시에 데미지를 두배로 입히고 눈을 가립니다." });
/* 23 */     InitAbility(0, 0, true);
/* 24 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 29 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 30 */     if ((PlayerCheck(Event.getDamager())) && 
/* 31 */       ((Event.getEntity() instanceof Player))) {
/* 32 */       Player p = (Player)Event.getEntity();
/* 33 */       Player p1 = (Player)Event.getDamager();
/* 34 */       if (AbilityBase.Direction(p) == AbilityBase.Direction(p1))
/*    */       {
/* 36 */         return 0;
/*    */       }
/*    */     }
/* 39 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 44 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 45 */     Player p = (Player)Event.getEntity();
/* 46 */     p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0), true);
/* 47 */     Event.setDamage((int) (Event.getDamage() * 2.0D));
/* 48 */     ((Player)Event.getDamager()).sendMessage(ChatColor.GREEN + "백스텝 성공!");
/* 49 */     p.sendMessage(ChatColor.RED + "백스텝을 당하셨습니다.");
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Assassin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */