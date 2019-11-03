/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class ShockWave extends AbilityBase
/*    */ {
/*    */   public ShockWave()
/*    */   {
/* 17 */     InitAbility("쇼크웨이브", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/* 18 */       "자신이 보고있는 방향으로 막강한 직선 충격포를 쏩니다.", 
/* 19 */       "충격파로 인해 물과 벽 건너편까지 폭발력이 통과합니다.", 
/* 20 */       "어떤 방향으로도 발사할수 있으며 발사 제약이 없습니다.", 
/* 21 */       "공격 범위는 25칸정도이며 자신의 주변 4칸은 보호됩니다.", 
/* 22 */       "금괴 왼클릭으로 능력을 발동시킵니다. 철괴 1개 소모." });
/* 23 */     InitAbility(53, 0, true);
/* 24 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 29 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 30 */     if ((PlayerCheck(Event.getPlayer())) && 
/* 31 */       (ItemCheck(Material.GOLD_INGOT.getId()))) {
/* 32 */       PlayerInventory inv = Event.getPlayer().getInventory();
/* 33 */       if (inv.contains(Material.IRON_INGOT, 1)) {
/* 34 */         return 0;
/*    */       }
/* 36 */       Event.getPlayer().sendMessage(org.bukkit.ChatColor.RED + "철괴가 부족합니다.");
/* 37 */       return -2;
/*    */     }
/*    */     
/*    */ 
/* 41 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 47 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 48 */     PlayerInventory inv = Event.getPlayer().getInventory();
/* 49 */     Location l = Event.getPlayer().getLocation();
/* 50 */     Location l2 = Event.getPlayer().getLocation();
/*    */     
/* 52 */     int sell = inv.first(Material.IRON_INGOT);
/* 53 */     if (inv.getItem(sell).getAmount() == 1) {
/* 54 */       inv.clear(sell);
/*    */     } else {
/* 56 */       inv.getItem(sell).setAmount(inv.getItem(sell).getAmount() - 1);
/*    */     }
/* 58 */     Event.getPlayer().updateInventory();
/*    */     
/* 60 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/* 61 */     double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/* 62 */     for (int i = 1; i < 7; i++) {
/* 63 */       l2.setX(l.getX() + (3 * i + 3) * (
/* 64 */         Math.sin(degrees) * Math.cos(ydeg)));
/* 65 */       l2.setY(l.getY() + (3 * i + 3) * Math.sin(ydeg));
/* 66 */       l2.setZ(l.getZ() + (3 * i + 3) * (
/* 67 */         Math.cos(degrees) * Math.cos(ydeg)));
/* 68 */       l2.getWorld().createExplosion(l2, 4.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\ShockWave.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */