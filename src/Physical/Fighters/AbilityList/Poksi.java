/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.ProjectileHitEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Poksi
/*    */   extends AbilityBase
/*    */ {
/*    */   public Poksi()
/*    */   {
/* 30 */     InitAbility("이슈타르의 링", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 31 */       "철괴로 능력을 사용합니다.", 
/* 32 */       "철괴를 들고 우클릭시 바라보는 방향으로 화살 두발을 발사합니다. (꾹 느르고 있으면 연사됩니다.)" });
/* 33 */     InitAbility(0, 0, true, AbilityBase.ShowText.Custom_Text);
/*    */     
/* 35 */     RegisterRightClickEvent();
/* 36 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 3));
/* 37 */     EventManager.onProjectileHitEvent.add(new EventData(this, 5));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 41 */     switch (CustomData) {
/*    */     case 1: 
/* 43 */       PlayerInteractEvent Event0 = (PlayerInteractEvent)event;
/* 44 */       if ((!EventManager.DamageGuard) && 
/* 45 */         (PlayerCheck(Event0.getPlayer())) && (ItemCheck(ACC.DefaultItem))) {
/* 46 */         return 10;
/*    */       }
/*    */       break;
/*    */     case 3: 
/* 50 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/* 51 */       if ((Event1.getDamager() instanceof Arrow)) {
/* 52 */         Arrow a = (Arrow)Event1.getDamager();
/* 53 */         if (PlayerCheck((Entity)a.getShooter())) {
/* 54 */           if (((Event1.getEntity() instanceof Player)) && 
/* 55 */             ((Player)a.getShooter() == 
/* 56 */             (Player)Event1
/* 57 */             .getEntity()))
/* 58 */             return -1;
/* 59 */           return 3;
/*    */         }
/*    */       }
/*    */       break;
/*    */     case 5: 
/* 64 */       ProjectileHitEvent Event2 = (ProjectileHitEvent)event;
/* 65 */       if ((Event2.getEntity() instanceof Arrow)) {
/* 66 */         Arrow a = (Arrow)Event2.getEntity();
/* 67 */         if (((a.getShooter() instanceof Player)) && 
/* 68 */           (PlayerCheck((Player)a.getShooter()))) {
/* 69 */           a.remove();
/* 70 */           return -2;
/*    */         }
/*    */       }
/*    */       
/*    */       break;
/*    */     }
/*    */     
/* 77 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 83 */     switch (CustomData) {
/*    */     case 3: 
/* 85 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*    */       
/* 87 */       Event0.setDamage(4);
/* 88 */       break;
/*    */     case 10: 
/* 90 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/* 91 */       Arrow a = (Arrow)Event1.getPlayer().launchProjectile(Arrow.class);
/* 92 */       a.setVelocity(a.getVelocity().multiply(3));
/* 93 */       Arrow a2 = (Arrow)Event1.getPlayer().launchProjectile(Arrow.class);
/* 94 */       a2.setVelocity(a2.getVelocity().multiply(2));
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\poksi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */