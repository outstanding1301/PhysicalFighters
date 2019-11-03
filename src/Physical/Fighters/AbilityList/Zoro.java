/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Zoro extends AbilityBase
/*    */ {
/* 17 */   int dama = 0;
/*    */   
/* 19 */   public Zoro() { InitAbility("조로", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 20 */       "철괴 왼쪽클릭시 칼의 데미지가 랜덤으로 설정됩니다." });
/* 21 */     InitAbility(45, 0, true);
/* 22 */     RegisterLeftClickEvent();
/* 23 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 28 */     if (CustomData == 1) {
/* 29 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 30 */       if ((PlayerCheck(Event.getDamager())) && ((ItemCheck(Material.DIAMOND_SWORD.getId())) || (ItemCheck(Material.WOOD_SWORD.getId())) || (ItemCheck(Material.IRON_SWORD.getId())) || (ItemCheck(Material.GOLD_SWORD.getId())))) {
/* 31 */         if (this.dama != 0) {
/* 32 */           Event.setDamage(this.dama);
/*    */         } else {
/* 34 */           this.dama = ((int)Event.getDamage());
/*    */         }
/*    */       }
/* 37 */     } else if (CustomData == 0)
/*    */     {
/* 39 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/* 40 */       if ((PlayerCheck(Event1.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/* 41 */         return 0;
/*    */       }
/*    */     }
/* 44 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 49 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 50 */     int rand = 0;
/* 51 */     Random random = new Random();
/* 52 */     rand = random.nextInt(5) + 5;
/*    */     
/* 54 */     this.dama = rand;
/* 55 */     Event.getPlayer().sendMessage(org.bukkit.ChatColor.RED + "데미지가 " + this.dama + "로 설정되었습니다.");
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Zoro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */