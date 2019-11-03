/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;

import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ public class Trash extends AbilityBase
/*    */ {
/*    */   public Trash()
/*    */   {
/* 19 */     if ((!PhysicalFighters.Toner) && 
/* 20 */       (PhysicalFighters.SRankUsed) && 
/* 21 */       (!PhysicalFighters.Specialability))
/*    */     {
/* 23 */       InitAbility("쓰레기", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.FF, new String[] {
/* 24 */         "능력 사용시 체력을 소비하여 1분간 허약해집니다.", 
/* 25 */         "철괴로 상대를 타격시 1%확률로 능력을 서로 바꿉니다." });
/* 26 */       InitAbility(10, 0, true);
/* 27 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/* 28 */       RegisterRightClickEvent();
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 34 */     switch (CustomData) {
/*    */     case 0: 
/* 36 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/* 37 */       if (PlayerCheck(Event0.getDamager()))
/*    */       {
/* 39 */         if (Math.random() <= 0.01D)
/*    */         {
/* 41 */           Player p1 = (Player)Event0.getDamager();
/* 42 */           Player p2 = (Player)Event0.getEntity();
/* 43 */           AbilityBase a = AbilityBase.FindAbility(p1);
/* 44 */           AbilityBase a2 = AbilityBase.FindAbility(p2);
/* 45 */           a2.SetPlayer(p1, false);
/* 46 */           a.SetPlayer(p2, false);
/* 47 */           a2.SetRunAbility(true);
/* 48 */           a.SetRunAbility(true);
/* 49 */           p1.sendMessage("당신은 쓰레기 능력을 사용해 상대방과 능력을 바꿨습니다.");
/* 50 */           p2.sendMessage("당신은 쓰레기 능력에 의해 쓰레기가 되었습니다.");
/*    */         }
/*    */       }
/* 53 */       break;
/*    */     case 1: 
/* 55 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 56 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/* 57 */         return 0;
/*    */       }
/*    */       break;
/*    */     }
/* 61 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 66 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 67 */     Player p = Event.getPlayer();
/* 68 */     p.setHealth(((Damageable)p).getHealth() - 4);
/* 69 */     p.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.WEAKNESS, 1200, 
/* 70 */       0), true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Trash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */