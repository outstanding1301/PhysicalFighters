/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.util.BlockIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Paladin
/*    */   extends AbilityBase
/*    */ {
/* 27 */   boolean candmg = true;
/*    */   
/*    */   public Paladin() {
/* 30 */     InitAbility("팔라딘", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.A, new String[] {
/* 31 */       "칼을 들었을 때 다수의 적에게 10~20의 랜덤데미지를 가하며 사거리가 두배가 됩니다. 단, 공격속도가 감소합니다." });
/* 32 */     InitAbility(0, 0, true);
/* 33 */     RegisterLeftClickEvent();
/* 34 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 39 */     if (CustomData == 0) {
/* 40 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 41 */       if (PlayerCheck(e.getPlayer()) && !EventManager.DamageGuard) {
/* 42 */         return 0;
/*    */       }
/*    */     }
/* 45 */     if (CustomData == 1) {
/* 46 */       EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
/* 47 */       if ((PlayerCheck(e.getDamager())) && 
/* 48 */         (!this.candmg)) {
/* 49 */         e.setCancelled(true);
/*    */       }
/*    */     }
/* 52 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 57 */     if (CustomData == 0) {
/* 58 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 59 */       if ((PlayerCheck(e.getPlayer())) && 
/* 60 */         (isSword(e.getPlayer().getItemInHand()))) {
/* 61 */         BlockIterator bi = new BlockIterator(e.getPlayer(), 6);
/* 62 */         Random r = new Random();
/* 63 */         int i = r.nextInt(10) + 10;
/* 64 */         while (bi.hasNext()) {
/* 65 */           Block bb = bi.next();
/* 66 */           ExplosionDMG(e.getPlayer(), bb.getLocation(), 3, i);
/*    */         }
/* 68 */         e.getPlayer().sendMessage(ChatColor.GREEN + ""+i + "의 데미지를 가했습니다.");
/* 69 */         this.candmg = false;
/* 70 */         Timer t = new Timer();
/* 71 */         t.schedule(new TimerTask()
/*    */         {
/*    */           public void run()
/*    */           {
/* 75 */             Paladin.this.candmg = true;
/*    */           }
/*    */           
/* 78 */         }, 2000L);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Paladin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */