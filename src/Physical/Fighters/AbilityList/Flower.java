/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Flower extends AbilityBase
/*    */ {
/*    */   public Flower()
/*    */   {
/* 21 */     if (PhysicalFighters.SRankUsed) {
/* 22 */       InitAbility("흡혈초", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/* 23 */         "철괴 왼클릭시 맞은 사람의 체력을 흡수합니다.", 
/* 24 */         "철괴 오른클릭시 자신의 체력을 소비해 레벨을 얻습니다." });
/* 25 */       InitAbility(5, 0, true);
/* 26 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/* 27 */       RegisterRightClickEvent();
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 33 */     switch (CustomData) {
/*    */     case 0: 
/* 35 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/* 36 */       if (((Event1.getEntity() instanceof Player)) && 
/* 37 */         (PlayerCheck(Event1.getDamager())) && 
/* 38 */         (ItemCheck(ACC.DefaultItem)) && 
/* 39 */         (!EventManager.DamageGuard)) {
/* 40 */         return 1;
/*    */       }
/*    */       
/*    */       break;
/*    */     case 1: 
/* 45 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 46 */       if ((PlayerCheck(Event2.getPlayer())) && 
/* 47 */         (((Damageable)GetPlayer()).getHealth() >= 16.0D) && 
/* 48 */         (ItemCheck(ACC.DefaultItem))) {
/* 49 */         return 2;
/*    */       }
/*    */       break;
/*    */     }
/*    */     
/* 54 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 59 */     switch (CustomData) {
/*    */     case 1: 
/* 61 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/* 62 */       Player p1 = (Player)Event1.getEntity();
/* 63 */       if (((Damageable)GetPlayer()).getHealth() <= 14.0D) {
/* 64 */         GetPlayer().setHealth(((Damageable)GetPlayer()).getHealth() + 6);
/* 65 */         p1.setHealth(((Damageable)p1).getHealth() - 6);
/*    */       } else {
/* 67 */         GetPlayer().setHealth(20);
/* 68 */         p1.setHealth(((Damageable)p1).getHealth() - 6);
/*    */       }
/* 70 */       p1.sendMessage(String.format(
/* 71 */         ChatColor.RED + "%s님이 당신의 체력을 흡수했습니다.", new Object[] {GetPlayer()
/* 72 */         .getName() }));
/* 73 */       GetPlayer().sendMessage(
/* 74 */         String.format(ChatColor.RED + "%s님의 체력을 흡수했습니다.", new Object[] {
/* 75 */         p1.getName() }));
/* 76 */       break;
/*    */     case 2: 
/* 78 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 79 */       Player p2 = Event2.getPlayer();
/* 80 */       p2.setLevel(p2.getLevel() + 10);
/* 81 */       p2.sendMessage(ChatColor.GREEN + "레벨을 얻었습니다. +2");
/* 82 */       p2.setHealth(((Damageable)p2).getHealth() - 15);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Flower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */