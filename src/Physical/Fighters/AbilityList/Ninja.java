/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Ninja
/*    */   extends AbilityBase
/*    */ {
/*    */   public Ninja()
/*    */   {
/* 25 */     InitAbility("닌자", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 26 */       "철괴 왼쪽클릭시 매우 빠르게 화살을 발사합니다. ", 
/* 27 */       "이 화살에 플레이어가 맞을 경우 10%확률로 플레이어를 폭발시키고, ", 
/* 28 */       "30%의 확률로 플레이어에게 불을 붙히고,", "65%의 확률로 쿨타임이 초기화됩니다." });
/* 29 */     InitAbility(10, 0, true);
/* 30 */     RegisterLeftClickEvent();
/* 31 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 35 */     switch (CustomData) {
/*    */     case 0: 
/* 37 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 38 */       if ((!EventManager.DamageGuard) && (PlayerCheck(Event.getPlayer())) && 
/* 39 */         (ItemCheck(ACC.DefaultItem))) {
/* 40 */         return 0;
/*    */       }
/*    */       
/*    */       break;
/*    */     case 1: 
/* 45 */       EntityDamageByEntityEvent E = (EntityDamageByEntityEvent)event;
/* 46 */       if ((E.getDamager() instanceof Arrow)) {
/* 47 */         Arrow a = (Arrow)E.getDamager();
/* 48 */         if (PlayerCheck((Player)a.getShooter())) {
/* 49 */           Player p = (Player)a.getShooter();
/* 50 */           E.setDamage(E.getDamage() + 13);
/* 51 */           if (Math.random() <= 0.65D) {
/* 52 */             AbilityCTimerCancel();
/* 53 */             p.sendMessage(ChatColor.YELLOW + "플레이어를 맞춰 쿨타임이 초기화되었습니다.");
/*    */           }
/* 55 */           if (Math.random() <= 0.1D) {
/* 56 */             World w = E.getEntity().getWorld();
/* 57 */             w.createExplosion(E.getEntity().getLocation(), 4.0F);
/*    */           }
/*    */         }
/*    */       }
/*    */       break;
/*    */     }
/* 63 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 68 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 69 */     Player p = Event.getPlayer();
/* 70 */     Arrow a = p.shootArrow();
/*    */     
/*    */ 
/* 73 */     a.setVelocity(a.getVelocity().multiply(8));
/* 74 */     if (Math.random() <= 0.3D) {
/* 75 */       a.setFireTicks(20);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Ninja.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */