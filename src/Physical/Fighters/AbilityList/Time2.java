/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import java.util.Collection;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Time2 extends AbilityBase
/*    */ {
/*    */   public Time2()
/*    */   {
/* 18 */     if (!Physical.Fighters.PhysicalFighters.Specialability) {
/* 19 */       InitAbility("시간을 지배하는 자", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/* 20 */         "자신을 제외한 모든 플레이어들의 속도를 15초간 느리게 만듭니다." });
/* 21 */       InitAbility(40, 0, true);
/* 22 */       RegisterLeftClickEvent();
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 28 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 29 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 30 */       return 0;
/*    */     }
/* 32 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 37 */     Player[] List = Bukkit.getOnlinePlayers();
/* 38 */     Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = List).length; for (int i = 0; i < j; i++) { Player p = arrayOfPlayer1[i];
/* 39 */       if (p != GetPlayer()) {
/* 40 */         p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2), true);
/* 41 */         p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2), false);
/* 42 */         p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2), false); }
/* 43 */       p.sendMessage(org.bukkit.ChatColor.GREEN + 
/* 44 */         "시간을 지배하는 자에의해 15초간 당신의 시간이 느리게 흘러갑니다.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Time2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */